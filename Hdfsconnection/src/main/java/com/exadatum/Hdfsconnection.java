package com.exadatum;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class Hdfsconnection {
    public static void main(String[] args) throws IOException, URISyntaxException
    {
        Configuration conf = new Configuration();
        conf.set("fs.default.name","hdfs://sandbox.hortonworks.com:8020");
        URI uri = new URI("hdfs://sandbox.hortonworks.com:8020/user/root/practice/example.txt");
        FileSystem fileSystem = FileSystem.get(uri, conf);
        InputStream inputStream = null;

        //FileStatus[] fileStatus = fileSystem.listStatus(new Path("hdfs://sandbox.hortonworks.com:8020//user/wc"));
       //Path[] paths = FileUtil.stat2Paths(fileStatus);
        //System.out.println("***** Contents of the Directory *****");
        //for(Path path : paths)
        //{
          //  System.out.println(path);
        //}
        try{
            //5. Prepare the Path, i.e similar to File class in Java, Path represents file in HDFS
            Path path = new Path(uri);
            //6. Open a Input Stream to read the data from HDFS
            inputStream = fileSystem.open(path);
            //7. Use the IOUtils to flush the data from the file to console
            IOUtils.copyBytes(inputStream, System.out, 4096, false);
        }finally{
            //8. Close the InputStream once the data is read
            IOUtils.closeStream(inputStream);
        }
    }
}