package com.bit.backend.services.impl;

import com.bit.backend.dtos.BookClassDto;
import com.bit.backend.dtos.MyBookingDto;
import com.bit.backend.entities.AddClassEntity;
import com.bit.backend.entities.BookClassEntity;
import com.bit.backend.entities.MemberEntity;
import com.bit.backend.entities.User;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.BookClassMapper;
import com.bit.backend.repositories.AddClassRepository;
import com.bit.backend.repositories.BookClassRepository;
import com.bit.backend.repositories.MemberRepository;
import com.bit.backend.repositories.UserRepository;
import com.bit.backend.services.BookClassServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookClassService implements BookClassServiceI {

    private static final String STATUS_CANCELLED = "CANCELLED";
    private static final String STATUS_SCHEDULED = "Scheduled";

    private final BookClassRepository bookClassRepository;
    private final BookClassMapper bookClassMapper;
    private final AddClassRepository addClassRepository;
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;

    public BookClassService(BookClassRepository bookClassRepository, BookClassMapper bookClassMapper,
                            AddClassRepository addClassRepository, UserRepository userRepository,
                            MemberRepository memberRepository) {
        this.bookClassRepository = bookClassRepository;
        this.bookClassMapper = bookClassMapper;
        this.addClassRepository = addClassRepository;
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public BookClassDto addBookClassEntity(BookClassDto bookClassDto) {

        // === Step 1: Validate input ===
        if (bookClassDto.getFirstName() == null || bookClassDto.getFirstName().trim().isEmpty()) {
            throw new AppException("First name is required to complete the booking.", HttpStatus.BAD_REQUEST);
        }
        if (bookClassDto.getLastName() == null || bookClassDto.getLastName().trim().isEmpty()) {
            throw new AppException("Last name is required to complete the booking.", HttpStatus.BAD_REQUEST);
        }
        if (bookClassDto.getEmail() == null || !isValidEmail(bookClassDto.getEmail())) {
            throw new AppException("A valid email address is required to complete the booking.", HttpStatus.BAD_REQUEST);
        }
        if (bookClassDto.getBookedBy() == null || bookClassDto.getBookedBy().trim().isEmpty()) {
            throw new AppException("Unable to identify your account. Please log in again.", HttpStatus.BAD_REQUEST);
        }
        if (bookClassDto.getClassId() <= 0) {
            throw new AppException("Invalid class selection. Please go back and try again.", HttpStatus.BAD_REQUEST);
        }



        // === Step 2: Check if user already has an active booking for the same class ===
        boolean hasActiveBooking = bookClassRepository
                .findByBookedByAndClassId(bookClassDto.getBookedBy(), bookClassDto.getClassId())
                .stream()
                .anyMatch(b -> !STATUS_CANCELLED.equalsIgnoreCase(b.getStatus()));
        if (hasActiveBooking) {
            throw new AppException("You have already booked this class.", HttpStatus.CONFLICT);
        }

        // === Step 3: Check remaining slots ===
        Optional<AddClassEntity> addClassEntityOpt = addClassRepository.findById(bookClassDto.getClassId())
                .filter(e -> !Boolean.TRUE.equals(e.getDeleted()));
        if (addClassEntityOpt.isEmpty()) {
            throw new AppException("Class not found. It may have been removed.", HttpStatus.NOT_FOUND);
        }

        AddClassEntity addClassEntity = addClassEntityOpt.get();

        if (!STATUS_SCHEDULED.equalsIgnoreCase(addClassEntity.getStatus())) {
            throw new AppException("This class is no longer available for booking.", HttpStatus.BAD_REQUEST);
        }

        if (addClassEntity.getRemainingSlots() < 1) {
            throw new AppException("This class is fully booked. No slots are available.", HttpStatus.CONFLICT);
        }

        // === Step 4: All good, book the class ===
        BookClassEntity bookClassEntity = bookClassMapper.toBookClassEntity(bookClassDto);
        BookClassEntity savedItem = bookClassRepository.save(bookClassEntity);

        // === Step 5: Reduce available slots ===
        addClassEntity.setRemainingSlots(addClassEntity.getRemainingSlots() - 1);
        addClassRepository.save(addClassEntity);

        return bookClassMapper.toBookClassDto(savedItem);
    }

    @Override
    @Transactional
    public BookClassDto confirmBooking(long classId, long userId) {
        // Check class exists, is not deleted, and is bookable
        AddClassEntity classEntity = addClassRepository.findById(classId)
                .filter(e -> !Boolean.TRUE.equals(e.getDeleted()))
                .orElseThrow(() -> new AppException("Class not found. It may have been removed.", HttpStatus.NOT_FOUND));

        if (!STATUS_SCHEDULED.equalsIgnoreCase(classEntity.getStatus())) {
            throw new AppException("This class is no longer available for booking.", HttpStatus.BAD_REQUEST);
        }
        if (classEntity.getRemainingSlots() < 1) {
            throw new AppException("This class is fully booked. No slots are available.", HttpStatus.CONFLICT);
        }

        // Check member hasn't already booked this class
        boolean alreadyBooked = bookClassRepository.findByUserIdAndClassId(userId, classId)
                .stream()
                .anyMatch(b -> !STATUS_CANCELLED.equalsIgnoreCase(b.getStatus()));
        if (alreadyBooked) {
            throw new AppException("You have already booked this class.", HttpStatus.CONFLICT);
        }

        // Fetch user → get customerLoginId → fetch member details
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException("Unable to retrieve your account details. Please log in again.", HttpStatus.NOT_FOUND));

        String firstName = user.getFirstName();
        String lastName  = user.getLastName();
        String email     = user.getEmail();
        String phone     = null;

        if (user.getCustomerLoginId() != null) {
            Optional<MemberEntity> memberOpt = memberRepository.findById(user.getCustomerLoginId());
            if (memberOpt.isPresent()) {
                MemberEntity member = memberOpt.get();
                if (member.getFirstName() != null) firstName = member.getFirstName();
                if (member.getLastName()  != null) lastName  = member.getLastName();
                if (member.getEmail()     != null) email     = member.getEmail();
                phone = member.getPhoneNumber();
            }
        }

        // Create the booking
        BookClassEntity booking = new BookClassEntity();
        booking.setUserId(userId);
        booking.setClassId(classId);
        booking.setFirstName(firstName);
        booking.setLastName(lastName);
        booking.setEmail(email);
        booking.setPhone(phone);
        booking.setBookedBy(user.getLogin());
        booking.setStatus("CONFIRMED");
        booking.setBookedDate(LocalDate.now());

        BookClassEntity saved = bookClassRepository.save(booking);

        // Decrement remaining slots
        classEntity.setRemainingSlots(classEntity.getRemainingSlots() - 1);
        addClassRepository.save(classEntity);

        return bookClassMapper.toBookClassDto(saved);
    }

    @Override
    public List<MyBookingDto> getMyBookings(long userId) {
        try {
            List<BookClassEntity> bookings = bookClassRepository.findByUserId(userId);
            List<MyBookingDto> result = new ArrayList<>();

            for (BookClassEntity booking : bookings) {
                Optional<AddClassEntity> classOpt = addClassRepository.findById(booking.getClassId());
                if (classOpt.isEmpty()) continue;

                AddClassEntity cls = classOpt.get();
                MyBookingDto dto = new MyBookingDto();

                dto.setBookingId(booking.getId());
                dto.setUserId(booking.getUserId());
                dto.setBookingStatus(booking.getStatus());
                dto.setBookedDate(booking.getBookedDate());

                dto.setClassId(cls.getId());
                dto.setClassTitle(cls.getClassTitle());
                dto.setClassType(cls.getClassType());
                dto.setDescription(cls.getDescription());
                dto.setClassDate(cls.getDate());
                dto.setStartTime(cls.getStartTime());
                dto.setEndTime(cls.getEndTime());
                dto.setConductorName(cls.getConductorName());
                dto.setTrainerEmployeeId(cls.getTrainerEmployeeId());
                dto.setClassStatus(cls.getStatus());
                dto.setTotalSlots(cls.getTotalSlots());
                dto.setRemainingSlots(cls.getRemainingSlots());

                result.add(dto);
            }

            result.sort((a, b) -> b.getClassDate().compareTo(a.getClassDate()));
            return result;
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to load your bookings. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public void cancelBooking(long bookingId, long userId) {

        // Step 1: Find the booking
        BookClassEntity booking = bookClassRepository.findById(bookingId)
                .orElseThrow(() -> new AppException(
                    "Booking not found. It may have already been removed.", HttpStatus.NOT_FOUND));

        // Step 2: Verify the booking belongs to the requesting user
        if (booking.getUserId() == null || !booking.getUserId().equals(userId)) {
            throw new AppException(
                "You are not authorised to cancel this booking.", HttpStatus.FORBIDDEN);
        }

        // Step 3: Check booking is still active
        if (STATUS_CANCELLED.equalsIgnoreCase(booking.getStatus())) {
            throw new AppException(
                "This booking has already been cancelled.", HttpStatus.CONFLICT);
        }

        // Step 4: Find the class
        Optional<AddClassEntity> classOpt = addClassRepository.findById(booking.getClassId());

        // Step 5: Validate class state if the class still exists
        if (classOpt.isPresent()) {
            AddClassEntity cls = classOpt.get();

            if ("Completed".equalsIgnoreCase(cls.getStatus())) {
                throw new AppException(
                    "Cannot cancel a booking for a class that has already been completed.",
                    HttpStatus.CONFLICT);
            }

            // Step 6: Free up the slot if class is still scheduled
            if (STATUS_SCHEDULED.equalsIgnoreCase(cls.getStatus())) {
                cls.setRemainingSlots(cls.getRemainingSlots() + 1);
                addClassRepository.save(cls);
            }
        }

        // Step 7: Mark the booking as cancelled
        booking.setStatus(STATUS_CANCELLED);
        bookClassRepository.save(booking);
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
}
