import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("array dimension:");
        int n = scanner.nextInt();
        Integer[] massiv =new Integer[n];
        for (int i = 0; i < n; ++i) {
            massiv[i] = random.nextInt(1,n + 1);
        }
        System.out.println("dimension: " + n);
        System.out.println("massiv: " + Arrays.toString(massiv));
        System.out.println("array sort order ascending or descending (0 or 1): ");
        int k = scanner.nextInt();
        while (k != 0 && k != 1) {
            System.out.println("Error number. Enter 0 or 1: ");
            k = scanner.nextInt();
        }
        int finalK = k;
        Runnable sorting = new Runnable() {
            @Override
            public void run() {
                if (finalK == 0) {
                    Comparator<Integer> comparator = new Comparator<Integer>() {
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            return o2 - o1;
                        }
                    };
                    Arrays.sort(massiv, comparator);
                }
                else if (finalK == 1) {
                    Comparator<Integer> comparator = new Comparator<Integer>() {
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            return o1 -o2;
                        }
                    };
                    Arrays.sort(massiv, comparator);
                }
            }
        };
        Thread Sort = new Thread(sorting);
        Sort.start();
        Sort.join();
        System.out.println("massiv:" + Arrays.toString(massiv));
    }
}