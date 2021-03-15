package com.mtv.erp.responsePlanFix;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class WorkerPfDtoResponse {
    private WorkerPfDtoUsersResponse users;
    private String status;

    public WorkerPfDtoResponse(WorkerPfDtoUsersResponse users, String status) {
        this.users = users;
        this.status = status;
    }

    public WorkerPfDtoUsersResponse getUsers() {
        return users;
    }

    public void setUsers(WorkerPfDtoUsersResponse users) {
        this.users = users;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkerPfDtoResponse that = (WorkerPfDtoResponse) o;
        return Objects.equals(users, that.users) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users, status);
    }
}
