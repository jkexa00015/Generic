package com.exadatum.hadoop.practice;

/**
 * Created by jksingh on 20/2/17.
 */

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ComparatorSourceMapper extends Mapper<LongWritable, Text, LongWritable, Text> {

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();
        try {
            System.out.println("Data preparation");
            System.out.println(key);
            context.write(key, new Text(line));
        }catch (IndexOutOfBoundsException e){
            context.write(null, null);
        }

    }
}

