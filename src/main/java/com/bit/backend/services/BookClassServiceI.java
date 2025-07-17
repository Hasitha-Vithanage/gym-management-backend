package com.bit.backend.services;

import com.bit.backend.dtos.AssignTrainerDto;
import com.bit.backend.dtos.BookClassDto;

public interface BookClassServiceI {

    BookClassDto addBookClassEntity(BookClassDto bookClassDto);
}
