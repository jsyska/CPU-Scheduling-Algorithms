package sample;

public class SJF {

    private static float averageWaitingTime=0,averageTurnAroundTime=0;

    static void calculateWaitingTime(Process[] proccesses) {
        int n = proccesses.length;
        int at[] = new int[n]; // at means arrival time
        int bt[] = new int[n]; // bt means burst time
        int ct[] = new int[n]; // ct means complete time
        int ta[] = new int[n]; // ta means turn around time
        int wt[] = new int[n];  //wt means waiting time
        int f[] = new int[n];  // f means it is flag it checks process is completed or not
        int st = 0;
        int tot = 0; // total number of processes

        for(int i = 0; i< n; i++){
            at[i]=proccesses[i].getArrivalTime();
            bt[i]=proccesses[i].getBurstTime();
            f[i]=0;
        }

        boolean a = true;
        while(true)
        {
            int c=n, min=999;
            if (tot == n) // total no of process = completed process loop will be terminated
                break;

            for (int i=0; i<n; i++)
            {
                /*
                 * If i'th process arrival time <= system time and its flag=0 and burst<min
                 * That process will be executed first
                 */
                if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min))
                {
                    min=bt[i];
                    c=i;
                }
            }

            /* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
            if (c==n)
                st++;
            else
            {
                ct[c]=st+bt[c];
                st+=bt[c];
                ta[c]=ct[c]-at[c];
                wt[c]=ta[c]-bt[c];
                f[c]=1;
                tot++;
            }
        }

        for(int i=0;i<n;i++) {
            averageWaitingTime += wt[i];
            averageTurnAroundTime += ta[i];
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
