package com.mtv.erp.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mtv.erp.TestPlanfix.Response;
import com.mtv.erp.mybatis.daoimpl.UserDaoImpl;
import com.mtv.erp.requestPlanFix.PfDtoRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Objects;

public class TestUserDao {
    private final UserDao userDao = new UserDaoImpl();

//    @Test
//    public void testInsert() throws ServerException {
//        User user = userDao.insert(new User("mikhail", "Михаил", "Баранцев", "Сергеевич"));
//        Assertions.assertNotEquals(0, user.getId());
//    }

    @Test
    public void XmlTest() throws JSONException, IOException {
        RestTemplate restTemplate = new RestTemplate();
        String body = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<request method=\"user.getList\">\n" +
                "  <account>Aseng</account>\n" +
                "  <status>ACTIVE</status>\n" +
                "  <pageCurrent>1</pageCurrent>\n" +
                "  <pageSize>100</pageSize>\n" +
                "</request>";
        PfDtoRequest pfRequest = new PfDtoRequest("user.getList");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YTU3YmYxZTg5ZmZlZDY1ZmJhYzRkZmZkNmE2Nzg4OWM6YTUyZDk2ZDE0MTI3OGEzZTI5ZTgyZmVhMTUwZWRlMzY=");
        httpHeaders.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<String> request = new HttpEntity<>(body, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity("https://apiru.planfix.ru/xml", request, String.class);
        System.out.println(response.getBody());
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.readValue(response.getBody(), Response.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String value = objectMapper.writeValueAsString(jsonNode);
//        System.out.println(value);
    }
}
