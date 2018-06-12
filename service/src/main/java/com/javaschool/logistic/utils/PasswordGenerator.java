package com.javaschool.logistic.utils;


import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PasswordGenerator {


    public static String getGeneratedPassword(){
        Random random=new Random();
        String LOWER = "abcdefghijklmnopqrstuvwxyz";
        String DIGIT = "0123456789";
        return String.valueOf(LOWER.charAt(random.nextInt(26)))+
                String.valueOf(LOWER.charAt(random.nextInt(26)))+
                String.valueOf(LOWER.charAt(random.nextInt(26)))+
                String.valueOf(DIGIT.charAt(random.nextInt(10)))+
                String.valueOf(DIGIT.charAt(random.nextInt(10)))+
                String.valueOf(DIGIT.charAt(random.nextInt(10)));
    }

}
