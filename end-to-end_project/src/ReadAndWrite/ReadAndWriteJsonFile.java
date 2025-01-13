package ReadAndWrite;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadAndWriteJsonFile {
    public static String ReadJsonFile (String name) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String input = objectMapper.writeValueAsString(objectMapper.readTree(new File(name)));
        JSONObject jsonObject = new JSONObject(input);
        StringBuilder txtOutput = new StringBuilder();
        for (String key : jsonObject.keySet()) {
            txtOutput.append(key).append(" ").append(jsonObject.get(key)).append("\n");
        }
        return txtOutput.toString();
    }

    public static void WriteJsonFile (String name, String str) throws IOException {
        Map<String, Double> results = new HashMap<>();
        Pattern pattern = Pattern.compile("\\b(\\d+(\\.\\d+)?)\\b");
        Matcher matcher = pattern.matcher(str);
        String[] wordsBetweenNumbers = ExtractWordsBetweenNumbers(str);
        int index = 0;
        while (matcher.find()) {
            String key = "empty" + (index + 1);
            if (index < wordsBetweenNumbers.length) {
                key = wordsBetweenNumbers[index];
            }
            Double value = Double.parseDouble(matcher.group(1)); 
            results.put(key, value);
            index++;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(new File(name), results);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] ExtractWordsBetweenNumbers(String str) {
        ArrayList<String> wordsBetweenNumbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("((?<![\\d.])[a-zA-Z0-9]+(?![\\d.]))(?:\\s*[+-/*]?\\d+\\.?\\d*\\s*)");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            wordsBetweenNumbers.add(matcher.group(1).trim());
        }
        return wordsBetweenNumbers.toArray(new String[0]);
    }

    public static String GetJsonInfo (String string) throws IOException {
        return new String(Files.readAllBytes(Paths.get(string)));
    }
}
