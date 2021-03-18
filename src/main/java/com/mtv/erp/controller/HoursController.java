package com.mtv.erp.controller;

import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.LaborRecord;
import com.mtv.erp.service.HoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class HoursController {

    @Autowired
    private HoursService hoursService;

    @GetMapping(path = "/hours/{monthYear}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<LaborRecord> get(@PathVariable("monthYear") String monthYear) throws ServerException {
        return hoursService.getFromDate(monthYear);
    }

    @PostMapping(path = "/hours/update")
    List<LaborRecord> update() throws ServerException {
        return hoursService.update();
    }
}
