package ReadAndWrite;

import java.io.File;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadAndWriteXmlFile {
    public static String ReadXmlFile (String name) throws IOException, SAXException, ParserConfigurationException {
        String input = new String(Files.readAllBytes(Paths.get(name)));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(input)));
        document.getDocumentElement().normalize();
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        StringBuilder txtOutput = new StringBuilder();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                txtOutput.append(element.getTagName()).append(" ").append(element.getTextContent()).append("\n");
            }
        }
        return txtOutput.toString();
    }

    public static void WriteXmlFile (String name, String str) throws IOException {
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
        XmlMapper xmlMapper = new XmlMapper();
        try { 
            xmlMapper.writeValue(new File(name), results);
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

    public static String GetXmlInfo (String string) throws IOException {
        return new String(Files.readAllBytes(Paths.get(string)));
    }
}
