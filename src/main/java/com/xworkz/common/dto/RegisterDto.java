package com.xworkz.common.dto;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Data
@Component
public class RegisterDto {

    private Integer registerId;
    private String userName;
    private Long phoneNumber;
    private String email;
    private String psw;
    private String cPsw;
    private String imageName;
    MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }
}
