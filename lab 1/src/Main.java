import java.util.Formatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        int k;
        float x;
        System.out.println("Введите k:");
        Scanner scanner = new Scanner(System.in);
        Formatter f = new Formatter();
        k = scanner.nextInt();
        System.out.println("Введите x:");
        x = scanner.nextFloat();
        Calculations calculations = new Calculations();
        f.format("%3." + k + "f", calculations.task(x,k));
        System.out.println("По ряду Тейлора:");
        System.out.println(f);
        System.out.println("С Math:");
        System.out.println(calculations.taskWithMath(x,k));
    }
}