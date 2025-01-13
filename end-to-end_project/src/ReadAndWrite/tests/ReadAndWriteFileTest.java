package ReadAndWrite.tests;

import ReadAndWrite.ReadAndWriteJsonFile;
import ReadAndWrite.ReadAndWriteXmlFile;
import ReadAndWrite.ReadAndWriteYamlFile;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ReadAndWrite.ReadAndWriteTxtFile;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

class ReadAndWriteFileTest {
    @Test
    void ReadAndWriteTxt () throws IOException {
        String str1 = "Hello World \n test1 \n";
        String str2 = "";
        ReadAndWriteTxtFile.WriteTxtFile("test1.txt", str1);
        str2 = ReadAndWriteTxtFile.ReadTxtFile("test1.txt");
        assertEquals(str1, str2);
    }

    @Test
    void ReadAndWriteJson () throws IOException {
        String str1 = "task1 7.0\ntask2 5.0\ntask3 4.5\ntask4 27.0\n";
        String str2 = "";
        ReadAndWriteJsonFile.WriteJsonFile("test2.json", str1);
        str2 = ReadAndWriteJsonFile.ReadJsonFile("test2.json");
        assertEquals(str1, str2);
    }

    @Test
    void ReadAndWriteXml () throws IOException, ParserConfigurationException, SAXException {
        String str1 = "task1 7.0\ntask2 5.0\ntask3 4.5\ntask4 27.0\n";
        String str2 = "";
        ReadAndWriteXmlFile.WriteXmlFile("test3.xml", str1);
        str2 = ReadAndWriteXmlFile.ReadXmlFile("test3.xml");
        assertEquals(str1, str2);
    }

    @Test
    void ReadAndWriteYaml () throws IOException {
        String str1 = "task1 7.0\ntask2 5.0\ntask3 4.5\ntask4 27.0\n";
        String str2 = "";
        ReadAndWriteYamlFile.WriteYamlFile("test4.yaml", str1);
        str2 = ReadAndWriteYamlFile.ReadYamlFile("test4.yaml");
        assertEquals(str1, str2);
    }
}
