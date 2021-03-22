package sample;

import java.util.Random;

public class Scheduling {

    public static Process[] generateProcesses(int n, int maxArrivalTime, int maxBurstTime) {
        Process[] result = new Process[n];
        for (int i = 0; i < n; i++) {
            result[i]= new Process(i,getRandomNumberInRange(0,maxArrivalTime),getRandomNumberInRange(1,maxBurstTime));
        }
        return result;
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }



}
