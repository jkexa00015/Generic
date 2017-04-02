package com.exadatum.hadoop.practice;

import org.apache.pig.ExecType;
import org.apache.pig.PigServer;

import java.io.IOException;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by jksingh on 8/3/17.
 */
public class PigClient {

    private static final Log log = LogFactory.getLog(PigClient.class);

    public static void main(String ...args) throws IOException {

        Properties props = new Properties();
        props.setProperty("fs.default.name", "hdfs://127.0.0.1:8020");
        props.setProperty("mapred.job.tracker", "127.0.0.1:8021");

        PigServer pigServer = new PigServer(ExecType.MAPREDUCE, props);
        pigServer.setBatchOn();

        pigServer.registerQuery("saabji = LOAD '/incoming/cmdm/sample.txt' USING PigStorage(',') AS ( id:int, firstname:chararray, lastname:chararray);");

        //pigServer.registerQuery("Store saabji into '/output.txt' using PigStorage();");

        pigServer.dumpSchema("saabji");

        System.out.println(pigServer.openIterator("saabji"));

        pigServer.executeBatch();
        //pigServer.store("saabji","/output.txt");

    }
}
