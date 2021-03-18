package com.mtv.erp.controller;

import com.mtv.erp.exception.ServerException;
import com.mtv.erp.response.UserGetFromDate;
import com.mtv.erp.response.UserGetFromDateById;
import com.mtv.erp.response.UserGetAllDtoResponse;
import com.mtv.erp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/user/", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserGetAllDtoResponse> getAll() throws ServerException {
        return userService.getAll();
    }

    @GetMapping(path = "/user/{monthYear}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserGetFromDate> getFromDate(@PathVariable("monthYear") String monthYear) throws ServerException {
        return userService.getFromDate(monthYear);
    }

    @GetMapping(path = "/user/{id}/{monthYear}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserGetFromDateById getFromDateById(@PathVariable("id") int id, @PathVariable("monthYear") String monthYear) throws ServerException {
        return userService.getFromDateById(id, monthYear);
    }

    @PostMapping(path = "/user/update")
    List<UserGetAllDtoResponse> update() throws ServerException {
        return userService.update();
    }
}
