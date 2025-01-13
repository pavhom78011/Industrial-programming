import Calculations.LibraryCalculation;
import Calculations.RegexCalculation;
import ReadAndWrite.ReadAndWriteJsonFile;
import ReadAndWrite.ReadAndWriteTxtFile;
import ReadAndWrite.ReadAndWriteXmlFile;
import ReadAndWrite.ReadAndWriteYamlFile;
import ReadAndWrite.encryption.DecryptionAndEncryption;
import ReadAndWrite.unzip_zip.UnzipZipFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        int a = 0, b = 0, c = 0, d = 0;
        String inputfilename = "";
        String outputfilename = "";
        String inputfiletype = "";
        String outputfiletype = "";
        String inputtext = "";
        StringBuffer resultString = new StringBuffer();
        StringTokenizer inputtokenizer = Input();
        inputfilename = inputtokenizer.nextToken();
        outputfilename = inputtokenizer.nextToken();
        a = Integer.parseInt(inputtokenizer.nextToken());
        b = Integer.parseInt(inputtokenizer.nextToken());
        c = Integer.parseInt(inputtokenizer.nextToken());
        d = Integer.parseInt(inputtokenizer.nextToken());
        inputfiletype = inputfilename.substring(inputfilename.lastIndexOf('.') + 1);
        String inputwordBeforeDot = inputfilename.substring(0, inputfilename.lastIndexOf('.')).replaceAll(".*\\b(\\w+)", "$1");
        String outputwordBeforeDot = outputfilename.substring(0, outputfilename.lastIndexOf('.')).replaceAll(".*\\b(\\w+)", "$1");
        if (a == 2) {
            UnzipZipFile.Unzip(inputwordBeforeDot);
        }
        switch (inputfiletype) {
            case ("txt"):
                inputtext = ReadAndWriteTxtFile.ReadTxtFile(inputfilename);
                break;
            case ("json"):
                inputtext = ReadAndWriteJsonFile.ReadJsonFile(inputfilename);
                break;
            case ("xml"):
                inputtext = ReadAndWriteXmlFile.ReadXmlFile(inputfilename);
                break;
            case ("yaml"):
                inputtext = ReadAndWriteYamlFile.ReadYamlFile(inputfilename);
                break;
        }
        if (d == 1) {
            RegexCalculation.RegexProcessing(inputtext, resultString);
        }
        else {
            LibraryCalculation.ProcessExpressions(inputtext, resultString);
        }
        outputfiletype = outputfilename.substring(outputfilename.lastIndexOf('.') + 1);
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
        }
        if (c == 2) {
            DecryptionAndEncryption.Encrypt(new File(outputfilename),new File(outputfilename), DecryptionAndEncryption.generateSecretKey());
        }
        if (b == 2) {
            UnzipZipFile.Zip(outputfilename, outputwordBeforeDot);
            Path path = Paths.get(outputfilename);
            Files.delete(path);
        }
        if (a == 2) {
            Path path = Paths.get(inputfilename);
            Files.delete(path);
        }
    }

    public static StringTokenizer Input() {
        int a = 0, b = 0, c = 0, d = 0;
        String inputfilename = "", outputfilename = "";
        String[] formats = {"txt", "json", "xml", "yaml"};
        Scanner consoleinput = new Scanner(System.in);
        while (!Arrays.stream(formats).anyMatch(inputfilename::contains)) {
            System.out.println("Input file name(available formats: txt, json, xml, yaml):");
            inputfilename = consoleinput.nextLine();
        }
        while (!Arrays.stream(formats).anyMatch(outputfilename::contains)) {
            System.out.println("Output file name(available formats: txt, json, xml, yaml):");
            outputfilename = consoleinput.nextLine();
        }
        while (a != 1 && a != 2) {
            System.out.println("Print 1 if input file not archived, 2 if archived:");
            a = consoleinput.nextInt();
        }
        while (b != 1 && b != 2) {
            System.out.println("Print 1 if output file not archived, 2 if archived:");
            b = consoleinput.nextInt();
        }
        while (c != 1 && c != 2) {
            System.out.println("Print 1 if output file not encrypted, 2 if encrypted:");
            c = consoleinput.nextInt();
        }
        while (d != 1 && d != 2) {
            System.out.println("select evaluate using regular expression(enter 1) or library(enter 2):");
            d = consoleinput.nextInt();
        }
        return new StringTokenizer(inputfilename + " " + outputfilename + " " + String.valueOf(a) + " " + String.valueOf(b) + " " + String.valueOf(c) + " " + String.valueOf(d));
    }
}