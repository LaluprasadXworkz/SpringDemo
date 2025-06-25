package com.xworkz.common.service;

import com.xworkz.common.dto.LoginDto;
import com.xworkz.common.dto.RegisterDto;
import com.xworkz.common.entity.RegisterEntity;

public interface CommonService {
    String saveRegisterDto(RegisterDto dto);
    boolean getRegisterByEmailId(LoginDto dto);
    RegisterDto getRegisterByEmailId(String email);
    String validateRegisterByPoneNumber(Long mobile);
}
