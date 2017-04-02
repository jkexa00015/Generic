package com.exadatum.product;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by exa00015 on 6/11/16.
 */
public class HiveMetadataFetcher extends HiveExecutor {
    public static Map<String,Object> metadatabase = new HashMap<String, Object>();
    public static DatabaseMetaData dbmd;
    public void init(){
        try {
            dbmd=hive_con.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getDatabaseInfo() throws SQLException {
        metadatabase.put("Driver Name", dbmd.getDriverName());
        metadatabase.put("Driver Version", dbmd.getDriverVersion());
        metadatabase.put("Database Product Name", dbmd.getDatabaseProductName());
        metadatabase.put("Database Product Version", dbmd.getDatabaseProductVersion());
    }

    public ArrayList<String> getALLtables() throws SQLException {
        DatabaseMetaData dbmd=hive_con.getMetaData();
        ArrayList<String> tables = new ArrayList<String>();
        String table[]={"TABLE"};
        ResultSet rs=dbmd.getTables(null,null,null,table);
        while(rs.next()){
            tables.add(rs.getString(3));
            System.out.println(rs.getString(3));
        }
        return tables;
    }




}
