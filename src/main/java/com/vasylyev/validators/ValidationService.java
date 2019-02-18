package com.vasylyev.validators;

import com.vasylyev.exceptions.BusinessException;

public interface ValidationService {

    void validateAge(int age) throws BusinessException;

    void validateEmail(String email) throws BusinessException;

    void validatePhone(String phone) throws BusinessException;

}
