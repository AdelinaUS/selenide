package quru.qa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Compress {

    public static File zipFiles(String zipFileName, List<String> addToZip) throws IOException {
        File tempFile = File.createTempFile(zipFileName, ".zip");

        final FileOutputStream fos = new FileOutputStream(tempFile);

        ZipOutputStream zipOut = new ZipOutputStream(fos);

        for (String srcFile : addToZip) {
            File fileToZip = new File(srcFile);
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) > 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }

        zipOut.close();
        fos.close();

        return tempFile;
    }
}
