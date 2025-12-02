package com.bit.backend.dtos;

public record SignUpDto(
        String firstName,
        String lastName,
        String login,
        String email,
        char[] password,
        String role,
        String status,
        Long employeeLoginId,
        Long customerLoginId) {
}
