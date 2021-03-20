package com.mtv.erp.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mtv.erp.model.User;

import java.time.LocalDate;
import java.util.Objects;

@JsonIgnoreProperties(value = "handler")
public class HoursGetUserDtoRequest {
    private int id;
    private User user;
    private LocalDate date;
    private int hours;
    private boolean saved;

    public HoursGetUserDtoRequest() {
    }

    public HoursGetUserDtoRequest(int id, User user, LocalDate date, int hours, boolean saved) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.hours = hours;
        this.saved = saved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public boolean getSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HoursGetUserDtoRequest)) return false;
        HoursGetUserDtoRequest that = (HoursGetUserDtoRequest) o;
        return getId() == that.getId() &&
                getHours() == that.getHours() &&
                getSaved() == that.getSaved() &&
                Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getDate(), getHours(), getSaved());
    }
}