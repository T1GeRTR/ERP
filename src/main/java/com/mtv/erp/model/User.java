package com.mtv.erp.model;

import java.time.LocalDate;
import java.util.Objects;

public class User {
    private int id;
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    private String patronymic;
    private String number;
    private LocalDate birthDay;
    private Department department;
    private Position position;
    private UserType userType;

    public User(int id, String login, String password, String firstname, String lastname, String patronymic, String number, LocalDate birthDay, Department department, Position position, UserType userType) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.number = number;
        this.birthDay = birthDay;
        this.department = department;
        this.position = position;
        this.userType = userType;
    }
    public User(String login, String password, String firstname, String lastname, String patronymic, String number, LocalDate birthDay, Department department, Position position, UserType userType) {
        this(0, login, password, firstname, lastname, patronymic, number, birthDay, department, position, userType);
    }

    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Department getDepartament() {
        return department;
    }

    public void setDepartament(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() && Objects.equals(getLogin(), user.getLogin()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getFirstname(), user.getFirstname()) && Objects.equals(getLastname(), user.getLastname()) && Objects.equals(getPatronymic(), user.getPatronymic()) && Objects.equals(getNumber(), user.getNumber()) && Objects.equals(getBirthDay(), user.getBirthDay()) && Objects.equals(getDepartament(), user.getDepartament()) && Objects.equals(getPosition(), user.getPosition()) && getUserType() == user.getUserType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getPassword(), getFirstname(), getLastname(), getPatronymic(), getNumber(), getBirthDay(), getDepartament(), getPosition(), getUserType());
    }
}
