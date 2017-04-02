package com.jk.practice;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by exa00015 on 4/11/16.
 */
public class TableTester4 {

    public static void main(String args[]){
      DataProvider  dataProvider = mock(DataProvider.class);
        Table sample = new Table("KPI_FINAL",dataProvider);
        Table sample2 = new Table("KPIBACKLOG",dataProvider);
        when(dataProvider.getData(isA(String.class))).thenReturn(5);
        System.out.println(sample.getValue());
        System.out.println(sample2.getValue());

    }
}
