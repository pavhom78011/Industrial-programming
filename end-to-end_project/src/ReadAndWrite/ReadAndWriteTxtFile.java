package ReadAndWrite;

import java.io.*;

public class ReadAndWriteTxtFile {
    public static String ReadTxtFile (String name) throws IOException {
        FileReader reader = new FileReader(name);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String str = "";
        while (bufferedReader.ready()) {
            str += bufferedReader.readLine() + '\n';
        }
        return str;
    }

    public static void WriteTxtFile (String name, String str) throws IOException {
        FileWriter fileWriter = new FileWriter(name);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(String.valueOf(str));
        bufferedWriter.flush();
    }
}
