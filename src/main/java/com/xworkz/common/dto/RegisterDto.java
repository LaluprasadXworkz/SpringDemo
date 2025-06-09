package com.xworkz.common.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RegisterDto {

    private Integer registerId;
    private String userName;
    private Long phoneNumber;
    private String email;
    private String psw;
    private String cPsw;

}
