package com.vasylyev.validators.impl;

import com.vasylyev.exceptions.BusinessException;
import com.vasylyev.validators.ValidationService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidationServiceImplTest {

    private ValidationService validationService;

    @Before
    public void setUp(){
        validationService = new ValidationServiceImpl();
    }

    @Test
    public void validateAge() throws BusinessException {
        //WHEN
        int age = 50;
        validationService.validateAge(age);
    }

    @Test(expected = BusinessException.class)
    public void validateWrongAge() throws BusinessException{
        //WHEN
        int age = 201;
        validationService.validateAge(age);
    }
}