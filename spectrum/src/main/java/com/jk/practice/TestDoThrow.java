package com.jk.practice;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

/**
 * Created by exa00015 on 4/11/16.
 */
public class TestDoThrow{

    public static void main(String args[]) throws IOException
    {
        OutputStream mockStream = mock(OutputStream.class);
        doThrow(new IOException()).when(mockStream).close();
        OutputStreamWriter streamWriter= new OutputStreamWriter(mockStream);
        streamWriter.close();

    }
}
