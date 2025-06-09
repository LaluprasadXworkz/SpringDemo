package com.xworkz.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("home/")
public class HomeController {

    @GetMapping("redirectToRegister")
    public String redirectToRegister() {
        System.out.println("Invoking redirectToRegister");
        return "register";
    }

}
