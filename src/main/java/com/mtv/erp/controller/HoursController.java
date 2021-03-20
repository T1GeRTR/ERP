package com.mtv.erp.controller;

import com.mtv.erp.exception.ServerException;
import com.mtv.erp.response.EmptyResponse;
import com.mtv.erp.service.HoursService;
import com.mtv.erp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HoursController {

    @Autowired
    private HoursService hoursService;

    @Autowired
    private UserService userService;

    //@PostConstruct
    @RequestMapping(value = {"/hours/update"}, method = RequestMethod.GET)
    EmptyResponse update() throws ServerException {
        userService.update();
        return hoursService.update();
    }
}
