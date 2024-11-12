import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)  throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input file name:");
        String str = scanner.nextLine();
        int [][] matrix = Matrica.ReadFile(str);
        Matrica.WriteMatrix(matrix);
        Matrica.calculateMaxNumber(matrix);
        Matrica.calculateSimilarLines(matrix);
        Matrica.calculateGrowthOfCharacteristics(matrix);
    }
}