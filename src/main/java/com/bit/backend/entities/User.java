package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
public class User {

    public User() {
    }

    public User(Long id, String firstName, String lastName, String login, String password, String role, Long employeeLoginId, Long customerLoginId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.role = role;
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

    @Column(nullable = false)
    private String password;

    @Column(name = "role")
    private String role;

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
