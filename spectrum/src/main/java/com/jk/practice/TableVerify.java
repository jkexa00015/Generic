package com.jk.practice;

import static org.mockito.Mockito.*;

/**
 * Created by exa00015 on 4/11/16.
 */
public class TableVerify {

    public static void main(String args[]){
      DataProvider  dataProvider = mock(DataProvider.class);
        Table sample = new Table("KPI_FINAL",dataProvider);
        Table sample2 = new Table("KPIBACKLOG",dataProvider);
        sample.getValue();
        sample2.getValue();
     //   verify(dataProvider,times(2)).getData(anyString());
     //verify(dataProvider,never()).getData(anyString());
       verify(dataProvider,atLeastOnce()).getData(anyString());
      verify(dataProvider,atLeast(2)).getData(anyString());
     //   verify(dataProvider,atMost(3)).getData(anyString());


    }
}
