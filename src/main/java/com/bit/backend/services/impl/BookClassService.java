package com.bit.backend.services.impl;

import com.bit.backend.dtos.BookClassDto;
import com.bit.backend.dtos.MemberDto;
import com.bit.backend.entities.AddClassEntity;
import com.bit.backend.entities.BookClassEntity;
import com.bit.backend.entities.MemberEntity;
import com.bit.backend.entities.SupplementInventoryEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.BookClassMapper;
import com.bit.backend.mappers.MemberMapper;
import com.bit.backend.repositories.AddClassRepository;
import com.bit.backend.repositories.BookClassRepository;
import com.bit.backend.repositories.MemberRepository;
import com.bit.backend.services.BookClassServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookClassService implements BookClassServiceI {

    private final BookClassRepository bookClassRepository;
    private final BookClassMapper bookClassMapper;
    private final AddClassRepository addClassRepository;

    public BookClassService(BookClassRepository bookClassRepository, BookClassMapper bookClassMapper, AddClassRepository addClassRepository) {
        this.bookClassRepository = bookClassRepository;
        this.bookClassMapper = bookClassMapper;
        this.addClassRepository = addClassRepository;
    }

    @Override
    public BookClassDto addBookClassEntity(BookClassDto bookClassDto) {
        System.out.println("In the addBookClassEntity method");

        // === Step 1: Validate input ===
        if (bookClassDto.getFirstName() == null || bookClassDto.getFirstName().trim().isEmpty()) {
            throw new AppException("First name is required", HttpStatus.BAD_REQUEST);
        }
        if (bookClassDto.getLastName() == null || bookClassDto.getLastName().trim().isEmpty()) {
            throw new AppException("Last name is required", HttpStatus.BAD_REQUEST);
        }
        if (bookClassDto.getEmail() == null || !isValidEmail(bookClassDto.getEmail())) {
            throw new AppException("Valid email is required", HttpStatus.BAD_REQUEST);
        }
        if (bookClassDto.getBookedBy() == null || bookClassDto.getBookedBy().trim().isEmpty()) {
            throw new AppException("BookedBy (user) is required", HttpStatus.BAD_REQUEST);
        }
        if (bookClassDto.getClassId() <= 0) {
            throw new AppException("Valid classId is required", HttpStatus.BAD_REQUEST);
        }



        // === Step 2: Check if user already booked the same class ===
        Optional<BookClassEntity> existingBooking = bookClassRepository
                .findByBookedByAndClassId(bookClassDto.getBookedBy(), bookClassDto.getClassId());
        if (existingBooking.isPresent()) {
            throw new AppException("User has already booked this class", HttpStatus.BAD_REQUEST);
        }

        // === Step 3: Check remaining slots ===
        Optional<AddClassEntity> addClassEntityOpt = addClassRepository.findById(bookClassDto.getClassId());
        if (addClassEntityOpt.isEmpty()) {
            throw new AppException("Class not found", HttpStatus.NOT_FOUND);
        }

        AddClassEntity addClassEntity = addClassEntityOpt.get();

        if (!"Scheduled".equalsIgnoreCase(addClassEntity.getStatus())) {
            throw new AppException("Only scheduled classes can be booked", HttpStatus.BAD_REQUEST);
        }

        if (addClassEntity.getRemainingSlots() < 1) {
            throw new AppException("No available slots", HttpStatus.BAD_REQUEST);
        }

        // === Step 4: All good, book the class ===
        BookClassEntity bookClassEntity = bookClassMapper.toBookClassEntity(bookClassDto);
        BookClassEntity savedItem = bookClassRepository.save(bookClassEntity);

        // === Step 5: Reduce available slots ===
        addClassEntity.setRemainingSlots(addClassEntity.getRemainingSlots() - 1);
        addClassRepository.save(addClassEntity);

        return bookClassMapper.toBookClassDto(savedItem);
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }


}
