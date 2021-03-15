package com.mtv.erp.responsePlanFix;


import com.mtv.erp.model.User;

import java.util.List;
import java.util.Objects;

public class WorkerPfDtoUsersResponse {
    private List<User> users;
    private int totalCount;
    private int count;

    public WorkerPfDtoUsersResponse(List<User> users, int totalCount, int count) {
        this.users = users;
        this.totalCount = totalCount;
        this.count = count;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkerPfDtoUsersResponse that = (WorkerPfDtoUsersResponse) o;
        return totalCount == that.totalCount && count == that.count && Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users, totalCount, count);
    }
}
