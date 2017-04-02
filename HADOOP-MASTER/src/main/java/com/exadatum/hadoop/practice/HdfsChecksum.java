package com.exadatum.hadoop.practice;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by jksingh on 8/2/17.
 */
public class HdfsChecksum extends Configured implements Tool {

        @Override
        public int run(String args[]) throws IOException {
            Configuration conf = new Configuration();
            FileSystem fs = FileSystem.get(URI.create(args[0]),conf);
            InputStream in = null;
            try {
                in = fs.open(new Path(URI.create(args[0])));
                String md5 = DigestUtils.md5Hex(in);
                writeCksumFile(args[1],md5);
            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
                IOUtils.closeStream(in);
            }
            return 0;
        }

    public static void main(String[] args) throws IOException, URISyntaxException {
        try {
            int exitcode = ToolRunner.run(new HdfsGenerateMd5(), args);
            System.exit(exitcode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeCksumFile(String ChecksumFile,String Checksumvalue)throws IOException {
        try{
            PrintWriter writer = new PrintWriter(ChecksumFile, "UTF-8");
            writer.println(Checksumvalue);

            writer.close();
        } catch (IOException e) {
            // do something
        }
    }


}
