package com.exadatum.product;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by exa00015 on 6/11/16.
 */
public class HiveBatchExceutor extends HiveExecutor {

    public void hqlexecutor(File hql) throws IOException, SQLException {

        BufferedReader reader = new BufferedReader(new FileReader(hql));
        String currentLine;
        Statement stmt = hive_con.createStatement();

        while((currentLine = reader.readLine()) != null){
            stmt.addBatch(currentLine);
        }
        stmt.executeBatch();
    }

}
