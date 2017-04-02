package com.exadatum.product;
/**
 * Created by exa00015 on 6/11/16.
 */

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class HiveMetaData {
    public static String driverName = "org.apache.hive.jdbc.HiveDriver";
    public static Connection hive_con;

    public void dbconnect(String db_name) throws ClassNotFoundException, SQLException {
        Class.forName(driverName);
        hive_con = DriverManager.getConnection("jdbc:hive2://192.168.56.101:10000/"+db_name, "exa00015", "jassikaran");
    }
    public void disconnect()throws SQLException{
        hive_con.close();
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
                    System.out.println(res.getString(1) + "  ->  " + res.getString(2));
                }
        }
        return detailsmap;
        }




}