package Swing;

import Calculations.LibraryCalculation;
import Calculations.RegexCalculation;
import ReadAndWrite.ReadAndWriteJsonFile;
import ReadAndWrite.ReadAndWriteTxtFile;
import ReadAndWrite.ReadAndWriteXmlFile;
import ReadAndWrite.ReadAndWriteYamlFile;
import ReadAndWrite.encryption.DecryptionAndEncryption;
import ReadAndWrite.unzip_zip.UnzipZipFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataProcessing {
    static String inputfilename = "";
    static String outputfilename = "";
    static String inputfiletype = "";
    static String outputfiletype = "";
    static String inputtext = "";
    static String inputInfo = "";
    static String inputwordBeforeDot = "";
    static String outputwordBeforeDot = "";
    static StringBuffer resultString = new StringBuffer();
    static boolean matches = true;

    public static void Working() throws Exception {
        matches = true;
        resultString.setLength(0);
        inputfilename = MainMenu.GetInputFileName();
        outputfilename = MainMenu.GetOutputFileName();
        inputfiletype = inputfilename.substring(inputfilename.lastIndexOf('.') + 1);
        outputfiletype = outputfilename.substring(outputfilename.lastIndexOf('.') + 1);
        inputwordBeforeDot = inputfilename.substring(0, inputfilename.lastIndexOf('.')).replaceAll(".*\\b(\\w+)", "$1");
        outputwordBeforeDot = outputfilename.substring(0, outputfilename.lastIndexOf('.')).replaceAll(".*\\b(\\w+)", "$1");
        if (MainMenu.InputArchivedStatus()) {
            try {
                UnzipZipFile.Unzip(inputwordBeforeDot);
            }
            catch (Exception e) {
                throw e;
            }
        }
        switch (inputfiletype) {
            case ("txt"):
                inputtext = ReadAndWriteTxtFile.ReadTxtFile(inputfilename);
                inputInfo = inputtext;
                break;
            case ("json"):
                inputtext = ReadAndWriteJsonFile.ReadJsonFile(inputfilename);
                inputInfo = ReadAndWriteJsonFile.GetJsonInfo(inputfilename);
                break;
            case ("xml"):
                inputtext = ReadAndWriteXmlFile.ReadXmlFile(inputfilename);
                inputInfo = ReadAndWriteXmlFile.GetXmlInfo(inputfilename);
                break;
            case ("yaml"):
                inputtext = ReadAndWriteYamlFile.ReadYamlFile(inputfilename);
                inputInfo = ReadAndWriteYamlFile.GetYamlInfo(inputfilename);
                break;
            default:
                matches = false;
                break;
        }
        if (MainMenu.RegexStatus()) {
            RegexCalculation.RegexProcessing(inputtext, resultString);
        }
        else {
            LibraryCalculation.ProcessExpressions(inputtext, resultString);
        }
        switch (outputfiletype) {
            case ("txt"):
                ReadAndWriteTxtFile.WriteTxtFile(outputfilename, resultString.toString());
                break;
            case("json"):
                ReadAndWriteJsonFile.WriteJsonFile(outputfilename, resultString.toString());
                break;
            case("xml"):
                ReadAndWriteXmlFile.WriteXmlFile(outputfilename, resultString.toString());
                break;
            case("yaml"):
                ReadAndWriteYamlFile.WriteYamlFile(outputfilename, resultString.toString());
                break;
            default:
                matches = false;
                break;
        }
        if (MainMenu.OutputEncryptedStatus()) {
            DecryptionAndEncryption.Encrypt(new File(outputfilename),new File(outputfilename), DecryptionAndEncryption.generateSecretKey());
        }
        switch (outputfiletype) {
            case ("txt"):
                resultString = new StringBuffer(ReadAndWriteTxtFile.ReadTxtFile(outputfilename));
                break;
            case("json"):
                resultString = new StringBuffer(ReadAndWriteJsonFile.GetJsonInfo(outputfilename));
                break;
            case("xml"):
                resultString = new StringBuffer(ReadAndWriteXmlFile.GetXmlInfo(outputfilename));
                break;
            case("yaml"):
                resultString = new StringBuffer(ReadAndWriteYamlFile.GetYamlInfo(outputfilename));
                break;
        }
        if (MainMenu.OutputArchivedStatus()) {
            UnzipZipFile.Zip(outputfilename, outputwordBeforeDot);
            Path path = Paths.get(outputfilename);
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (MainMenu.InputArchivedStatus()) {
            Path path = Paths.get(inputfilename);
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String GetInputText () {
        return inputInfo;
    }

    public static String GetOutputText () {
        return resultString.toString();
    }

    public static boolean GetMatches () {
        return matches;
    }

    public static void SetMatches (boolean match) {
        matches = match;
    }
}