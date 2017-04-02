package com.jk.practice;

/**
 * Created by exa00015 on 4/11/16.
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;


public class MathApplicationTester {
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @InjectMocks
    MathApplication mathApplication = new MathApplication();
    @Mock
    CalculatorService calcService;
    @Test
    public void testAdd() {
          when(calcService.add(10.0, 20.0)).thenReturn(40.00);
        Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0);
    }
}