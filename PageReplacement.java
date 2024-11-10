package algorithms;
import java.util.*;

public class PageReplacement {

    public static void fifo(int[] pages, int frames) {
        Set<Integer> s = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        int pageFaults = 0;

        for (int page : pages) {
            if (!s.contains(page)) {
                if (q.size() == frames) {
                    int first = q.poll();
                    s.remove(first);
                }
                s.add(page);
                q.add(page);
                pageFaults++;
            }
        }
        System.out.println("FIFO Page Faults: " + pageFaults);
    }

    public static void lru(int[] pages, int frames) {
        Set<Integer> s = new HashSet<>();
        LinkedList<Integer> l = new LinkedList<>();
        int pageFaults = 0;

        for (int page : pages) {
            if (!s.contains(page)) {
                if (s.size() == frames) {
                    int last = l.removeLast();
                    s.remove(last);
                }
                s.add(page);
                pageFaults++;
            } else {
                l.remove((Integer) page);
            }
            l.addFirst(page);
        }
        System.out.println("LRU Page Faults: " + pageFaults);
    }

    public static void opt(int[] pages, int frames) {
        int pageFaults = 0;
        int n = pages.length;
        Set<Integer> s = new HashSet<>();
        
        for (int i = 0; i < n; i++) {
            if (s.size() < frames) {
                if (!s.contains(pages[i])) {
                    s.add(pages[i]);
                    pageFaults++;
                }
            } else {
                if (!s.contains(pages[i])) {
                    int farthest = -1, farthestIndex = -1;
                    for (int page : s) {
                        int j;
                        for (j = i + 1; j < n; j++) {
                            if (pages[j] == page) {
                                if (j > farthestIndex) {
                                    farthestIndex = j;
                                    farthest = page;
                                }
                                break;
                            }
                        }
                        if (j == n) {
                            farthest = page;
                            break;
                        }
                    }
                    s.remove(farthest);
                    s.add(pages[i]);
                    pageFaults++;
                }
            }
        }
        System.out.println("OPT Page Faults: " + pageFaults);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        System.out.print("Enter the number of pages: ");
        int n = sc.nextInt();
        int[] pages = new int[n];
        System.out.print("Enter the page reference string: ");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        System.out.println("Running FIFO Algorithm:");
        fifo(pages, frames);
        System.out.println();

        System.out.println("Running LRU Algorithm:");
        lru(pages, frames);
        System.out.println();

        System.out.println("Running OPT Algorithm:");
        opt(pages, frames);
        
        sc.close();
    }
}
