package com.exadatum.product;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by exa00015 on 15/11/16.
 */
public class Table{

    public String tablename;
    public String owner;
    public String createtime;
    public String lastaccesstime;
    public String protectmode;
    public String retention;
    public String location;
    public String tabletype;
    public String serdelibrary;
    public String inputformat;
    public String outputformat;
    public String columncount;
    public String compression;
    public String numberofbuckets;
    public String bucketcolumns;
    public String sortcolumns;
    public String databasename;
    public ArrayList<Column> columnset;
    public Table(String name){
        try {
            this.tablename = name;
            Metadata meta = new Metadata();
            Map<String,String> metadata = meta.getTableMeta(name);
            this.databasename = metadata.get("Database:");
            this.owner = metadata.get("Owner:");
            this.createtime = metadata.get("CreateTime:");
            this.lastaccesstime = metadata.get("LastAccessTime:");
            this.protectmode = metadata.get("Protect Mode:");
            this.retention = metadata.get("Retention:");
            this.location = metadata.get("Location:");
            this.tabletype = metadata.get("Table Type:");
            this.serdelibrary = metadata.get("SerDe Library:");
            this.inputformat = metadata.get("InputFormat:");
            this.outputformat = metadata.get("OutputFormat:");
            this.compression = metadata.get("Compressed:");
            this.numberofbuckets = metadata.get("Num Buckets:");
            this.bucketcolumns = metadata.get("Bucket Columns:");
            this.sortcolumns = metadata.get("Sort Columns:");
            this.columnset = meta.getColumnsMetadata(name);
        }catch (Exception e){
            e.printStackTrace();
        }



    }

}
