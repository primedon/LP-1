import java.util.Scanner;

public class NonSJF {
  public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter the number of process :");
    int n=sc.nextInt();

    int[]arrival=new int[n],
    burst=new int[n],
    completion=new int[n],
    TAT=new int[n],
    waiting=new int[n];
    float avgTAT=0,totalWT=0;

    for(int i=0;i<n;i++){
      System.out.println("Arrival time for Process" + (i+1) + ":");
      arrival[i]=sc.nextInt();
      System.out.println("Burst time for Process " + (i+1) + ":");
      burst[i]=sc.nextInt();
    }
   
    for(int i=0;i<n;i++){
      for(int j=i+1;j<n;j++){
        if(arrival[i]>arrival[j]||(arrival[i]==arrival[j]&& burst[i]>burst[j])){
          int temp=arrival[i];
          arrival[i]=arrival[j];
          arrival[j]=temp;

          temp=burst[i];
          burst[i]=burst[j];
          burst[j]=temp;
        }
      }
    }

int currentTime=0;
for(int i =0;i<n;i++){
  currentTime=Math.max(currentTime, arrival[i]) + burst[i];
  completion[i]=currentTime;
  TAT[i]=completion[i]-arrival[i];
  waiting[i]=completion[i]-burst[i];

  avgTAT+= TAT[i];
  totalWT+= waiting[i];


}
System.out.println("\nP\tarrival\tburst\tcompletion\tTAT\twaiting");
for(int i=0;i<n;i++){
  System.out.println("P" +(i+1) + "\t" +arrival[i]+ "\t" + burst[i]+ "\t" + completion[i] +
  "\t" + TAT[i] + "\t" + waiting[i]);
}
System.out.println("Avg TAT time :" + (avgTAT/n));
System.out.println("Avg waiting time :" + (totalWT/n));
}
}

