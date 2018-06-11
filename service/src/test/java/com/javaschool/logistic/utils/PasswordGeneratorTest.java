package com.javaschool.logistic.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordGeneratorTest {

    @Test
    public void getGeneratedPassword() {

        assertTrue(PasswordGenerator.getGeneratedPassword().matches("^(\\D*){3}(\\d){3}$"));

    }
}