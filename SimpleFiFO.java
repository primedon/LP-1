import java.util.Scanner;

public class SimpleFiFO {

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
        
     
        int[] frames = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            frames[i] = -1;  
        }

        int pageFaults = 0;  
        int index = 0;      

        System.out.println("\nPage replacement process (F = Fault, H = Hit):");
        for (int page : pages) {
            boolean found = false; 


            for (int frame : frames) {
                if (frame == page) {  
                    System.out.print("H ");
                    found = true;
                    break;
                }
            }
            if (!found) {
                frames[index] = page; 
                index = (index + 1) % capacity;  
                pageFaults++;  
                System.out.print("F ");  
            }
        }

        
        System.out.println("\n\nTotal Page Faults: " + pageFaults);
        System.out.println("Total Page Hits: " + (numPages - pageFaults));
        
        sc.close();  
    }
}
