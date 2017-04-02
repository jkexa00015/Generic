package com.exadatum.product;
/**
 * Created by exa00015 on 6/11/16.
 */

import java.sql.*;

public class HiveExecutor {
    public static String driverName = "org.apache.hive.jdbc.HiveDriver";
    public static Connection hive_con;
    public String hiveHostName = "127.0.0.1";
    public String hiveForwardedPort = "2200";

    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName(driverName);
        hive_con = DriverManager.getConnection("jdbc:hive2://127.0.0.1:10000/db_gold", "exa00015", "jassikaran");
    }
    public void disconnect()throws SQLException{
        hive_con.close();
    }
    public ResultSet queryHive(String query) throws SQLException {
        Statement stmt = hive_con.createStatement();
        ResultSet result = stmt.executeQuery(query);
        return result;
    }
}