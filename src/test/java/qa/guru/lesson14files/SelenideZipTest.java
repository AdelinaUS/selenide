package qa.guru.lesson14files;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SelenideZipTest {

    @Test
    void selenideZipTest() throws IOException {
        String file1 = "src/test/resources/files/sample.pdf";
        String file2 = "src/test/resources/files/file_example_XLSX_5000.xlsx";
        String file3 = "src/test/resources/files/SampleCSVFile_53000kb.csv";
        final List<String> srcFiles = Arrays.asList(file1, file2, file3);

        final FileOutputStream fos = new FileOutputStream(Paths.get(file1).getParent().toAbsolutePath() + "/compressed.zip");

        ZipOutputStream zipOut = new ZipOutputStream(fos);

        for (String srcFile : srcFiles) {
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

        // @todo
    }
}
