package com.exadatum.product;

/**
 * Created by exa00015 on 11/11/16.
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Metadata {
    public static String driverName = "org.apache.hive.jdbc.HiveDriver";
    public static Connection hive_con;
    public static DatabaseMetaData metadata = null;

    static {
        try {
            Class.forName(driverName);
            hive_con = DriverManager.getConnection("jdbc:hive2://127.0.0.1:10000/db_gold", "jksingh", "jassikaran");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            metadata = hive_con.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Map<String,Object>  printGeneralMetadata() throws SQLException {
        Map<String,Object> metadatabase = new HashMap<String,Object>();
        metadatabase.put("Driver Name", metadata.getDriverName());
        metadatabase.put("Driver Version", metadata.getDriverVersion());
        metadatabase.put("Database Product Name", metadata.getDatabaseProductName());
        metadatabase.put("Database Product Version", metadata.getDatabaseProductVersion());
        System.out.println("Database Product Name: "
                + metadata.getDatabaseProductName());
        System.out.println("Database Product Version: "
                + metadata.getDatabaseProductVersion());
        System.out.println("JDBC Driver: " + metadata.getDriverName());
        System.out.println("Driver Version: " + metadata.getDriverVersion());
        System.out.println("\n");
        return metadatabase;
    }


    public static ArrayList<String> getTables() throws SQLException {
        String table[] = { "TABLE" };
        ResultSet rs = null;
        ArrayList<String> tables = null;
        rs = metadata.getTables(null, null, null, table);
        tables = new ArrayList<String>();
        while (rs.next()) {
            tables.add(rs.getString("TABLE_NAME"));
        }
        return tables;
    }


    public  ArrayList<Column>  getColumnsMetadata(String table)
            throws SQLException {
        ArrayList<Column> metacolumn = new ArrayList<Column>();
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
            rs = metadata.getColumns(null, null,table, null);
            rsmd = rs.getMetaData();
            int i = 1;
            while (rs.next()) {
                Column data = new Column();
                data.columnname = rs.getString("COLUMN_NAME");
                data.columndatatype = rs.getString("TYPE_NAME");
                data.columnsize = rs.getString("COLUMN_SIZE");
                data.columnlabel = rsmd.getColumnLabel(i);
                data.columndisplaysize = rsmd.getColumnDisplaySize(i);
                data.columnprecision = rsmd.getPrecision(i);
                data.columnscale = rsmd.getScale(i);
                System.out.println(rs.getString("COLUMN_NAME") + " "
                        + rs.getString("TYPE_NAME") + " "
                        + rs.getString("COLUMN_SIZE") + " "
                +rsmd.getColumnLabel(i)+" "
                +rsmd.getColumnDisplaySize(i)+" "
                +rsmd.getPrecision(i)+" "
                +rsmd.getScale(i));
                metacolumn.add(data);
                i++;
            }
        return metacolumn;
    }
    public Map<String,String> getTableMeta(String tablename) throws SQLException {
        ResultSet res = null;
        Map<String,String> detailsmap = new HashMap<String, String>();
        PreparedStatement ps = hive_con.prepareStatement("DESCRIBE EXTENDED employee");
        res = ps.executeQuery("DESCRIBE FORMATTED "+tablename);
        ResultSetMetaData rsmd = res.getMetaData();
        while(res.next()){
            if(res.getString(1)!=null&&res.getString(2)!=null) {
                detailsmap.put(res.getString(1),res.getString(2));
                System.out.println(res.getString(1) + "->" + res.getString(2));
            }
        }
        return detailsmap;
    }

    public static void main(String[] args) {
        try {
            Metadata obj = new Metadata();
            obj.printGeneralMetadata();
            Map<String,String> metamap = obj.getTableMeta("gold_hct_prfl");
            System.out.println(metamap.get("Database:           "));
            obj.getColumnsMetadata("gold_hct_prfl");
            hive_con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

