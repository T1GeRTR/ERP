package com.mtv.erp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Department {
    private int id;
    private String name;
    private List<User1> users;

    public Department(int id, String name, List<User1> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public Department(){}

    public Department(int id){
        this(id, "", new ArrayList<>());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User1> getUsers() {
        return users;
    }

    public void setUsers(List<User1> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return getId() == that.getId() && Objects.equals(getName(), that.getName()) && Objects.equals(getUsers(), that.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getUsers());
    }
}
