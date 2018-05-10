package com.javaschool.logistic.utils;


import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PasswordGenerator {


    private static String LOWER="abcdefghijklmnopqrstuvwxyz";
    private static String DIGIT="0123456789";


    public static String getGeneratedPassword(){
        Random random=new Random();
        String res= String.valueOf(LOWER.charAt(random.nextInt(26)))+
                String.valueOf(LOWER.charAt(random.nextInt(26)))+
                String.valueOf(LOWER.charAt(random.nextInt(26)))+
                String.valueOf(DIGIT.charAt(random.nextInt(10)))+
                String.valueOf(DIGIT.charAt(random.nextInt(10)))+
                String.valueOf(DIGIT.charAt(random.nextInt(10)));

        return res;
    }

}
