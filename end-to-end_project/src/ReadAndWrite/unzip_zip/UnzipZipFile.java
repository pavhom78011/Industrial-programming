package ReadAndWrite.unzip_zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class UnzipZipFile {
    public static void Unzip (String zipFileName) throws IOException {
        File zipFile = new File(zipFileName + ".zip");
        String destDir = zipFile.getParent();
        if (destDir == null) {
            destDir = ".";
        }
        File dir = new File(destDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(zipFileName + ".zip"); ZipInputStream zis = new ZipInputStream(fis)) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                String fileName = zipEntry.getName();
                File newFile = new File(destDir + File.separator + fileName);
                System.out.println("Unzipping to " + newFile.getAbsolutePath());
                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + newFile);
                    }
                } else {
                    File parent = newFile.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException("Failed to create directory " + parent);
                    }
                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
        } catch (IOException e) {
            throw e;
        }
    }

    public static void Zip (String fileName, String zipFileName) throws IOException {
        File fileToZip = new File(fileName);
        if (!fileToZip.exists()) {
            throw new IOException("The file " + fileName + " does not exist.");
        }
        try (FileOutputStream fos = new FileOutputStream(zipFileName + ".zip"); ZipOutputStream zos = new ZipOutputStream(fos); FileInputStream fis = new FileInputStream(fileToZip)) {
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zos.putNextEntry(zipEntry);
            byte[] buffer = new byte[1024]; int len;
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
            zos.closeEntry();
            System.out.println("File successfully zipped!");
        }
    }
}