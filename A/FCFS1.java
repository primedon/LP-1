import java.util.Scanner;

public class FCFS {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] completionTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] waitingTime = new int[n];
        
        // Input arrival and burst times
        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time for process " + (i + 1) + ": ");
            arrivalTime[i] = sc.nextInt();
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            burstTime[i] = sc.nextInt();
        }
        
        // Calculate completion time
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                completionTime[i] = arrivalTime[i] + burstTime[i];
            } else {
                completionTime[i] = Math.max(completionTime[i - 1], arrivalTime[i]) + burstTime[i];
            }
        }
        
        float totalTurnaround = 0, totalWaiting = 0;
        
        System.out.println("\nProcess\tArrival\tBurst\tCompletion\tTurnaround\tWaiting");
        
        // Calculate turnaround time and waiting time, and display results
        for (int i = 0; i < n; i++) {
            turnaroundTime[i] = completionTime[i] - arrivalTime[i];
            waitingTime[i] = turnaroundTime[i] - burstTime[i];
            
            totalTurnaround += turnaroundTime[i];
            totalWaiting += waitingTime[i];
            
            System.out.println("P" + (i + 1) + "\t" + arrivalTime[i] + "\t" + burstTime[i] + "\t" + completionTime[i] +
                               "\t\t" + turnaroundTime[i] + "\t\t" + waitingTime[i]);
        }
        
        System.out.println("\nAverage Turnaround Time: " + (totalTurnaround / n));
        System.out.println("Average Waiting Time: " + (totalWaiting / n));
        
        sc.close();
    }
}
