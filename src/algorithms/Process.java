package algorithms;

import java.util.Random;

public class Process {

    private int arrivalTime;
    private int burstTime;
    private int id;
    private int completedTime;
    private int waitingTime;
    private int turnAroundTime;

    public Process(int id, int arrivalTime, int burstTime){
        this.id=id;
        this.arrivalTime=arrivalTime;
        this.burstTime=burstTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(int completedTime) {
        this.completedTime = completedTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }


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


    @Override
    public String toString() {
        return "Process{" +
                ", id=" + id +
                ", arrivalTime=" + arrivalTime +
                ", burstTime=" + burstTime +
                '}';
    }
}
