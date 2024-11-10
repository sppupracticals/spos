package algorithms;
import java.util.Scanner;

public class RoundRobin {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter number of processes (MAX 10): ");
        int num = s.nextInt();

        int[] burstTime = new int[num];
        int[] remainingTime = new int[num];
        int[] waitingTime = new int[num];

        System.out.println("Enter Burst Time for each process:");
        for (int i = 0; i < num; i++) {
            System.out.print("P[" + (i + 1) + "]: ");
            burstTime[i] = s.nextInt();
            remainingTime[i] = burstTime[i]; 
            waitingTime[i] = 0;
        }

        System.out.print("\nEnter quantum: ");
        int quantum = s.nextInt();

        int remainingProcesses = num, time = 0, i = 0;
        System.out.print("0"); 

        while (remainingProcesses != 0) {
            if (remainingTime[i] > quantum) {
                remainingTime[i] -= quantum;
                time += quantum;
                System.out.print(" | P[" + (i + 1) + "] | " + time);
            } else if (remainingTime[i] > 0) {
                time += remainingTime[i];
                remainingTime[i] = 0;
                System.out.print(" | P[" + (i + 1) + "] | " + time);
                remainingProcesses--;
            }

            i = (i + 1) % num;
        }
    }
}
