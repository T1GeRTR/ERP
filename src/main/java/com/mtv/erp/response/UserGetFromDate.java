package com.mtv.erp.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class UserGetFromDate {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private List<GetUserLaborRecord> hours;
    private GetUserLaborRecord[] monthHours;

    public UserGetFromDate(int id, String firstname, String lastname, String email, List<GetUserLaborRecord> hours, int lenMonth) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.hours = hours;
        setMonthHours(lenMonth);
    }

    public UserGetFromDate(String firstname, String lastname, int lenMonth) {
        this(0, firstname, lastname, "", new ArrayList<>(), lenMonth);
    }

    public UserGetFromDate(int id, String firstname, String lastname, String email, int lenMonth) {
        this(id, firstname, lastname, email, new ArrayList<>(), lenMonth);
    }

    public UserGetFromDate() {
    }

    private void setMonthHours(int lenMonth) {
        monthHours = new GetUserLaborRecord[lenMonth];
        for (int i = 0; i < monthHours.length; i++) {
            monthHours[i] = new GetUserLaborRecord(0);
        }
        if (hours.size() == 0) {
            return;
        }
        for (GetUserLaborRecord laborRecord : hours) {
            int index = laborRecord.getDate().getDayOfMonth() - 1;
            if (monthHours[index].getHours() == 0) {
                monthHours[index] = laborRecord;
            } else {
                GetUserLaborRecord record = monthHours[index];
                record.setHours(record.getHours() + laborRecord.getHours());
                record.setProjectId(record.getProjectId() + ", " + laborRecord.getProjectId());
                record.setProjectTitle(record.getProjectTitle() + ", " + laborRecord.getProjectTitle());
                record.setTaskId(record.getTaskId() + ", " + laborRecord.getTaskId());
                record.setTaskTitle(record.getTaskTitle() + ", " + laborRecord.getTaskTitle());
            }
        }
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

    public List<GetUserLaborRecord> getHours() {
        return hours;
    }

    public void setHours(List<GetUserLaborRecord> hours) {
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

    public GetUserLaborRecord[] getMonthHours() {
        return monthHours;
    }
}
