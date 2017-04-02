package com.exadatum.hadoop.practice;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.*;
import java.net.URI;
import java.util.zip.ZipOutputStream;

/**
 * Created by jksingh on 2/2/17.
 */
public class AllRounderCompressor {

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        String hdfsSource = args[0];
        String localPath = args[1];
        String codecClassname = args[2];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(hdfsSource),conf);
        Class<?> codecClass = Class.forName(codecClassname);
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, conf);

        InputStream in = null;
        OutputStream out =new BufferedOutputStream(new FileOutputStream(localPath));
        try{
            in = fs.open(new Path(args[0]));
            CompressionOutputStream cis = codec.createOutputStream(out);
            IOUtils.copyBytes(in,cis,4096,false);
        }finally{
            IOUtils.closeStream(out);
        }
    }
}
