package com.exadatum.hadoop.practice;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by jksingh on 26/1/17.
 */
public class QueryFile {

    public static void main(String args[]) throws URISyntaxException, IOException {
        Configuration conf = new Configuration();
        // conf.set("fs.default.name","hdfs:///");
        URI uri = new URI(args[0]);
        FileSystem fs = FileSystem.get(uri, conf);
        FileStatus fstat = fs.getFileStatus(new Path(URI.create(args[0])));
        System.out.println("Access Time: "+fstat.getAccessTime());
        System.out.println("Blocksize: "+fstat.getBlockSize());
        System.out.println("GROUP: "+fstat.getGroup());
        System.out.println("OWNER: "+fstat.getOwner());
        System.out.println("PATH: "+fstat.getPath());
        System.out.println("MODIFY TIME: "+fstat.getModificationTime());
        System.out.println("PERMISSION: "+fstat.getPermission());
        System.out.println("Replication: "+fstat.getReplication());
//        System.out.println(fstat.getSymlink());
        System.out.println("CHEK DIR: "+fstat.isDirectory());
        //Filesystem.listStatus() returns FileStatus class array for all the contents in directory
        //FileSystem.golbStatus() returns FileStatus class array for all the contents in directory matching the wildcard characters
    }

}
