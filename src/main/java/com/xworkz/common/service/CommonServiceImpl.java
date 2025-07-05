package com.xworkz.common.service;

import com.xworkz.common.dto.LoginDto;
import com.xworkz.common.dto.RegisterDto;
import com.xworkz.common.entity.RegisterEntity;
import com.xworkz.common.repository.CommonRepository;
import com.xworkz.common.resorce.OtpGenerate;
import com.xworkz.common.util.CommonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Transactional
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonRepository repository;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public String saveRegisterDto(RegisterDto dto) {
        System.out.println("Invoking saveRegisterDto");
        String status = "Register Data is Null";

        if(dto != null) {
            RegisterEntity emailEntity = repository.getRegisterByEmail(dto.getEmail());

            if(emailEntity == null) {
                RegisterEntity registerEntity = new RegisterEntity();
                String encodedPsw = passwordEncoder.encode(dto.getPsw());
                System.out.println("Encoded Password: " + encodedPsw);

                BeanUtils.copyProperties(dto, registerEntity);
                registerEntity.setPsw(encodedPsw);

                boolean isSaved = repository.saveRegisterDetails(registerEntity);
                status = isSaved ? "Registered" : "Not Registered";
            } else {
                status = "Email already Registered";
            }
        }
        return status;
    }

    @Override
    public boolean getRegisterByEmailId(LoginDto dto) {
        System.out.println("Invoking getRegisterByEmailId");
        boolean isLogin = false;

        if(dto != null) {
            RegisterEntity registerEntity = repository.getRegisterByEmail(dto.getEmail());

            if(registerEntity != null) {
                isLogin = passwordEncoder.matches(dto.getPassword(), registerEntity.getPsw());
            }
        }
        return isLogin;
    }

    @Override
    public RegisterDto getRegisterByEmailId(String email) {
        System.out.println("Invoking getRegisterByEmailId: " + email);
        RegisterDto dto = null;

        if(email != null && !email.isEmpty()) {
            RegisterEntity entity = repository.getRegisterByEmail(email);
            System.out.println("Entity from DB: " + entity);

            if(entity != null) {
                dto = CommonUtil.convertEntityToDto(entity);
                System.out.println("Converted DTO: " + dto);
            }
        }
        return dto;
    }

    @Override
    public String validateRegisterByPoneNumber(Long mobile) {
        System.out.println("Invoking validateRegisterByPoneNumber");
        String status = "Invalid mobile number";

        if(mobile != null && mobile != 0L) {
            RegisterEntity entity = repository.getRegisterByPhoneNumber(mobile);
            status = (entity == null) ? "Phone number not exist" : "Phone number already exist";
        }
        return status;
    }

    @Override
    public String generateOtp(String email) {
        System.out.println("Generating OTP for email: " + email);
        String newOtp = OtpGenerate.generateOTP();
        System.out.println("Generated OTP: " + newOtp);

        // Send OTP via email
        emailSender.mailSend(email, newOtp);

        // Update OTP in database
        repository.updateOtpByEmail(newOtp, email);

        return "OTP generated and sent successfully";
    }

    @Override
    public boolean validateOtp(String email, String otp) {
        System.out.println("Validating OTP for email: " + email);
        boolean isValid = false;

        if(email != null && otp != null) {
            RegisterEntity registerEntity = repository.getRegisterByEmail(email);

            if(registerEntity != null) {
                isValid = otp.equals(registerEntity.getOtp());
            }
        }
        return isValid;
    }

    @Override
    public RegisterDto getRegisterById(Integer id) {
        System.out.println("Getting register by ID: " + id);
        RegisterDto registerDto = null;

        if(id != null) {
            RegisterEntity entity = repository.getRegisterById(id);
            if(entity != null) {
                registerDto = CommonUtil.convertEntityToDto(entity);
            }
        }
        return registerDto;
    }

    @Override
    public boolean updateRegisterById(RegisterDto dto) {
        System.out.println("Updating register with ID: " + dto);
        boolean isUpdated = false;

        if(dto != null) {
            try {
                MultipartFile file = dto.getFile();
                if(file != null && !file.isEmpty()) {
                    byte[] bytes = file.getBytes();
                    Path path = Paths.get(CommonUtil.UPLOADED_FOLDER + file.getOriginalFilename());
                    Files.write(path, bytes);

                    System.out.println("File saved: " + file.getOriginalFilename());
                }

                RegisterEntity entity = new RegisterEntity();
                BeanUtils.copyProperties(dto, entity);

                if(file != null) {
                    entity.setImageName(file.getOriginalFilename());
                }

                repository.updateRegister(entity);
                isUpdated = true;

            } catch (IOException e) {
                System.err.println("Error saving file: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return isUpdated;
    }
}