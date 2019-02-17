package com.vasylyev.validators;

import com.vasylyev.exceptions.BusinessException;

public interface ValidationService {

    void validateAge(int age) throws BusinessException;

}
