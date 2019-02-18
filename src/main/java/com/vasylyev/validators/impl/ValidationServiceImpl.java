package com.vasylyev.validators.impl;

import com.vasylyev.exceptions.BusinessException;
import com.vasylyev.validators.ValidationService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationServiceImpl implements ValidationService {

    @Override
    public void validateAge(int age) throws BusinessException {
        if (age < 0 || age > 200) {
            throw new BusinessException("Incorrect age!! Must be between 0 and 200");
        }
    }

    @Override
    public void validateEmail(String email) throws BusinessException {
        if (!Pattern.matches("[a-zA-Z0-9]{1,}[@]{1,}[a-z]{1,}[.][a-z]{1,}", email)) {
            throw new BusinessException("Incorrect email!!");
        }
    }

    @Override
    public void validatePhone(String phone) throws BusinessException {
        if (!Pattern.matches("0(50|67|97)\\d{7}", phone)) {
            throw new BusinessException("Incorrect phone!! e.g(0501234455) 050 - phone code (can be 050 or 067 or 097)");
        }
    }
}
