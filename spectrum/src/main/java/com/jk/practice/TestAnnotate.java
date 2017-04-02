package com.jk.practice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.mockito.Mockito.when;

/**
 * Created by exa00015 on 4/11/16.
 */
public class TestAnnotate {
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    public Dictionary dictionary ;

    @Mock
    Map<String,String> wordmeaning;


    @Test
    public void Injectmock(){
        dictionary = new Dictionary("spy","jagga jasoos");
        when(wordmeaning.get("spy")).thenReturn("jk jasoos");
        Assert.assertEquals(dictionary.getMeaning("spy"),"jagga jasoos");
    }
}
