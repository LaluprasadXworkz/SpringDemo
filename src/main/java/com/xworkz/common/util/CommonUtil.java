package com.xworkz.common.util;

import com.xworkz.common.dto.RegisterDto;
import com.xworkz.common.entity.RegisterEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

    public static String UPLOADED_FOLDER = "C://Users//lalup//OneDrive//Desktop//spring//image//";


    public static RegisterEntity convertDtoToEntity(RegisterDto registerDto){
        RegisterEntity registerEntity=new RegisterEntity();
        BeanUtils.copyProperties(registerDto,registerEntity);
        return  registerEntity;
    }

    public static RegisterDto convertEntityToDto(RegisterEntity entity){
        RegisterDto registerDto=new RegisterDto();
        BeanUtils.copyProperties(entity,registerDto);
        return registerDto;
    }

}
