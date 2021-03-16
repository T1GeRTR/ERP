package com.mtv.erp.service;

import com.mtv.erp.dao.UserDao;
import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.User1;
import com.mtv.erp.mybatis.daoimpl.UserDaoImpl;
import com.mtv.erp.requestPlanFix.PfDtoRequest;
import com.mtv.erp.responsePlanFix.WorkerPfDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    protected RestTemplate restTemplate = new RestTemplate();

    @Autowired
    UserDao userDao = new UserDaoImpl();

    public boolean update() throws ServerException {
        final String url = "https://apiru.planfix.ru/xml";

//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//        MappingJackson2XmlHttpMessageConverter converter = new MappingJackson2XmlHttpMessageConverter();
//        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
//        messageConverters.add(converter);
//        restTemplate.setMessageConverters(messageConverters);

        PfDtoRequest pfRequest = new PfDtoRequest("user.getList");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YTU3YmYxZTg5ZmZlZDY1ZmJhYzRkZmZkNmE2Nzg4OWM6YTUyZDk2ZDE0MTI3OGEzZTI5ZTgyZmVhMTUwZWRlMzY=");
        httpHeaders.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<PfDtoRequest> request = new HttpEntity<>(pfRequest, httpHeaders);
        //List<User> usersFromPf = Objects.requireNonNull(restTemplate.exchange(url, HttpMethod.POST, request, WorkerPfDtoResponse.class).getBody()).getUsers().getUsers();
        List<User1> usersFromPf = restTemplate.postForEntity(url, request, WorkerPfDtoResponse.class).getBody().getUsers().getUsers();
        List<User1> users = userDao.getAll();
        List<User1> usersUpdate = new ArrayList<>();
        List<Integer> usersIdDelete = new ArrayList<>();
        for (int i = 0; i<users.size(); i++) {
            for (int j = 0; j < usersFromPf.size(); j++) {
                String nameFromPf = usersFromPf.get(j).getFirstname() + " " + usersFromPf.get(j).getLastname();
                String name = users.get(j).getFirstname() + " " + users.get(j).getLastname();
                if (nameFromPf.equals(name)) {
                    if (!usersFromPf.get(j).equals(users.get(i))) {
                        usersUpdate.add(usersFromPf.get(j));
                        usersFromPf.remove(j);
                        users.remove(i);
                        break;
                    }
                }
            }
        }
        for (User1 user : usersFromPf) {
            usersIdDelete.add(user.getId());
        }
        userDao.insertAll(users);
        for (User1 user : usersUpdate) {
            userDao.update(user);
        }
        for (Integer userId : usersIdDelete) {
            userDao.delete(userId);
        }
        return true;
    }
}
