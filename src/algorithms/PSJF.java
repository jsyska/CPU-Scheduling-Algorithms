package algorithms;

public class PSJF {


    private static float averageWaitingTime=0,averageTurnAroundTime=0;

    public static void calculateWaitingTime(Process[] proccesses) {
        int n = proccesses.length;
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] completionTime = new int[n];
        int[] turnAroundTime = new int[n];
        int[] waitingTime = new int[n];
        int[] flag = new int[n];  //checks if process is completed or not
        int[] copiedBurstTime = new int[n];   // it is also stores brust time
        int systemTime = 0;
        int totalNumberOfProcesses = 0;

        for(int i = 0; i< n; i++){
            arrivalTime[i]=proccesses[i].getArrivalTime();
            burstTime[i]=proccesses[i].getBurstTime();
            flag[i]=0;
            copiedBurstTime[i]=burstTime[i];
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
                burstTime[c]--;
                systemTime++;
                if (burstTime[c]==0)
                {
                    completionTime[c]= systemTime;
                    flag[c]=1;
                    totalNumberOfProcesses++;
                }
            }
        }

        for(int i=0;i<n;i++) {
            turnAroundTime[i] = completionTime[i] - arrivalTime[i];
            waitingTime[i] = turnAroundTime[i] - copiedBurstTime[i];
            averageWaitingTime+= waitingTime[i];
            averageTurnAroundTime+= turnAroundTime[i];
        }
        averageWaitingTime /= n;
        averageTurnAroundTime /=n;

    }
    public static float getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public static float getAverageTurnAroundTime() {

        return averageTurnAroundTime;
    }
}

