import java.util.ArrayList;
import java.util.Scanner;

public class factorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number:");
        int n = scanner.nextInt();
        while (n < 0) {
            System.out.println("Enter a correct number:");
            n = scanner.nextInt();
        }
        ArrayList<Integer> factorials = CalculateFactorials(n);
    }
    static ArrayList<Integer> CalculateFactorials(int n) {
        ArrayList<Integer> factorials = new ArrayList<>();
        factorials.add(1);//0!
        if (n == 1) {
            return factorials;
        }
        for (int i = 1; i <= n - 1; ++i) {
            factorials.add(i * factorials.get(i - 1));
        }
        return factorials;
    }
}