package com.xworkz.common.repository;

import com.xworkz.common.entity.RegisterEntity;

public interface CommonRepository {

    boolean saveRegisterDetails(RegisterEntity entity);

    RegisterEntity getRegisterByEmail(String email);

    RegisterEntity  getRegisterByPhoneNumber(Long mobileNumber);
    void updateOtpByEmail(String newOtp,String email);
    RegisterEntity getRegisterById(Integer id);
    boolean updateRegister(RegisterEntity entity);

}
