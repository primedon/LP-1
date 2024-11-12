import java.util.ArrayList;
import java.util.Scanner;

public class SimpleLRU {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

       
        System.out.print("Enter the number of pages: ");
        int numPages = sc.nextInt();
        int[] pages = new int[numPages];
        System.out.println("Enter each page number (separated by spaces):");
        for (int i = 0; i < numPages; i++) {
            pages[i] = sc.nextInt(); 
        }

        System.out.print("Enter the number of frames: ");
        int capacity = sc.nextInt();
        
       
        ArrayList<Integer> frames = new ArrayList<>(capacity);
        int pageFaults = 0, pageHits = 0;

        System.out.println("\nPage replacement process (F = Fault, H = Hit):");

        for (int page : pages) {
          
            if (frames.contains(page)) {
                pageHits++;
                System.out.print("H "); 
                frames.remove((Integer) page); 
                frames.add(page);  
            } else {
               
                pageFaults++;
                System.out.print("F ");  
               
                if (frames.size() == capacity) {
                    frames.remove(0);
                }

                frames.add(page);
            }
        }

        System.out.println("\n\nTotal Page Faults: " + pageFaults);
        System.out.println("Total Page Hits: " + pageHits);
        double hitRatio = ((double) pageHits / numPages) * 100;
        double faultRatio = ((double) pageFaults / numPages) * 100;
        System.out.printf("Hit Ratio: %.2f%%\n", hitRatio);
        System.out.printf("Fault Ratio: %.2f%%\n", faultRatio);

        sc.close();
    }
}
