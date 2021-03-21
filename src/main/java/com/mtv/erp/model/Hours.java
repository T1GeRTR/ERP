package com.mtv.erp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Hours {
    private int id;
    private User user;
    private LocalDate date;
    private float hours;
    private boolean saved;

    public Hours(int id, User user, LocalDate date, float hours, boolean saved) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.hours = hours;
        this.saved = saved;
    }

    public Hours(int id, User user, LocalDate date, float hours) {
        this(id, user, date, hours, false);
    }

    public Hours(){
    }

    public Hours(float hours, LocalDate date, User user){
        setHours(hours);
        setDate(date);
        setUser(user);
    }

    public Hours(int id, float hours){
        this(id, null, null, hours);
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

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hours)) return false;
        Hours hours1 = (Hours) o;
        return getId() == hours1.getId() &&
                getHours() == hours1.getHours() &&
                isSaved() == hours1.isSaved() &&
                Objects.equals(getUser(), hours1.getUser()) &&
                Objects.equals(getDate(), hours1.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getDate(), getHours(), isSaved());
    }
}
