package com.xworkz.common.service;

import com.xworkz.common.dto.LoginDto;
import com.xworkz.common.dto.RegisterDto;
import com.xworkz.common.entity.RegisterEntity;
import com.xworkz.common.repository.CommonRepository;
import com.xworkz.common.util.CommonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements  CommonService {
    @Autowired
    CommonRepository repository;


    @Override
    public String saveRegisterDto(RegisterDto dto) {
        System.out.println("Invoking saveRegisterDto");
        String isCheck=null;
        if(dto!=null){
            RegisterEntity emailEntity=repository.getRegisterByEmail(dto.getEmail());
            if(emailEntity==null) {
                boolean isSaved = repository.saveRegisterDetails(CommonUtil.convertDtoToEntity(dto));
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
}
