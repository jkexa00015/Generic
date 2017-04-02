package com.jk.practice;

/**
 * Created by exa00015 on 4/11/16.
 */
public class Table {

    public String id;
    public int value;
    public DataProvider dataProvider;

    public Table(String id,DataProvider dataProvider){
        this.id = id;
        this.dataProvider = dataProvider;
    }
    public String getId(){
        return id;
    }
    public int getValue(){
        this.value = dataProvider.getData(id);
        return value;
    }
}
