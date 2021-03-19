package com.mtv.erp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(value = "handler")
public class User {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private List<LaborRecord> hours;

    public User(int id, String firstname, String lastname, String email, List<LaborRecord> hours) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.hours = hours;
    }

    public User(String firstname, String lastname) {
        this(0, firstname, lastname, "", new ArrayList<>());
    }

    public User(int id, String firstname, String lastname, String email) {
        this(id, firstname, lastname, email, new ArrayList<>());
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<LaborRecord> getHours() {
        return hours;
    }

    public void setHours(List<LaborRecord> hours) {
        this.hours = hours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() && Objects.equals(getFirstname(), user.getFirstname()) && Objects.equals(getLastname(), user.getLastname()) && Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstname(), getLastname(), getEmail());
    }
}
