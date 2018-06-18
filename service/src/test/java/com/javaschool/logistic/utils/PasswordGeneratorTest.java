package com.javaschool.logistic.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordGeneratorTest {

    private PasswordGenerator passwordGenerator = new PasswordGenerator();

    @Test
    public void getGeneratedPassword() {

        assertTrue(passwordGenerator.getGeneratedPassword().matches("^(\\D*){3}(\\d){3}$"));

    }
}