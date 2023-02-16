package com.dynamic.program;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) {
        System.out.println(Instant.now().minus(10, ChronoUnit.DAYS).isBefore(Instant.now()));
        Main main = new Main();
        int result = main.leftRightSumIndex(new int[] {4, 7, 3, 8, 6});
        System.out.print(result);
    }

    /* Solution */
    public int leftRightSumIndex(int[] values) {
        if (values.length == 1) return -1;
        int totalSum = 0;
        for(int value : values){
            totalSum = totalSum + value;
        }
        int newSum = 0, index;
        for(index=0; index<values.length; index++){
            totalSum = totalSum - values[index];
            newSum = newSum + values[index];
            if(newSum == totalSum){
                return index;
            }
        }
        return -1;
    }
}
