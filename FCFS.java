import java.util.Scanner;

public class FCFS {

    public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter the number of process :");
    int n=sc.nextInt();
    
    int [] arrival=new int[n],
    bursttime=new int[n],
    waitingtime=new int[n],
    completiontime=new int[n],
    TAT=new int[n];
    float avgTAT=0;
    float totalWT=0;

     for(int i=0;i<n;i++){
        System.out.println("Arrival time for P" + (i+1) + ":");
        arrival[i]=sc.nextInt();
        System.out.println("Burst time for P" + (i+1) + ":");
        bursttime[i]=sc.nextInt();
     }
     completiontime[0]=arrival[0]+bursttime[0];
     for(int i=1;i<n;i++){
        completiontime[i]=Math.max(completiontime[i-1], arrival[i])+ bursttime[i];

     }
     for(int i=0;i<n;i++){
        TAT[i]=completiontime[i]-arrival[i];
        waitingtime[i]=TAT[i]-bursttime[i];
        avgTAT+= TAT[i];
        totalWT+= waitingtime[i];
     }
     System.out.println("\nP\tArrival\tBurst\tcompletion\tTAT\twaiting");
     for(int i=0;i<n;i++){
        System.out.println("P" + (i+1)+ "\t"+ arrival[i] + "\t" + bursttime[i] + "\t" + completiontime[i]+ "\t" +
        TAT[i]+  "\t" + waitingtime[i]);
     }

     System.out.println("Avg TAT :" + avgTAT/n);
     System.out.println("Avg waiting time :" + totalWT/n);



    }
}