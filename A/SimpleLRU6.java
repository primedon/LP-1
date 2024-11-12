import java.util.*;

public class SimpleLRU {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of pages: ");
        int noOfPages = sc.nextInt();
        
        int[] pages = new int[noOfPages];
        System.out.println("Enter the page numbers:");
        for (int i = 0; i < noOfPages; i++) {
            pages[i] = sc.nextInt();
        }
        
        System.out.print("Enter the frame capacity: ");
        int capacity = sc.nextInt();
        
        List<Integer> frame = new ArrayList<>(capacity);
        int hit = 0, fault = 0;
        
        for (int page : pages) {
            if (frame.contains(page)) {  // Page hit
                hit++;
                frame.remove((Integer) page); // Move page to the end to mark it as recently used
                frame.add(page);
                System.out.println("Page " + page + " - Hit");
            } else {  // Page fault
                fault++;
                if (frame.size() == capacity) {
                    frame.remove(0); // Remove the least recently used page
                }
                frame.add(page);
                System.out.println("Page " + page + " - Fault");
            }
            System.out.println("Frame: " + frame);
        }
        
        double hitRatio = (double) hit / noOfPages * 100;
        double faultRatio = (double) fault / noOfPages * 100;
        
        System.out.println("\nTotal Hits: " + hit);
        System.out.println("Total Faults: " + fault);
        System.out.printf("Hit Ratio: %.2f%%\n", hitRatio);
        System.out.printf("Fault Ratio: %.2f%%\n", faultRatio);
        
        sc.close();
    }
}
