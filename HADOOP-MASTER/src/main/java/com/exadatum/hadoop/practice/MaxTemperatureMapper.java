package com.exadatum.hadoop.practice;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by jksingh on 7/1/17.
 */
public class MaxTemperatureMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();
        try {
            String[] s = line.split(",");
            String year = s[0];
            Integer temp = Integer.parseInt(s[1]);
            context.write(new Text(year), new IntWritable(temp));
        }catch (IndexOutOfBoundsException e){
            context.write(null, null);
        }

    }
}
