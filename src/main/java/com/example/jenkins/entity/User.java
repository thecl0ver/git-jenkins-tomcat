package com.example.jenkins.entity;


import java.time.LocalDateTime;

public class User {

    private Long id;
    private String login;
    private String name;
    private String surname;
    private int age;
    private String email;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
    private boolean isDeleted;

    public User() {
    }

    public User(String login, String name, String surname, int age, String email) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
    }

    public User(Long id, String login, String name, String surname, int age, String email, LocalDateTime creationDate, LocalDateTime lastUpdateDate) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", creationDate=" + creationDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
