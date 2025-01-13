package ReadAndWrite;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;

public class ReadAndWriteYamlFile {
    public static String ReadYamlFile (String name) throws IOException {
        Yaml yaml = new Yaml();
        StringBuilder result = new StringBuilder();
        try (InputStream inputStream = new FileInputStream(name)) {
            Map<String, Double> data = yaml.load(inputStream);
            for (Map.Entry<String, Double> entry : data.entrySet()) {
                result.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static void WriteYamlFile (String name, String str) throws IOException {
        Map<String, Double> data = new HashMap<>();
        Pattern pattern = Pattern.compile("\\b(\\d+(\\.\\d+)?)\\b"); // "(\\d+\\.\\d+)"
        Matcher matcher = pattern.matcher(str);
        String[] wordsBetweenNumbers = ExtractWordsBetweenNumbers(str);
        int index = 0;
        while (matcher.find()) {
            String key = "empty" + (index + 1);
            if (index < wordsBetweenNumbers.length) {
                key = wordsBetweenNumbers[index];
            }
            Double value = Double.parseDouble(matcher.group(1));
            data.put(key, value);
            index++;
        }
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);
        try (FileWriter writer = new FileWriter(name)) {
            yaml.dump(data, writer);
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

    public static String GetYamlInfo (String string) throws IOException {
        return new String(Files.readAllBytes(Paths.get(string)));
    }
}
