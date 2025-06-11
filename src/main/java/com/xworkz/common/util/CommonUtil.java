package com.xworkz.common.util;

import com.xworkz.common.dto.RegisterDto;
import com.xworkz.common.entity.RegisterEntity;
import org.springframework.beans.BeanUtils;

public class CommonUtil {

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
