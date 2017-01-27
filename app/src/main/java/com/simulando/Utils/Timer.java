package com.simulando.Utils;

import java.util.concurrent.TimeUnit;

/**
 * Created by Luciano José on 09/01/2017.
 */

public class Timer {

    private static long startTime;
    private static long endTime;
    private static long elapsedTime;

    public static void start(){
        startTime = System.nanoTime();
    }

    public static void stop(){
        endTime = System.nanoTime();
    }

    public static long getElapsedTime(){
        elapsedTime = endTime - startTime;
        return TimeUnit.NANOSECONDS.toSeconds(elapsedTime);
    }

    public static void reset(){
        startTime = 0;
        endTime = 0;
        elapsedTime = 0;
    }

}
