package com.mtv.erp.response;

import com.mtv.erp.model.LaborRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class UserGetFromDate {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private List<LaborRecord> hours;

    public UserGetFromDate(int id, String firstname, String lastname, String email, List<LaborRecord> hours) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.hours = hours;
    }

    public UserGetFromDate(String firstname, String lastname) {
        this(0, firstname, lastname, "", new ArrayList<>());
    }

    public UserGetFromDate(int id, String firstname, String lastname, String email) {
        this(id, firstname, lastname, email, new ArrayList<>());
    }

    public UserGetFromDate() {
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
        if (!(o instanceof UserGetFromDate)) return false;
        UserGetFromDate user = (UserGetFromDate) o;
        return getId() == user.getId() &&
                Objects.equals(getFirstname(), user.getFirstname()) &&
                Objects.equals(getLastname(), user.getLastname()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getHours(), user.getHours());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstname(), getLastname(), getEmail(), getHours());
    }
}
