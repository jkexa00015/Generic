package com.exadatum.hadoop.practice;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * Created by jksingh on 26/1/17.
 */
public class ListContent {

    public static void main(String args[]) throws IOException {
        Configuration conf = new Configuration();
        //conf.set("fs,default.name","hdfs:///");
        FileSystem fs = FileSystem.get(URI.create(args[0]),conf);
        //FileSystem.get(URI.create(args[0]);  //static function
        //FileSystem.get(conf);
        InputStream in = null;
        FSDataInputStream fsIn = null;
        try {

            fsIn = fs.open(new Path(URI.create(args[0]))); //returns FSDataInputStream rather than java.io beacuse it provides random access implements seekable
            in = fsIn;
            IOUtils.copyBytes(in, System.out, 4096, false);
            fsIn.seek(0);
            IOUtils.copyBytes(fsIn,System.out,4096,false);
            //copyBytes(InputStream,OutputStream,Buffer Size(default  4 kb),close or not)
            //we can use read and readFully to read from a position
        }finally {
            IOUtils.closeStream(in);
        }

    }
}
