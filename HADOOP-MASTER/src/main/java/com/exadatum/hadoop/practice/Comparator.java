package com.exadatum.hadoop.practice;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by jksingh on 20/2/17.
 */
public class Comparator {

    public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length != 2) {
            System.err.println("Need two input and one output paths");
            System.exit(-1);
        }
        Job job = new Job();
        job.setJarByClass(Comparator.class);
        job.setJobName("Comparator");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.setMapperClass(ComparatorSourceMapper.class);
        job.setReducerClass(ComparatorReducer.class);
        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(Text.class);
        System.out.println("Starting job");
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
