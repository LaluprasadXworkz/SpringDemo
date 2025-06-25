package com.xworkz.common.service;

import com.xworkz.common.dto.LoginDto;
import com.xworkz.common.dto.RegisterDto;
import com.xworkz.common.entity.RegisterEntity;
import com.xworkz.common.repository.CommonRepository;
import com.xworkz.common.util.CommonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements  CommonService {
    @Autowired
    CommonRepository repository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @Override
    public String saveRegisterDto(RegisterDto dto) {
        System.out.println("Invoking saveRegisterDto");
        String isCheck=null;
        if(dto!=null){
            RegisterEntity emailEntity=repository.getRegisterByEmail(dto.getEmail());
            if(emailEntity==null) {
                RegisterEntity registerEntity=new RegisterEntity();
               String encodedPsw=passwordEncoder.encode(dto.getPsw());
                System.out.println(encodedPsw);
               BeanUtils.copyProperties(dto,registerEntity);
                registerEntity.setPsw(encodedPsw);
                boolean isSaved = repository.saveRegisterDetails(registerEntity);
                if (isSaved) {
                    isCheck = "Registered ";
                } else {
                    isCheck = "Not Registered ";
                }
            }else {
                isCheck="Email allReady Registered ";
            }
        }else {
            isCheck="Register Data is Null";
        }
        return isCheck;
    }

    @Override
    public boolean getRegisterByEmailId(LoginDto dto) {
        System.out.println("Invoking getRegisterByEmailId ");
        boolean isLogin=false;
        RegisterEntity registerEntity =repository.getRegisterByEmail(dto.getEmail());
        if(registerEntity!=null){
            if(dto.getPassword().equals(registerEntity.getPsw())){
              isLogin=true;
            }else {
                isLogin=false;
            }
        }else {
            isLogin=false;
        }
        return isLogin;
    }

    @Override
    public RegisterDto getRegisterByEmailId(String email) {
        System.out.println("Invoking getRegisterByEmailId :"+email);
        RegisterDto dto=null;
        if(email!=null){
           RegisterEntity entity= repository.getRegisterByEmail(email);
           dto=CommonUtil.convertEntityToDto(entity);
            System.out.println("Profile Data :"+dto);
        }
        return dto;
    }

    @Override
    public String validateRegisterByPoneNumber(Long mobile) {
        System.out.println("Invoking validateRegisterByPoneNumber");
        String isValidate=null;
        if(mobile!=0l){
            RegisterEntity entity=repository.getRegisterByPhoneNumber(mobile);
            if(entity==null){
                isValidate="Phone number not exist";

            }else {
                isValidate="Phone number already exist ";
            }

        }else{
            isValidate="";
        }
        return isValidate;
    }

}
