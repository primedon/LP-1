import java.util.Scanner;

class NonPriorityScheduling {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("*** Priority Scheduling (Non Preemptive) ***");
    System.out.print("Enter Number of Processes: ");
    int n = sc.nextInt();

    int[] process = new int[n];
    int[] arrivalTime = new int[n];
    int[] burstTime = new int[n];
    int[] priority = new int[n];
    int[] completionTime = new int[n];
    int[] tat = new int[n];
    int[] waitingTime = new int[n];
    
    // Input data for each process
    for (int i = 0; i < n; i++) {
      process[i] = i + 1;
      System.out.print("Enter Arrival Time for process " + process[i] + ": ");
      arrivalTime[i] = sc.nextInt();
      System.out.print("Enter Burst Time for process " + process[i] + ": ");
      burstTime[i] = sc.nextInt();
      System.out.print("Enter Priority for process " + process[i] + ": ");
      priority[i] = sc.nextInt();
    }
    
    // Sort processes by arrival time and priority
    for (int i = 0; i < n - 1; i++) {
      for (int j = i + 1; j < n; j++) {
        if (arrivalTime[i] > arrivalTime[j] || 
           (arrivalTime[i] == arrivalTime[j] && priority[i] > priority[j])) {
          // Swap all values to maintain order
          swap(arrivalTime, i, j);
          swap(burstTime, i, j);
          swap(priority, i, j);
          swap(process, i, j);
        }
      }
    }

    int currentTime = arrivalTime[0];
    double totalTAT = 0, totalWT = 0;

    // Schedule processes
    for (int i = 0; i < n; i++) {
      if (currentTime < arrivalTime[i]) {
        currentTime = arrivalTime[i];  // wait until process arrives
      }
      currentTime += burstTime[i];
      completionTime[i] = currentTime;
      tat[i] = completionTime[i] - arrivalTime[i];
      waitingTime[i] = tat[i] - burstTime[i];
      
      totalTAT += tat[i];
      totalWT += waitingTime[i];
    }

    // Display results
    System.out.println("\nProcess\tArrival\tBurst\tPriority\tCompletion\tTAT\tWaiting");
    System.out.println("----------------------------------------------------------");
    for (int i = 0; i < n; i++) {
      System.out.println("P" + process[i] + "\t" + arrivalTime[i] + "\t" + burstTime[i] + "\t" + priority[i] 
                         + "\t\t" + completionTime[i] + "\t\t" + tat[i] + "\t" + waitingTime[i]);
    }

    System.out.println("\nAverage Waiting Time: " + (totalWT / n));
    System.out.println("Average Turn Around Time: " + (totalTAT / n));
    
    sc.close();
  }

  // Helper method to swap array elements
  private static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}
