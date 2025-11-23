package com.bit.backend.dtos;

import lombok.Builder;

@Builder
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String token;
    private String role;
    private Long employeeLoginId;
    private Long customerLoginId;

    public UserDto() {
    }

    public UserDto(Long id, String firstName, String lastName, String login, String token, String role, Long employeeLoginId, Long customerLoginId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.token = token;
        this.role = role;
        this.employeeLoginId = employeeLoginId;
        this.customerLoginId = customerLoginId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getEmployeeLoginId() {
        return employeeLoginId;
    }

    public void setEmployeeLoginId(Long employeeLoginId) {
        this.employeeLoginId = employeeLoginId;
    }

    public Long getCustomerLoginId() {
        return customerLoginId;
    }

    public void setCustomerLoginId(Long customerLoginId) {
        this.customerLoginId = customerLoginId;
    }
}
