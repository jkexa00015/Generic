package com.exadatum.product;

/**
 * Created by jksingh on 28/2/17.
 */

import com.google.protobuf.ServiceException;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class HBaseCrawler {
    public static void main(String[] args) throws ServiceException {
        try {
            HbaseMetaData hbaseMetaData  =new HbaseMetaData();
            for(String hbaseTable:hbaseMetaData.getTableNames()){
                for (String column : hbaseMetaData  .getColumns(hbaseTable, 10000)) {
                    System.out.println(hbaseTable + "," + column);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}