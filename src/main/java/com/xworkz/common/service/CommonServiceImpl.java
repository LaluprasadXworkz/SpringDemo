package com.xworkz.common.service;

import com.xworkz.common.dto.RegisterDto;
import com.xworkz.common.entity.RegisterEntity;
import com.xworkz.common.repository.CommonRepository;
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
            RegisterEntity entity=new RegisterEntity();
            BeanUtils.copyProperties(dto,entity);
            boolean isSaved=repository.saveRegisterDetails(entity);
            if(isSaved){
                isCheck="Registered ";
            }else {
                isCheck="Not Registered ";
            }
        }else {
            isCheck="Register Data is Null";
        }
        return isCheck;
    }
}
