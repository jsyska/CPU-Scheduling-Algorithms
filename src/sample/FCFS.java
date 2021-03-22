package sample;

public class FCFS {
    private static float averageWaitingTime=0,averageTurnAroundTime=0;

    static void calculateWaitingTime(Process[] proccesses){
        int n = proccesses.length;
        int ar[] = new int[n];     // arrival times
        int bt[] = new int[n];     // burst times
        int ct[] = new int[n];     // completion times
        int ta[] = new int[n];     // turn around times
        int wt[] = new int[n];     // waiting times
        int temp;

        for(int i = 0; i< n; i++){
            ar[i]=proccesses[i].getArrivalTime();
            bt[i]=proccesses[i].getBurstTime();
        }

        //sort according to arrival time
        for(int i = 0 ; i <n; i++)
        {
            for(int  j=0;  j < n-(i+1) ; j++)
            {
                if( ar[j] > ar[j+1] )
                {
                    temp = ar[j];
                    ar[j] = ar[j+1];
                    ar[j+1] = temp;
                    temp = bt[j];
                    bt[j] = bt[j+1];
                    bt[j+1] = temp;

                }
            }
        }

        for(int  i = 0 ; i < n; i++)
        {
            if( i == 0)
            {
                ct[i] = ar[i] + bt[i];
            }
            else
            {
                if( ar[i] > ct[i-1])
                {
                    ct[i] = ar[i] + bt[i];
                }
                else
                    ct[i] = ct[i-1] + bt[i];
            }
            ta[i] = ct[i] - ar[i] ;          // turnaround time= completion time- arrival time
            wt[i] = ta[i] - bt[i] ;          // waiting time= turnaround time- burst time
            averageWaitingTime += wt[i];               // total waiting time
            averageTurnAroundTime += ta[i] ;               // total turnaround time
        }
        averageTurnAroundTime /= proccesses.length;
        averageWaitingTime /=proccesses.length;

    }

    public static float getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public static float getAverageTurnAroundTime() {
        return averageTurnAroundTime;
    }
}
