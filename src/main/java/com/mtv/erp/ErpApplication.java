package com.mtv.erp;

import com.mtv.erp.exception.ServerException;
import com.mtv.erp.service.HoursService;
import com.mtv.erp.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin()
@SpringBootApplication
public class ErpApplication {

	public static void main(String[] args){
		SpringApplication.run(ErpApplication.class, args);
	}

}
