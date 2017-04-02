package com.exadatum.product;

/**
 * Created by jksingh on 28/2/17.
 */
import com.google.protobuf.ServiceException;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.ipc.*;
import org.apache.hadoop.ipc.Server;

import java.io.IOException;
import java.sql.Connection;
import java.util.*;
import java.util.regex.Pattern;

public class HbaseMetaData {
    private HBaseAdmin hBaseAdmin;
    private Configuration hBaseConfiguration;

    public HbaseMetaData() throws IOException, ServiceException {
        this.hBaseConfiguration = HBaseConfiguration.create();
        Configuration config = HBaseConfiguration.create();
        hBaseConfiguration.set("hbase.zookeeper.quorum", "127.0.0.1");
        hBaseConfiguration.set("hbase.master", "127.0.0.1:60000");
        hBaseConfiguration.set("hbase.zookeeper.property.clientPort", "2181");
        hBaseConfiguration.set("zookeeper.znode.parent", "/hbase-unsecure");
        HBaseAdmin.checkHBaseAvailable(hBaseConfiguration);
        this.hBaseAdmin = new HBaseAdmin(hBaseConfiguration);
    }

    public List<String> getTableNames() throws IOException {
        //Pattern pattern = Pattern.compile(regex);
        List<String> tableList = new ArrayList<String>();
        TableName[] tableNames = hBaseAdmin.listTableNames();
        for (TableName tableName : tableNames) {
            //if (pattern.matcher(tableName.toString()).find()) {
                tableList.add(tableName.toString());
            //}
        }
        return tableList;
    }

    public Set<String> getColumns(String hbaseTable) throws IOException {
        return getColumns(hbaseTable, 10000);
    }

    public Set<String> getColumns(String hbaseTable, int limitScan) throws IOException {
        Set<String> columnList = new TreeSet<String>();
        HTable hTable = new HTable(hBaseConfiguration, hbaseTable);
        Scan scan = new Scan();
        scan.setFilter(new PageFilter(limitScan));
        ResultScanner results = hTable.getScanner(scan);
        for (Result result : results) {
            for (Cell keyValue : result.listCells()) {
                columnList.add(
                        new String(keyValue.getFamilyArray()) + ":" +
                                new String(keyValue.getValueArray())
                );
            }
        }
        return columnList;
    }
}
