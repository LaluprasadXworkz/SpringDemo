package com.xworkz.common.controller;

import com.xworkz.common.dto.LoginDto;
import com.xworkz.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    CommonService service;

    @GetMapping
    public String redirectToLogin() {
        System.out.println("Invoking redirectToLogin");
        return "login";
    }

    @PostMapping
    public String loginMethod(LoginDto loginDto, Model model) {
        System.out.println("Invoking loginMethod " + loginDto);
        boolean isLogin = service.getRegisterByEmailId(loginDto);
        if (isLogin) {
            model.addAttribute("msg", "Login Success");
            return "success";
        }
        model.addAttribute("msg", "Login Failed ");
        return "success";
    }


}
