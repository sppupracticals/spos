package algorithms;
import java.util.Scanner;

class Fcfs {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = s.nextInt();

        int[] burstTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnAroundTime = new int[n];

        System.out.println("\nEnter Burst Time for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process[" + (i + 1) + "]: ");
            burstTime[i] = s.nextInt();
        }

        waitingTime[0] = 0;
        for (int i = 1; i < n; i++) {
            waitingTime[i] = waitingTime[i - 1] + burstTime[i - 1];
        }

        int totalWaitingTime = 0, totalTurnAroundTime = 0;
        System.out.println("\nProcess\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            turnAroundTime[i] = waitingTime[i] + burstTime[i];
            totalWaitingTime += waitingTime[i];
            totalTurnAroundTime += turnAroundTime[i];

            System.out.println("P" + (i + 1) + "\t\t" + burstTime[i] + "\t\t" + waitingTime[i] + "\t\t" + turnAroundTime[i]);
        }

        float avgWaitingTime = (float) totalWaitingTime / n;
        float avgTurnAroundTime = (float) totalTurnAroundTime / n;

        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnAroundTime);
    }
}
