package com.bit.backend.dtos;

import java.time.LocalDate;

public record SignUpDto(
        String firstName,
        String lastName,
        String login,
        String email,
        char[] password,
        String role,
        String status,
        LocalDate requestedDate,
        Long employeeLoginId,
        Long customerLoginId) {
}
