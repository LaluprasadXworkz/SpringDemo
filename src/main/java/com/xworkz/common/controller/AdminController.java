package com.xworkz.common.controller;

import com.xworkz.common.dto.RegisterDto;
import com.xworkz.common.service.CommonService;
import com.xworkz.common.util.CommonUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
    CommonService service;

    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping("admin")
    public String adminPage() {
        System.out.println("adminPage");
        return "admin";
    }

    @PostMapping("generateOtp")
    public String generateOtp(@RequestParam("email") String email, Model model) {
        System.out.println("generateOtp: " + email);
        model.addAttribute("email", email);
        service.generateOtp(email);
        return "otp";
    }

    @PostMapping("validateOtp")
    public String validateOtp(@RequestParam("email") String email, @RequestParam("otp") String otp, Model model) {
        System.out.println("Validating OTP for email: " + email);
        boolean isValid = service.validateOtp(email, otp);
        if(isValid) {
            RegisterDto registerDto = service.getRegisterByEmailId(email);
            System.out.println("registerDto :"+registerDto);
            model.addAttribute("register", registerDto);
            return "profile";
        } else {
            model.addAttribute("error", "Invalid OTP, please try again");
            model.addAttribute("email", email);
            return "otp";
        }
    }

    @GetMapping("updatePage")
    public String redirectToUpdate(@RequestParam("id")String id,Model model){
        System.out.println("updatePage :"+id);
        RegisterDto registerDto = service.getRegisterById(Integer.valueOf(id));
        model.addAttribute("register", registerDto);
        return "update";
    }

    @PostMapping("update")
    public String updateProfile(RegisterDto dto,Model model){
        System.out.println("updateProfile :"+dto);
        boolean isUpdated=service.updateRegisterById(dto);
        if(isUpdated) {
            RegisterDto registerDto = service.getRegisterById(dto.getRegisterId());
            System.out.println("registerDto :"+registerDto);
            model.addAttribute("register", registerDto);
            return "profile";
//            model.addAttribute("msg", "Updated");
//            return "success";
        }else {
            model.addAttribute("msg", "Not Updated");
            return "success";
        }
    }

    @GetMapping("/display")
    public void displayUserImage(HttpServletResponse response, @RequestParam String imagepath)throws IOException{
        File file = new File(CommonUtil.UPLOADED_FOLDER + File.separator + imagepath);
        try (InputStream in = new BufferedInputStream(new FileInputStream(file));
             ServletOutputStream out = response.getOutputStream()) {
            IOUtils.copy(in, out);
            response.flushBuffer();
        } catch (FileNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found");
        }
    }
}