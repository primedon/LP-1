import java.util.Scanner;
import java.util.Arrays;

public class SimpleOptimalPageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Get input for the number of pages and the pages sequence
        System.out.print("Enter the number of pages: ");
        int noOfPages = sc.nextInt();
        
        int[] pages = new int[noOfPages];
        System.out.println("Enter the page numbers:");
        for (int i = 0; i < noOfPages; i++) {
            pages[i] = sc.nextInt();
        }
        
        // Get input for frame capacity
        System.out.print("Enter the frame capacity: ");
        int capacity = sc.nextInt();
        
        int[] frame = new int[capacity];  // Array to store the pages in memory
        Arrays.fill(frame, -1);  // Initialize frame with -1 to represent empty slots
        int hit = 0, fault = 0;
        
        for (int i = 0; i < noOfPages; i++) {
            int page = pages[i];
            
            // Check if the page is already in the frame (Hit)
            boolean isHit = false;
            for (int j = 0; j < capacity; j++) {
                if (frame[j] == page) {
                    isHit = true;
                    hit++;
                    System.out.println("Page " + page + " - Hit");
                    break;
                }
            }
            
            // If page is not in frame (Fault)
            if (!isHit) {
                fault++;
                int replaceIndex = findReplacementIndex(frame, pages, i, capacity);
                frame[replaceIndex] = page;  // Replace the page in frame
                System.out.println("Page " + page + " - Fault, replaced in frame position " + replaceIndex);
            }
            
            // Display current frame status
            System.out.println("Frame: " + Arrays.toString(frame));
        }
        
        // Calculate hit and fault ratios
        double hitRatio = (double) hit / noOfPages * 100;
        double faultRatio = (double) fault / noOfPages * 100;
        
        // Display results
        System.out.println("\nTotal Hits: " + hit);
        System.out.println("Total Faults: " + fault);
        System.out.printf("Hit Ratio: %.2f%%\n", hitRatio);
        System.out.printf("Fault Ratio: %.2f%%\n", faultRatio);
        
        sc.close();
    }
    
    // Method to find the replacement index based on future use
    private static int findReplacementIndex(int[] frame, int[] pages, int currentIndex, int capacity) {
        int farthest = -1;
        int indexToReplace = 0;
        
        // Check each page in the frame to find the optimal page to replace
        for (int i = 0; i < capacity; i++) {
            int j;
            for (j = currentIndex + 1; j < pages.length; j++) {
                if (frame[i] == pages[j]) {
                    if (j > farthest) {
                        farthest = j;
                        indexToReplace = i;
                    }
                    break;
                }
            }
            if (j == pages.length) {  // If frame[i] not used in future, replace it
                return i;
            }
        }
        return indexToReplace;
    }
}
