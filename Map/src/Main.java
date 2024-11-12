import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader("input.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        Map<String, ArrayList<StringStruct>> map = new HashMap<>();
        ArrayList<StringStruct> arrayList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (bufferedReader.ready()) {
            String str = bufferedReader.readLine();
            StringTokenizer tokenizer = new StringTokenizer(str, " ");
            StringStruct stringStruct = new StringStruct(tokenizer.nextToken(),tokenizer.nextToken(),tokenizer.nextToken());
            arrayList.add(stringStruct);
            map.putIfAbsent(arrayList.get(arrayList.size() - 1).GetCompetition(), new ArrayList<StringStruct>());
        }
        for (int i = 0; i < arrayList.size(); ++i) {
            map.get(arrayList.get(i).GetCompetition()).add(arrayList.get(i));
        }
        System.out.println("Entry key:");
        String str = scanner.nextLine();
        if (map.containsKey(str)) {
            System.out.println(map.get(str).toString());
        }
    }
}