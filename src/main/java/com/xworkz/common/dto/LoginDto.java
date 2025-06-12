package com.xworkz.common.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data

public class LoginDto {

    private String email;
    private String password;
}
