package com.jk.practice;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by exa00015 on 4/11/16.
 */
public class TableTester2 {

    public static void main(String args[]){
      DataProvider  dataProvider = mock(DataProvider.class);
        Table sample = new Table("KPI_FINAL",dataProvider);
        when(dataProvider.getData("KPI_FINAL")).thenReturn(9).thenReturn(6);
        System.out.println(sample.getValue());
        System.out.println(sample.getValue());
        System.out.println(sample.getValue());


    }
}
