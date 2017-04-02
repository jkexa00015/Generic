package com.exadatum.product;

import java.sql.*;

/**
 * Created by exa00015 on 6/11/16.
 */
public class TableMetadata extends HiveExecutor{


    @Override
    public ResultSet queryHive(String query) throws SQLException {
        Statement stmt = hive_con.createStatement();
        ResultSet result = stmt.executeQuery(query);
        System.out.println(result.getString(1));
        return result;
    }

    public void queryMetadataFetcher(String query) throws SQLException {
        PreparedStatement ps = hive_con.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
        ResultSetMetaData rsmd=rs.getMetaData();
        int cols = rsmd.getColumnCount();
        String colName[] = new String[cols];
        String colType[] = new String[cols];
        String catalogName[] = new String[cols];
        int colDisplaySize[] = new int[cols];
        String colLable[] = new String[cols];
        String colSchema[] = new String[cols];
        int colPrecision[] = new int[cols];
        int colScale[] = new int[cols];
        String colTable[] = new String[cols];
        for (int i=1;i<cols;i++) {
             colName[i] = rsmd.getColumnName(i);
             colType[i] = rsmd.getColumnTypeName(i);
             colDisplaySize[i] = rsmd.getColumnDisplaySize(i);
             colLable[i] = rsmd.getColumnLabel(i);
             colPrecision[i] = rsmd.getPrecision(i);
             colScale[i] = rsmd.getScale(i);
             colTable[i] = rsmd.getTableName(i);
             System.out.println(colName[i]);
             System.out.println(colType[i]);
             System.out.println(colPrecision[i]);
             System.out.println(colScale[i]);
             System.out.println(colDisplaySize[i]);
             System.out.println(colLable[i]);
        }

    }

}
