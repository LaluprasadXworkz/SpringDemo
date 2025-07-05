package com.xworkz.common.resorce;

import java.security.SecureRandom;

public class OtpGenerate {
    private static  final String OTP_CHARACTERS="0123456789";

    public static String generateOTP(){
        SecureRandom secureRandom=new SecureRandom();
        StringBuilder otp=new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            int index=secureRandom.nextInt(OTP_CHARACTERS.length());
            otp.append(OTP_CHARACTERS.charAt(index));
        }
        return otp.toString();
    }

//    private static final SecureRandom secureRandom = new SecureRandom();
//
//    public static int generateOTP() {
//        return 100000 + secureRandom.nextInt(900000);
//    }
}
