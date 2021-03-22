package sample;

public class Rotational {

    public static void findWaitingTime(Process[] processes, int waitingTime[], int quantum, int[] turnAroundTime){
        int n = processes.length;
        int remainingBurstTime[] = new int[n];
        int completedTime[] = new int[n];
        for (int i =0; i<n;i++)
            remainingBurstTime[i] = processes[i].getBurstTime();

        int currentTime=0;
        int counter=0;
        int completed=0;

        while(true){
            boolean done = true;

            if(counter == processes.length - completed)
                currentTime++;
            counter = 0;

            for(int i =0;i<n;i++){
                if(remainingBurstTime[i]>0){
                    done = false;
                    //check if process has already arrived
                    if(currentTime<processes[i].getArrivalTime()){
                        counter++;
                        continue;
                    }

                    if(remainingBurstTime[i]>quantum){
                        currentTime +=remainingBurstTime[i];
                        remainingBurstTime[i] -= quantum;
                    } else{
                        currentTime += remainingBurstTime[i];
                        waitingTime[i] = currentTime - processes[i].getBurstTime() - processes[i].getArrivalTime();

                        completedTime[i] = currentTime;

                        remainingBurstTime[i]=0;
                        completed++;
                    }
                }
            }
            if(done == true)
                break;
        }

        for(int i=0;i<processes.length;i++){
            turnAroundTime[i]= completedTime[i]-processes[i].getArrivalTime();
        }
    }

    public static double findAvgWaitingTime(Process[] processes, int quantum){
        int n = processes.length;
        int waitingTime[] = new int[n];
        int[] turnAroundTime = new int[n];
        int totalWaitingTime = 0;

        findWaitingTime(processes,waitingTime, quantum, turnAroundTime);

        for(int i = 0; i<n; i++ ){
            totalWaitingTime += waitingTime[i];
        }

        return (double)totalWaitingTime/(double)n;
    }

    public static double findAvgTurnaAroundTime(Process[] processes, int quantum){
        int n = processes.length;
        int waitingTime[] = new int[n];
        int[] turnAroundTime = new int[n];

        int totalTurnAroundTime = 0;

        findWaitingTime(processes,waitingTime, quantum, turnAroundTime);

        for(int i = 0; i<n; i++ ){
            totalTurnAroundTime += turnAroundTime[i];
        }

        return (double)totalTurnAroundTime/(double)n;
    }

//    private int calculateTimeQuantum(Process[] processes){
//        int max = processes[0].getBurstTime();
//        int min = processes[0].getArrivalTime();
//        int totalBT = processes[0].getBurstTime();
//        for(int i=1; i<processes.length; i++){
//            totalBT += processes[i].getBurstTime();
//            if(max<processes[i]){
//                max = this.processList.get(i).getBurstTime();
//            }
//            else if(min>this.processList.get(i).getBurstTime()){
//                min = this.processList.get(i).getBurstTime();
//            }
//        }
//
//        double c = (max-min)/2;
//        double z = (totalBT)/5;
//        int C = (int)(Math.ceil(c));
//        int Z = (int)(Math.ceil(z));
//
//        if (C<=Z && C!=0){
//            return C;
//        }
//        else{
//            return Z;
//        }
//    }

}
