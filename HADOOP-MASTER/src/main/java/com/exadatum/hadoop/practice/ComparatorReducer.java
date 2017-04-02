package com.exadatum.hadoop.practice;

/**
 * Created by jksingh on 20/2/17.
 */

import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;

public class ComparatorReducer  extends Reducer<LongWritable,Text,LongWritable,Text> {

    @Override
    public void reduce(LongWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        System.out.println(key);
        String [] line = new String[2];
        try {
            int c = 0;
            for (Text value : values) {
                System.out.println(value);
                line[c] = value.toString();
                c++;
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Error in shuffle phase");
        }
            if(!(line[0].equalsIgnoreCase(line[1]))){
                System.out.println("Comparison Failed");
            }
        context.write(key,new Text("true"));
    }
}
