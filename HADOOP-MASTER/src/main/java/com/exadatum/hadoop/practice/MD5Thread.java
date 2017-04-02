package com.exadatum.hadoop.practice;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import com.google.common.hash.*;

/**
 * Created by jksingh on 16/2/17.
 */
public class MD5Thread implements Runnable {

    private final int STREAM_BUFFER_LENGTH = 1024;

    private String location;

    MD5Thread(String location) {
        this.location = location;
    }

    @Override
    public void run() {
        Configuration conf = new Configuration();
        FileSystem fs = null;
        try {
            fs = FileSystem.get(URI.create(location), conf);
            System.out.println("created filesystem");
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream in = null;
        try {
            in = fs.open(new Path(URI.create(location)));
            System.out.println("will calculte md5");

            MessageDigest md = MessageDigest.getInstance("MD5");
            DigestInputStream dis = new DigestInputStream(in, md);
            byte[] digest = md.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                //   String hex=Integer.toHexString(0xff & digest[i]);
                //   if(hex.length()==1) hexString.append('0');
                //   hexString.append(hex);
                hexString.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            System.out.println("Printing");
            System.out.println(hexString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(in);
        }
    }

    private byte[] digest(MessageDigest digest, InputStream data) throws IOException {
        byte[] buffer = new byte[STREAM_BUFFER_LENGTH];
        int read = data.read(buffer, 0, STREAM_BUFFER_LENGTH);
        while (read > -1) {
            digest.update(buffer, 0, read);
            read = data.read(buffer, 0, STREAM_BUFFER_LENGTH);
        }
        return digest.digest();
    }
}