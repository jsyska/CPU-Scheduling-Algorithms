package algorithms;

import algorithms.Process;

public class SJF {

    private static float averageWaitingTime=0,averageTurnAroundTime=0;

    public static void calculateWaitingTime(Process[] proccesses) {
        int n = proccesses.length;
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] completionTime = new int[n];
        int[] turnAroundTime = new int[n];
        int[] waitingTime = new int[n];
        int[] flag = new int[n];
        int systemTime = 0;
        int totalNumberOfProcesses = 0; // total number of processes

        for(int i = 0; i< n; i++){
            arrivalTime[i]=proccesses[i].getArrivalTime();
            burstTime[i]=proccesses[i].getBurstTime();
            flag[i]=0;
        }

        while(true)
        {
            int c=n, min=999;
            if (totalNumberOfProcesses == n) // total no of process = completed process loop will be terminated
                break;

            for (int i=0; i<n; i++)
            {
                /*
                 * If i'th process arrival time <= system time and its flag=0 and burst<min
                 * That process will be executed first
                 */
                if ((arrivalTime[i] <= systemTime) && (flag[i] == 0) && (burstTime[i]<min))
                {
                    min=burstTime[i];
                    c=i;
                }
            }

            /* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
            if (c==n)
                systemTime++;
            else
            {
                completionTime[c]=systemTime+burstTime[c];
                systemTime+=burstTime[c];
                turnAroundTime[c]=completionTime[c]-arrivalTime[c];
                waitingTime[c]=turnAroundTime[c]-burstTime[c];
                flag[c]=1;
                totalNumberOfProcesses++;
            }
        }

        for(int i=0;i<n;i++) {
            averageWaitingTime += waitingTime[i];
            averageTurnAroundTime += turnAroundTime[i];
        }
        averageWaitingTime=averageWaitingTime/proccesses.length;
        averageTurnAroundTime=averageTurnAroundTime/proccesses.length;

    }
    public static float getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public static float getAverageTurnAroundTime() {

        return averageTurnAroundTime;
    }
}

