package com.mtv.erp.dao;

import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.Department;
import com.mtv.erp.model.Position;
import com.mtv.erp.model.User;
import com.mtv.erp.model.UserType;
import com.mtv.erp.mybatis.daoimpl.UserDaoImpl;
import com.mtv.erp.requestPlanFix.PfDtoRequest;
import com.mtv.erp.responsePlanFix.WorkerPfDtoResponse;
import com.sun.jersey.api.client.WebResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.sun.jersey.api.client.Client;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.time.LocalDate;

public class TestUserDao {
    private final UserDao userDao = new UserDaoImpl();

    @Test
    public void testInsert() throws ServerException {
        User user = userDao.insert(new User("mikhail", "Михаил", "Баранцев", "Сергеевич"));
        Assertions.assertNotEquals(0, user.getId());
    }

    @Test
    public void XmlTest(){
        Client client = Client.create();
        WebResource resource = client.resource("https://apiru.planfix.ru/xml");
        PfDtoRequest pfRequest = new PfDtoRequest("user.getList");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YTU3YmYxZTg5ZmZlZDY1ZmJhYzRkZmZkNmE2Nzg4OWM6YTUyZDk2ZDE0MTI3OGEzZTI5ZTgyZmVhMTUwZWRlMzY=");
        httpHeaders.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<PfDtoRequest> request = new HttpEntity<>(pfRequest, httpHeaders);
        WebResource.Builder builder = resource.accept(String.valueOf(MediaType.APPLICATION_XML));
        builder.header("Content-Type", "application/xml");
        builder.header("Accept", "application/xml");
        builder.header("Authorization", "Basic YTU3YmYxZTg5ZmZlZDY1ZmJhYzRkZmZkNmE2Nzg4OWM6YTUyZDk2ZDE0MTI3OGEzZTI5ZTgyZmVhMTUwZWRlMzY=");
        builder.accept(String.valueOf(MediaType.APPLICATION_XML));
        WorkerPfDtoResponse response = builder.post(WorkerPfDtoResponse.class, request);
        System.out.println(response.getStatus());
    }
}
