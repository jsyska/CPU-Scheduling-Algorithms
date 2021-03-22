package algorithms;

public class FCFS {
    private static float averageWaitingTime=0,averageTurnAroundTime=0;

    public static void calculateWaitingTime(Process[] proccesses){
        int n = proccesses.length;
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] completionTime = new int[n];
        int[] turnAroundTime = new int[n];
        int[] waitingTime = new int[n];
        int temp;

        for(int i = 0; i< n; i++){
            arrivalTime[i]=proccesses[i].getArrivalTime();
            burstTime[i]=proccesses[i].getBurstTime();
        }

        //sort according to arrival time
        for(int i = 0 ; i <n; i++)
        {
            for(int  j=0;  j < n-(i+1) ; j++)
            {
                if( arrivalTime[j] > arrivalTime[j+1] )
                {
                    temp = arrivalTime[j];
                    arrivalTime[j] = arrivalTime[j+1];
                    arrivalTime[j+1] = temp;
                    temp = burstTime[j];
                    burstTime[j] = burstTime[j+1];
                    burstTime[j+1] = temp;

                }
            }
        }

        for(int  i = 0 ; i < n; i++)
        {
            if( i == 0)
            {
                completionTime[i] = arrivalTime[i] + burstTime[i];
            }
            else
            {
                if( arrivalTime[i] > completionTime[i-1])
                {
                    completionTime[i] = arrivalTime[i] + burstTime[i];
                }
                else
                    completionTime[i] = completionTime[i-1] + burstTime[i];
            }
            turnAroundTime[i] = completionTime[i] - arrivalTime[i] ;
            waitingTime[i] = turnAroundTime[i] - burstTime[i] ;
            averageWaitingTime += waitingTime[i];
            averageTurnAroundTime += turnAroundTime[i] ;
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
