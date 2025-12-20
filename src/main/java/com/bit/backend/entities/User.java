package com.bit.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "app_user")
public class User {

    public User() {
    }

    public User(Long id, String firstName, String lastName, String login, String email, String password, String role, String status, LocalDate requestedDate, Long employeeLoginId, Long customerLoginId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
        this.requestedDate = requestedDate;
        this.employeeLoginId = employeeLoginId;
        this.customerLoginId = customerLoginId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String login;

    @Column(name = "email")
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "status")
    private String status;

    @Column(name = "requestedDate")
    private LocalDate requestedDate;

    @Column(name = "employeeLoginId")
    private Long employeeLoginId;

    @Column(name = "customerLoginId")
    private Long customerLoginId;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
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
