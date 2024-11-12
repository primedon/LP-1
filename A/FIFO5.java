import java.util.Scanner;

public class FIFO {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of pages: ");
        int numPages = sc.nextInt();
        int[] pages = new int[numPages];

        System.out.print("Enter the page numbers: ");
        for (int i = 0; i < numPages; i++) {
            pages[i] = sc.nextInt();
        }

        System.out.print("Enter the frame capacity: ");
        int capacity = sc.nextInt();
        int[] frame = new int[capacity];
        int index = 0, hits = 0, faults = 0;

        // Initialize frames with -1 to indicate empty slots
        for (int i = 0; i < capacity; i++) {
            frame[i] = -1;
        }

        System.out.println("\nPage Replacement Process:");
        System.out.println("--------------------------------");

        for (int page : pages) {
            boolean isHit = false;

            // Check if page is already in the frame (Hit)
            for (int i = 0; i < capacity; i++) {
                if (frame[i] == page) {
                    isHit = true;
                    hits++;
                    System.out.printf("Page %d: Hit\n", page);
                    break;
                }
            }

            // If page is not in frame (Fault)
            if (!isHit) {
                frame[index] = page; // Replace page at current index
                index = (index + 1) % capacity; // Move to next index in FIFO order
                faults++;
                System.out.printf("Page %d: Fault\n", page);
            }
        }

        // Calculate and display hit ratio and fault ratio
        double hitRatio = (double) hits / numPages * 100;
        double faultRatio = (double) faults / numPages * 100;

        System.out.println("--------------------------------");
        System.out.printf("Total Hits: %d, Total Faults: %d\n", hits, faults);
        System.out.printf("Hit Ratio: %.2f%%, Fault Ratio: %.2f%%\n", hitRatio, faultRatio);

        sc.close();
    }
}
