package com.mtv.erp.controller;

import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.User;
import com.mtv.erp.response.EmptyResponse;
import com.mtv.erp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/user/", produces = MediaType.APPLICATION_JSON_VALUE)
    List<User> get() throws ServerException {
        return userService.getAll();
    }

    @PostMapping(path = "/user/update")
    EmptyResponse update() throws ServerException{
        userService.update();
        return new EmptyResponse();
    }
}
