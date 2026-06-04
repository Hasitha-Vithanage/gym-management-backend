package com.bit.backend.services;

import com.bit.backend.dtos.BookClassDto;
import com.bit.backend.dtos.MyBookingDto;

import java.util.List;

public interface BookClassServiceI {

    BookClassDto addBookClassEntity(BookClassDto bookClassDto);
    BookClassDto confirmBooking(long classId, long userId);
    List<MyBookingDto> getMyBookings(long userId);
    void cancelBooking(long bookingId, long userId);
}
