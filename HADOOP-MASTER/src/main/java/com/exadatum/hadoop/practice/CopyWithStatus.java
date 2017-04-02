package com.exadatum.hadoop.practice;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.*;
import java.net.URI;

/**
 * Created by jksingh on 26/1/17.
 */
public class CopyWithStatus {

    public static void main(String args[]) throws IOException {
        String src = args[0];
        String dst = args[1];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        InputStream in = new BufferedInputStream(new FileInputStream(src));
        OutputStream out = null;
        try{
            out  = fs.create(new Path(URI.create(dst)), new Progressable() {
                @Override
                public void progress() {
                    System.out.print("*");
                }
            });
            //to explicitly create a directory we can call mkdir() method of filesystem class
            //IF destination folder does not exists it would create according and would not report an error
            //we can use .exists() method to check id destination exists or not
            //.appends() is used to append to a file
            IOUtils.copyBytes(in,out,4096,false);
        }finally {
            IOUtils.closeStream(out);
        }
    }
}
