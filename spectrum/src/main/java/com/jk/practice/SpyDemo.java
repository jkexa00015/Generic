package com.jk.practice;

import java.util.LinkedList;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * Created by exa00015 on 4/11/16.
 */
public class SpyDemo {

    public static void main(String args[]){
        LinkedList<String> jasoos = spy(new LinkedList<String>());
        jasoos.add("mauja hi mauja");
        jasoos.add("Rab meher kri");
        doReturn("Jk jasoos").when(jasoos).get(0);
        System.out.println(jasoos.get(0));

    }
}
