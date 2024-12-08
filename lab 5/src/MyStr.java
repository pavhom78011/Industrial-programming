import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

public class MyStr {
    String company = "";
    Vector<String> presents = new Vector<>();
    Vector<Integer> costs = new Vector<>();
    static ArrayList<MyStr> ReadFile () throws IOException {
        ArrayList<MyStr> list = new ArrayList<>();
        FileReader fileReader = new FileReader("input.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while (bufferedReader.ready()) {
            MyStr a = new MyStr();
            String[] words = bufferedReader.readLine().split("\\s+");
            a.company = words[0];
            for (int i = 1; i < words.length / 2 + 1; ++i) {
                a.presents.add(words[i]);
                a.costs.add(Integer.parseInt(words[words.length / 2 + i]));
            }
            list.add(a);
        }
        return list;
    }
    static int ReadConcertCost () throws IOException {
        FileReader fileReader = new FileReader("concert_cost.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine());
        tokenizer.nextToken();
        tokenizer.nextToken();
        return Integer.parseInt(tokenizer.nextToken());
    }
    static void Write (double sum) throws IOException {
        FileWriter fileWriter = new FileWriter("output.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("Cost: " + String.valueOf(sum));
        bufferedWriter.flush();
    }
}