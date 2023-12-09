package com.example.aups.models;

public class UserDto {
    private Long id;
    private String firstName;
    private String surname;
    private String email;
    private Long roleId;

    public UserDto(Long id, String firstName, String surname, String email, Long roleId) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.roleId = roleId;
    }

    public UserDto(String firstName, String surname, String email, Long roleId) {
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.roleId = roleId;
    }

    public UserDto() {

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
