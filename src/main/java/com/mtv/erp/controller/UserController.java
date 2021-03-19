package com.mtv.erp.controller;

import com.mtv.erp.exception.ServerException;
import com.mtv.erp.response.UserGetFromDate;
import com.mtv.erp.response.UserGetFromDateById;
import com.mtv.erp.response.UserGetAllDtoResponse;
import com.mtv.erp.service.UserService;
import com.mtv.erp.utils.MonthYearConverter;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins = "*")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public String getAll(Model model) throws ServerException {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @RequestMapping(value = {"/user/{monthYear}"}, method = RequestMethod.GET)
    String getFromDate(@PathVariable("monthYear") String monthYear, Model model) throws ServerException {
        List<Integer> daysOfMonth = new ArrayList<>();
        for (int i = 0; i < LocalDate.now().lengthOfMonth(); i++) {
            daysOfMonth.add(i + 1);
        }
        List<UserGetFromDate> list = userService.getFromDate(monthYear);
        model.addAttribute("users", list);
        model.addAttribute("days", daysOfMonth);
        model.addAttribute("month", MonthYearConverter.getMonth(monthYear));
        model.addAttribute("year", MonthYearConverter.getYear(monthYear));
        return "usersFromDate";
    }

    @RequestMapping(value = {"/user/id/{monthYear}"}, method = RequestMethod.GET)
    String getFromDateById(@PathVariable("id") int id, @PathVariable("monthYear") String monthYear, Model model) throws ServerException {
        List<Integer> daysOfMonth = new ArrayList<>();
        for (int i = 0; i < LocalDate.now().lengthOfMonth(); i++) {
            daysOfMonth.add(i + 1);
        }
        model.addAttribute("users", userService.getFromDateById(id, monthYear));
        model.addAttribute("days", daysOfMonth);
        model.addAttribute("month", MonthYearConverter.getMonth(monthYear));
        model.addAttribute("year", MonthYearConverter.getYear(monthYear));
        return "userFromDate";
    }

    @RequestMapping(value = {"/user/update"}, method = RequestMethod.GET)
    String update(Model model) throws ServerException {
        model.addAttribute("users", userService.update());
        return "users";
    }
}
