package com.exadatum.hadoop.practice;

/**
 * Created by jksingh on 21/2/17.
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.*;
import java.net.URI;

public class Archiver {

    public static String CODEC = "org.apache.hadoop.io.compress.GzipCodec";

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        String hdfsSource = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(hdfsSource), conf);
        Path[] paths = FileUtil.stat2Paths(fs.listStatus(new Path(hdfsSource)));
        Class<?> codecClass = Class.forName(CODEC);
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, conf);
        OutputStream out = fs.create(new Path(hdfsSource+".archive"),true);
        OutputStream index = fs.create(new Path(hdfsSource+"_index.txt"),true);

        Long offset = 0l;
        try {
            CompressionOutputStream cos = codec.createOutputStream(out);
            for (Path path : paths) {
                System.out.println(path);
                InputStream in = fs.open(path);
                offset = copyBytes(in, cos,1024);
                System.out.println(offset);
                IOUtils.copyBytes(new ByteArrayInputStream(((
                        offset.toString()+"\t"+path.getName()+"\n")).getBytes()),index,1024,true);
            }
        } finally {
            IOUtils.closeStream(out);
        }

    }

    public static Long copyBytes(InputStream in, OutputStream out, int buffSize)
            throws IOException {
        Long offset = 0l;
        PrintStream ps = out instanceof PrintStream ? (PrintStream) out : null;
        byte buf[] = new byte[buffSize];
        int bytesRead = in.read(buf);
        while (bytesRead >= 0) {
            out.write(buf, 0, bytesRead);
            offset++;
            if ((ps != null) && ps.checkError()) {
                throw new IOException("Unable to write to output stream.");
            }
            bytesRead = in.read(buf);
        }
        return offset;
    }
}

