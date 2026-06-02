package com.bit.backend.services.impl;

import com.bit.backend.dtos.BookClassDto;
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
import java.util.Optional;

@Service
public class BookClassService implements BookClassServiceI {

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



        // === Step 2: Check if user already booked the same class ===
        Optional<BookClassEntity> existingBooking = bookClassRepository
                .findByBookedByAndClassId(bookClassDto.getBookedBy(), bookClassDto.getClassId());
        if (existingBooking.isPresent()) {
            throw new AppException("You have already booked this class.", HttpStatus.CONFLICT);
        }

        // === Step 3: Check remaining slots ===
        Optional<AddClassEntity> addClassEntityOpt = addClassRepository.findById(bookClassDto.getClassId());
        if (addClassEntityOpt.isEmpty()) {
            throw new AppException("Class not found. It may have been removed.", HttpStatus.NOT_FOUND);
        }

        AddClassEntity addClassEntity = addClassEntityOpt.get();

        if (!"Scheduled".equalsIgnoreCase(addClassEntity.getStatus())) {
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
        // Check class exists and is bookable
        AddClassEntity classEntity = addClassRepository.findById(classId)
                .orElseThrow(() -> new AppException("Class not found. It may have been removed.", HttpStatus.NOT_FOUND));

        if (!"Scheduled".equalsIgnoreCase(classEntity.getStatus())) {
            throw new AppException("This class is no longer available for booking.", HttpStatus.BAD_REQUEST);
        }
        if (classEntity.getRemainingSlots() < 1) {
            throw new AppException("This class is fully booked. No slots are available.", HttpStatus.CONFLICT);
        }

        // Check member hasn't already booked this class
        if (bookClassRepository.findByUserIdAndClassId(userId, classId).isPresent()) {
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

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
}
