package com.jk.practice;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by exa00015 on 4/11/16.
 */
public class argCapturer {
    @Test
    public void testwithoutAnnotate() {
        List<String> mockedlist = mock(List.class);
        ArgumentCaptor<String> arg = ArgumentCaptor.forClass(String.class);
        mockedlist.add("Sardaarji");
        verify(mockedlist).add(arg.capture());
        Assert.assertEquals(arg.getValue(),"Sardaarji");
    }
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Mock List<String> mocklist;

    @Captor ArgumentCaptor<String> arg;

    @Test
    public void testwithAnnotate(){
        mocklist.add("jatt");
        verify(mocklist).add(arg.capture());
        Assert.assertEquals(arg.getValue(),"jatt");
    }

}







