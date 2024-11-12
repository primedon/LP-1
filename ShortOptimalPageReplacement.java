import java.util.Scanner;

public class ShortOptimalPageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of pages: ");
        int numPages = sc.nextInt();
        int[] pages = new int[numPages];
        
        System.out.println("Enter pages:");
        for (int i = 0; i < numPages; i++) pages[i] = sc.nextInt();
        
        System.out.print("Enter frame capacity: ");
        int capacity = sc.nextInt();
        int[] frames = new int[capacity];
        for (int i = 0; i < capacity; i++) frames[i] = -1;
        
        int faults = 0, hits = 0;
        System.out.println("Page replacement (F = Fault, H = Hit):");

        for (int i = 0; i < numPages; i++) {
            int page = pages[i];
            boolean isHit = false;
            for (int f : frames) if (f == page) { isHit = true; hits++; break; }
            if (isHit) { System.out.print("H "); continue; }

            faults++;
            System.out.print("F ");
            int replaceIndex = findOptimalIndex(frames, pages, i + 1);
            frames[replaceIndex] = page;
        }

        System.out.printf("\n\nTotal Faults: %d\nTotal Hits: %d\n", faults, hits);
        sc.close();
    }

    private static int findOptimalIndex(int[] frames, int[] pages, int start) {
        int farthest = start, replaceIndex = -1;
        for (int i = 0; i < frames.length; i++) {
            boolean foundLater = false;
            for (int j = start; j < pages.length; j++) {
                if (frames[i] == pages[j]) { 
                    if (j > farthest) { farthest = j; replaceIndex = i; }
                    foundLater = true;
                    break;
                }
            }
            if (!foundLater) return i;
        }
        return replaceIndex == -1 ? 0 : replaceIndex;
    }
}
