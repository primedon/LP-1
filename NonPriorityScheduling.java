import java.util.Scanner;

public class NonPriorityScheduling {

    public static void main(String[] args) {
        
        System.out.println("*** Priority Scheduling (Non Preemptive) ***");

        Scanner sc = new Scanner(System.in);

    
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] priority = new int[n];
        int[] completionTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Process " + (i + 1));
            System.out.print("Arrival time: ");
            arrivalTime[i] = sc.nextInt();
            System.out.print("Burst time: ");
            burstTime[i] = sc.nextInt();
            System.out.print("Priority: ");
            priority[i] = sc.nextInt();
        }

        
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arrivalTime[i] > arrivalTime[j] || 
                   (arrivalTime[i] == arrivalTime[j] && priority[i] > priority[j])) {
                    
                    int temp = arrivalTime[i];
                    arrivalTime[i] = arrivalTime[j];
                    arrivalTime[j] = temp;

                    temp = burstTime[i];
                    burstTime[i] = burstTime[j];
                    burstTime[j] = temp;

                    temp = priority[i];
                    priority[i] = priority[j];
                    priority[j] = temp;
                }
            }
        }

        int currentTime = 0;
        for (int i = 0; i < n; i++) {
          
            if (currentTime < arrivalTime[i]) {
                currentTime = arrivalTime[i];
            }

           
            currentTime += burstTime[i];
            completionTime[i] = currentTime;
            turnaroundTime[i] = completionTime[i] - arrivalTime[i];
            waitingTime[i] = turnaroundTime[i] - burstTime[i];
        }

        double avgWT = 0, avgTAT = 0;
        for (int i = 0; i < n; i++) {
            avgWT += waitingTime[i];
            avgTAT += turnaroundTime[i];
        }
        avgWT /= n;
        avgTAT /= n;

        System.out.println("\nProcess\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + "\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" +
                    completionTime[i] + "\t\t" + turnaroundTime[i] + "\t\t\t" + waitingTime[i]);
        }


        System.out.println("\nAverage Waiting Time: " + avgWT);
        System.out.println("Average Turnaround Time: " + avgTAT);

        sc.close();
    }
}
