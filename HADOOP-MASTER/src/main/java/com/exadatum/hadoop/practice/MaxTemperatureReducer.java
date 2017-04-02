package com.exadatum.hadoop.practice;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


import java.io.IOException;

/**
 * Created by jksingh on 7/1/17.
 */
public class MaxTemperatureReducer  extends Reducer<Text,IntWritable,Text,IntWritable> {

    @Override
    public void reduce(Text key,Iterable<IntWritable> values,Context context) throws IOException, InterruptedException {

        String year = key.toString();
        Integer maxValue = Integer.MIN_VALUE;
        for(IntWritable value : values){
            maxValue = Math.max(maxValue,value.get());
        }
        context.write(key,new IntWritable(maxValue));
    }
}
