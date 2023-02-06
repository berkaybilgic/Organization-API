package com.example.organization.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CommonUtilsTest {
    @Test
    public void shouldFormatNormalizedString(){
        String input = "Hello, World!";
        String expectedOutput = "helloworld";
        String actualOutput = CommonUtils.normalizedString(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldUnFormatNormalizedString(){
        String input = "helloworld";
        String expectedOutput = "helloworld";
        String actualOutput = CommonUtils.normalizedString(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldValidIsValidEmail(){
        String validEmail = "example@domain.com";
        String invalidEmail = "example-domain.com";
        assertTrue(CommonUtils.isValidEmail(validEmail));
        assertFalse(CommonUtils.isValidEmail(invalidEmail));
    }

    @Test
    public void shouldInValidIsValidEmail(){
        String validEmail = "example@domain.com";
        String invalidEmail = "example@domain.com";
        assertTrue(CommonUtils.isValidEmail(validEmail));
        assertTrue(CommonUtils.isValidEmail(invalidEmail));
    }
}