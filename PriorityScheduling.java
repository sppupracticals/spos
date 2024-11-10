package algorithms;
import java.util.Scanner;

public class PriorityScheduling {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = s.nextInt();

        int[] process = new int[n];
        int[] burstTime = new int[n];
        int[] priority = new int[n];
        int[] waitingTime = new int[n];
        int[] turnAroundTime = new int[n];

        System.out.println("\nEnter CPU time and priority for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process[" + (i + 1) + "] - Burst Time: ");
            burstTime[i] = s.nextInt();
            System.out.print("Priority: ");
            priority[i] = s.nextInt();
            process[i] = i + 1; 
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (priority[i] < priority[j]) {
                    int temp = priority[i];
                    priority[i] = priority[j];
                    priority[j] = temp;

                    temp = burstTime[i];
                    burstTime[i] = burstTime[j];
                    burstTime[j] = temp;

                    temp = process[i];
                    process[i] = process[j];
                    process[j] = temp;
                }
            }
        }

        waitingTime[0] = 0;
        int totalWaitingTime = 0, totalTurnAroundTime = 0;
        turnAroundTime[0] = burstTime[0];
        totalTurnAroundTime += turnAroundTime[0];

        for (int i = 1; i < n; i++) {
            waitingTime[i] = turnAroundTime[i - 1];
            totalWaitingTime += waitingTime[i];

            turnAroundTime[i] = waitingTime[i] + burstTime[i];
            totalTurnAroundTime += turnAroundTime[i];
        }

        System.out.println("Process\t| Burst Time\t| Waiting Time\t| Turnaround Time\t| Priority");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + process[i] + "\t|\t " + burstTime[i] + "\t|\t " + waitingTime[i] + "\t|\t " + turnAroundTime[i] + "\t|\t " + priority[i]);
        }
        System.out.println("-----------------------------------------------------------------------");

        float averageWaitingTime = (float) totalWaitingTime / n;
        float averageTurnAroundTime = (float) totalTurnAroundTime / n;

        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnAroundTime);
    }
}
