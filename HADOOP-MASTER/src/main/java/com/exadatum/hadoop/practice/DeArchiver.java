package com.exadatum.hadoop.practice;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.*;
import java.net.URI;
/**
 * Created by jksingh on 21/2/17.
 */
public class DeArchiver {

    public static  String CODEC = "org.apache.hadoop.io.compress.GzipCodec";

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        String archivePath = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(archivePath), conf);
        Class<?> codecClass = Class.forName(CODEC);
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, conf);
            InputStream cis = codec.createInputStream(fs.open(new Path(archivePath)));
            InputStream index = fs.open(new Path(archivePath.split("\\.")[0] + "_index.txt"));
            BufferedReader br = new BufferedReader(new InputStreamReader(index));
            String strLine = br.readLine();
            Long offset = 0l;
            while (strLine != null) {
                String[] indexValue = strLine.split("\\\t");
                System.out.println(strLine);
                offset = Long.parseLong(indexValue[0]);
                OutputStream out = fs.create(new Path(archivePath.split("\\.")[0] + ".dearchive/" + indexValue[1]), true);
                copyBytes(cis, out, 1024, offset);
                strLine = br.readLine();
                IOUtils.closeStream(out);
            }
    }

    public static void copyBytes(InputStream in, OutputStream out, int buffSize,Long offset)
            throws IOException {
        PrintStream ps = out instanceof PrintStream ? (PrintStream) out : null;
        byte buf[] = new byte[buffSize];
        int bytesRead = in.read(buf);
        while (offset >= 0) {
            out.write(buf, 0, bytesRead);
            offset--;
            if ((ps != null) && ps.checkError()) {
                throw new IOException("Unable to write to output stream.");
            }
            bytesRead = in.read(buf);
        }
    }
}
