import java.io.*;

public class ReadWrite {
    static void WriteFile(String s) throws IOException {
        FileWriter fileWriter = new FileWriter("output.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        fileWriter.write(s);
        fileWriter.flush();
    }
    static String ReadFile() throws IOException {
        String s = "";
        try {
            FileReader reader = new FileReader("output.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            while (bufferedReader.ready()) {
                s += bufferedReader.readLine() + '\n';
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return s;
    }
}