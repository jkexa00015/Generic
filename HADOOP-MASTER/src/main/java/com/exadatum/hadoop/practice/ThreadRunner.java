package com.exadatum.hadoop.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jksingh on 16/2/17.
 */
public class ThreadRunner {

    public static void main(String args[]){

        List<Thread> threads = new ArrayList<Thread>();
        for (String arg : args) {
            Runnable task = new MD5Thread(arg);
            Thread worker = new Thread(task);
            worker.setName(String.valueOf(arg));
            worker.start();
            threads.add(worker);
        }
        int running = 0;
        do {
            running = 0;
            for (Thread thread : threads) {
                if (thread.isAlive()) {
                    running++;
                }
            }
            //System.out.println("We have " + running + " running threads. ");
        } while (running > 0);
    }
}
