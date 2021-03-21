package com.mtv.erp.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mtv.erp.model.User;

import java.time.LocalDate;
import java.util.Objects;

@JsonIgnoreProperties(value = "handler")
public class HoursGetUserDtoResponse {
    private int id;
    private User user;
    private LocalDate date;
    private float hours;
    private int hoursInt;
    private boolean saved;

    public HoursGetUserDtoResponse() {
        setHoursInt();
    }

    public HoursGetUserDtoResponse(int id, User user, LocalDate date, float hours, boolean saved) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.hours = hours;
        this.saved = saved;
        setHoursInt();
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

    public float getHours() {
        return hours;
    }

    public void setHours(float hours) {
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
        if (!(o instanceof HoursGetUserDtoResponse)) return false;
        HoursGetUserDtoResponse that = (HoursGetUserDtoResponse) o;
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

    public int getHoursInt() {
        return hoursInt;
    }

    private void setHoursInt() {
        this.hoursInt = (int) hours;
    }
}
