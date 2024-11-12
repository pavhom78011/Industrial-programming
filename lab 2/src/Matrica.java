import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Matrica {
    public static int [][] ReadFile(String s) throws IOException {
        try {
            FileReader reader = new FileReader(s);
            BufferedReader bufferedReader = new BufferedReader(reader);
        }
        catch (IOException e) {
            System.out.println("Ошибка при чтении файла.");
            System.exit(0);
        }
        FileReader reader = new FileReader(s);
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<String> lines = new ArrayList<>();
        while(bufferedReader.ready()) {
            String str = bufferedReader.readLine();
            lines.add(str);
        }
        if (lines.size() == 0) {
            System.exit(0);
        }
        int m = lines.get(0).split(" ").length;
        int n = lines.size();
        int [][] matrix = new int [n][m];
        for (int i = 0; i < n; ++i) {
            StringTokenizer tokenizer = new StringTokenizer(lines.get(i), " ");
            for (int k = 0; k < m; ++k) {
                matrix[i][k] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        return matrix;
    }
    public static void WriteMatrix(int[][] matrix) {
        System.out.println("Matrix:");
        for (int i = 0; i < matrix.length; ++i) {
            for (int k = 0; k < matrix[0].length; ++k) {
                System.out.print(matrix[i][k] + " ");
            }
            System.out.println();
        }
    }
    public static void calculateMaxNumber(int[][] matrix) throws IOException {
        FileWriter writer = new FileWriter("calculateMaxNumber.txt", false);
        int max = 0;
        Vector<Integer> vector = new Vector<Integer>();
        for (int[] row : matrix) {
            for (int element : row) {
                vector.addElement(element);
                if(Collections.frequency(vector, element) > 1 && max < element)
                    max = element;
            }
        }
        System.out.println("Max element occurring more than once:" + max);
        writer.write("Max element occurring more than once:" + max + '\n');
        writer.flush();
    }
    public static void calculateSimilarLines(int[][] matrix) throws IOException{
        FileWriter writer = new FileWriter("calculateSimilarLines.txt", false);
        Vector<Set<Integer>> vector = new Vector<Set<Integer>>();
        Vector<Boolean> strings = new Vector<Boolean>();
        for (int i = 0; i < matrix.length; ++i) {
            vector.add(new HashSet<>());
        }
        for (int i = 0; i < matrix.length; ++i) {
            for (int k= 0; k < matrix[0].length; ++k) {
                vector.get(i).add(matrix[i][k]);
            }
        }
        int ind = 1;
        boolean a = true;
        for (Set<Integer> vec : vector) {
            for (int i = ind; i < vector.size(); ++i) {
                if (vec.equals(vector.get(i)) && a) {
                    strings.add(false);
                    a = false;
                }
            }
            if (a) {
                strings.add(true);
            }
            ++ind;
            a = true;
        }
        System.out.println("number of strings in the maximum set of pairwise dissimilar strings: " + Collections.frequency(strings, true));
        writer.write("number of strings in the maximum set of pairwise dissimilar strings: " + Collections.frequency(strings, true) + '\n');
        for (int i = 0; i < matrix.length; ++i) {
            if (strings.get(i)) {
                for (int k = 0; k < matrix[0].length; ++k) {
                    System.out.print(matrix[i][k] + " ");
                    writer.write(matrix[i][k] + " ");
                }
                System.out.println();
                writer.write('\n');
            }
        }
        writer.flush();
    }
    public static void calculateGrowthOfCharacteristics(int[][] matrix) throws IOException {
        FileWriter writer = new FileWriter("calculateGrowthOfCharacteristics.txt", false);
        Vector<Integer> vector = new Vector<Integer>();
        int line = matrix.length;
        int column = matrix[0].length;
        int sum = 0;
        for (int i = 0; i < line; ++i) {
            for (int k = 0; k < column; ++k) {
                if ((matrix[i][k] > 0) && (matrix[i][k] % 2 == 0)) {
                    sum += matrix[i][k];
                }
            }
            vector.addElement(sum);
            sum = 0;
        }
        Collections.sort(vector);
        for (int g = 0; g < vector.size(); ++g) {
            for (int i = 0; i < line; ++i) {
                for (int k = 0; k < column; ++k) {
                    if ((matrix[i][k] > 0) && (matrix[i][k] % 2 == 0)) {
                        sum += matrix[i][k];
                    }
                }
                if(sum == vector.elementAt(g)) {
                    swap(matrix, i, g);
                }
                sum = 0;
            }
        }
        System.out.println("Matrix in accordance with the growth of characteristics:");
        for (int i = 0; i < matrix.length; ++i) {
            for (int k = 0; k < matrix[0].length; ++k) {
                System.out.print(matrix[i][k] + " ");
            }
            System.out.println();
        }
        writer.write("Matrix in accordance with the growth of characteristics:" + '\n');
        for (int i = 0; i < matrix.length; ++i) {
            for (int k = 0; k < matrix[0].length; ++k) {
                writer.write(matrix[i][k] + " ");
            }
            writer.write('\n');
        }
        writer.flush();
    }
    public static void swap (int [][] matrix, int line1, int line2) {
        int [] tmp = matrix[line1];
        matrix[line1] = matrix[line2];
        matrix[line2] = tmp;
    }
}
