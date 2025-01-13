package ReadAndWrite.tests;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import ReadAndWrite.unzip_zip.UnzipZipFile;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ZipUnzipTest {
    @Test void zip_unzip() throws IOException {
        String originalFileName = "input.txt";
        String zipFileName = "archive";
        Files.write(Paths.get(originalFileName), "This is a test".getBytes());
        UnzipZipFile.Zip(originalFileName, zipFileName);
        UnzipZipFile.Unzip(zipFileName);
        File unzippedFile = new File(originalFileName);
        assertTrue(unzippedFile.exists());
        assertTrue(compareFiles(originalFileName, unzippedFile.getAbsolutePath()));
    }
    private boolean compareFiles(String filePath1, String filePath2) throws IOException {
        byte[] file1 = Files.readAllBytes(Paths.get(filePath1));
        byte[] file2 = Files.readAllBytes(Paths.get(filePath2));
        return java.util.Arrays.equals(file1, file2);
    }
}
