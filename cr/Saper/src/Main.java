import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n = 0, m = 0, w = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter field dimensions:");
        n = scanner.nextInt();
        m = scanner.nextInt();
        System.out.println("Number of bombs:");
        w = scanner.nextInt();
        int [][] matrica = new int[n][m];
        int [][] matrica2 = new int[w][2];
        for (int i = 0; i < matrica2.length; ++i) {
            System.out.println("Bomb coordinates:");
            matrica2[i][0] = scanner.nextInt();
            matrica2[i][1] = scanner.nextInt();
        }
        for (int i = 0; i < matrica.length; ++i) {
            for (int k = 0; k < matrica[0].length; ++ k) {
                matrica[i][k] = 0;
            }
        }
        for (int i = 0; i < matrica2.length; ++i) {
            matrica[matrica2[i][0] - 1][matrica2[i][1] - 1] = -1;
        }
        for (int i = 0; i < matrica.length; ++i) {
            for (int k = 0; k < matrica[0].length; ++ k) {
                if( matrica[i][k] == -1) {
                    if (k < matrica[0].length - 1 && matrica[i][k + 1] != -1) {
                        matrica[i][k + 1] += 1;
                    }
                    if (k < matrica[0].length - 1 && i < matrica.length - 1 && matrica[i + 1][k + 1] != -1) {
                        matrica[i + 1][k + 1] += 1;
                    }
                    if (i < matrica.length - 1 && matrica[i + 1][k] != -1) {
                        matrica[i + 1][k] += 1;
                    }
                    if (k > 0 && i < matrica.length - 1 && matrica[i + 1][k - 1] != -1) {
                        matrica[i + 1][k - 1] += 1;
                    }
                    if (k > 0 && matrica[i][k - 1] != -1) {
                        matrica[i][k - 1] += 1;
                    }
                    if (k > 0 && i > 0 && matrica[i - 1][k - 1] != -1) {
                        matrica[i - 1][k - 1] += 1;
                    }
                    if (i > 0 && matrica[i - 1][k] != -1) {
                        matrica[i - 1][k] += 1;
                    }
                    if (k < matrica[0].length - 1 && i > 0 && matrica[i - 1][k + 1] != -1) {
                        matrica[i - 1][k + 1] += 1;
                    }
                }
            }
        }
        for (int i = 0; i < matrica.length; ++i) {
            for (int k = 0; k < matrica[0].length; ++ k) {
                if (matrica[i][k] != -1) {
                    System.out.print(matrica[i][k] + " ");
                }
                else {
                    System.out.print("*" + " ");
                }
            }
            System.out.println();
        }
    }
}