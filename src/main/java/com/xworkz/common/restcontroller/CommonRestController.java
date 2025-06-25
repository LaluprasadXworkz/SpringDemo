package com.xworkz.common.restcontroller;


import com.xworkz.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/common/")
public class CommonRestController {

    @Autowired
    CommonService service;

    @GetMapping("checkMobileNumber/{phoneNumber}")
    public String checkPhoneNumber(@PathVariable Long phoneNumber){
        System.out.println("Invoking checkPhoneNumber : "+phoneNumber);
        return service.validateRegisterByPoneNumber(phoneNumber);
    }


}
