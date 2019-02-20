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
        if (!Pattern.matches("[a-zA-Z0-9]{1,}@[a-z]{1,}[.][a-z]{1,}", email)) {
            throw new BusinessException("Incorrect email!!");
        }
    }

    @Override
    public void validatePhone(String phone) throws BusinessException {

        OperatorCodes[] operatorCodes = OperatorCodes.values();
        StringBuilder listOfCodes = new StringBuilder();
        for (OperatorCodes s : operatorCodes) {
            listOfCodes.append(s.getCode() + "|");
        }
        if (listOfCodes.length() > 0) {
            listOfCodes.deleteCharAt(listOfCodes.length() - 1);
        }

        if (!Pattern.matches("(" + listOfCodes.toString() + ")\\d{7}", phone)) {
            throw new BusinessException("Incorrect phone!! e.g(0501234455) 050 - phone code");
        }
    }
}

enum OperatorCodes {
    VD1("050"), VD2("066"), VD3("095"), VD4("099"),
    KS1("039"), KS2("067"), KS3("068"), KS4("096"), KS5("097"), KS6("098"),
    LF1("063"), LF2("093"),
    ALL("044");

    private String code;

    OperatorCodes(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}