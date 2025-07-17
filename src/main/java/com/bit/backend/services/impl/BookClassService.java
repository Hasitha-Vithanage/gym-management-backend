package com.bit.backend.services.impl;

import com.bit.backend.dtos.BookClassDto;
import com.bit.backend.dtos.MemberDto;
import com.bit.backend.entities.BookClassEntity;
import com.bit.backend.entities.MemberEntity;
import com.bit.backend.mappers.BookClassMapper;
import com.bit.backend.mappers.MemberMapper;
import com.bit.backend.repositories.BookClassRepository;
import com.bit.backend.repositories.MemberRepository;
import com.bit.backend.services.BookClassServiceI;
import org.springframework.stereotype.Service;

@Service
public class BookClassService implements BookClassServiceI {

    private final BookClassRepository bookClassRepository;
    private final BookClassMapper bookClassMapper;

    public BookClassService(BookClassRepository bookClassRepository, BookClassMapper bookClassMapper) {
        this.bookClassRepository = bookClassRepository;
        this.bookClassMapper = bookClassMapper;
    }

    @Override
    public BookClassDto addBookClassEntity(BookClassDto bookClassDto) {
        System.out.println("In the addBookClassEntity method");

        BookClassEntity bookClassEntity = bookClassMapper.toBookClassEntity(bookClassDto);
        BookClassEntity savedItem = bookClassRepository.save(bookClassEntity);
        BookClassDto savedDto = bookClassMapper.toBookClassDto(savedItem);
        return savedDto;
    }

}
