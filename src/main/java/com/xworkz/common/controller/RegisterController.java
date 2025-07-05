package com.xworkz.common.controller;

import com.xworkz.common.dto.RegisterDto;
import com.xworkz.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("register")
public class RegisterController {

    @Autowired
    CommonService service;

    @PostMapping("save")
    public String saveRegistration(RegisterDto dto, Model model) {
        System.out.println("Invoking saveRegistration ");
        System.out.println("Received: " + dto);
        String message = service.saveRegisterDto(dto);
        model.addAttribute("msg", message);
        return "success";
    }
}