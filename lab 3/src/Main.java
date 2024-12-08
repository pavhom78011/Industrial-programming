import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {
    public static void main(String[] args) throws IOException {
        String str = "";
        String answer = "";
        int k = 0;
        String characters;

        FileReader reader = new FileReader("hello.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        Scanner scanner = new Scanner(System.in);

        while (bufferedReader.ready()) {
            str += bufferedReader.readLine() + " ";
        }

        System.out.println(str);
        System.out.println("Введите k:");
        k = scanner.nextInt();
        System.out.println("Введите символы:");
        characters = scanner.next();

        StringTokenizer tokenizer = new StringTokenizer(str, ",.!? ");
        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken();
            if (word.length() >= k) {
                word = word.substring(0, k - 1) + word.substring(k, word.length()) + " ";
                String something = word.substring(0, k - 1) + characters + word.substring(k - 1, word.length()) + " ";
                answer += something;
            }
            else
            {
                answer += word + " ";
            }
        }
        System.out.println(answer);

        StringTokenizer nexttokenizer = new StringTokenizer(str, ",.!?  ");
        int number = 0;
        Vector<Character> list = new Vector<Character>();
        list.addElement('a');
        list.addElement('e');
        list.addElement('y');
        list.addElement('u');
        list.addElement('i');
        list.addElement('o');
        while (nexttokenizer.hasMoreTokens()) {
            String word = nexttokenizer.nextToken();
            if (list.contains(word.charAt(0)) && list.contains(word.charAt(word.length() - 1))) {
                ++number;
            }
        }
        System.out.println("Количество слов начинающихся и заканчивающихся гласной буквой: " + number);
    }
}