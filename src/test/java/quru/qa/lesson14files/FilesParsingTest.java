package quru.qa.lesson14files;

import static org.assertj.core.api.Assertions.assertThat;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import quru.qa.Compress;
import quru.qa.json.Owner;
import quru.qa.json.Package;

@DisplayName("Работа с файлами")
public class FilesParsingTest {

    ClassLoader classLoader = FilesParsingTest.class.getClassLoader();

    @DisplayName("Запаковка и проверка содержимого архива")
    @Test
    void selenideZipTest() throws Exception {
        String file1 = "src/test/resources/files/sample.pdf";
        String file2 = "src/test/resources/files/file_example_XLSX_5000.xlsx";
        String file3 = "src/test/resources/files/SampleCSVFile_53000kb.csv";
        final List<String> srcFiles = Arrays.asList(file1, file2, file3);

        File compressed = Compress.zipFiles("compressed", srcFiles);

        try (
            InputStream resource = new FileInputStream(compressed);
            ZipInputStream zis = new ZipInputStream(resource)
        ) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String extension = FilenameUtils.getExtension(entry.getName());

                switch (extension) {
                    case "xlsx":
                        XLS excel = new XLS(zis);

                        assertThat(
                            excel.excel.getSheetAt(0).getRow(4999).getCell(3).getStringCellValue())
                            .isEqualTo("Male");

                        break;
                    case "pdf":
                        PDF pdf = new PDF(zis);

                        assertThat(pdf.text).contains("a little more text");

                        break;
                    case "csv":
                        CSVReader reader = new CSVReader(new InputStreamReader(zis));
                        List<String[]> content = reader.readAll();

                        assertThat(content.get(100)[7]).isEqualTo("Storage & Organization");

                        break;
                    default:
                        throw new NotImplementedException();
                }
            }
        }
    }

    @DisplayName("Проверка структуры и содержимого JSON")
    @Test
    void parseJsonTest() throws IOException {
        try (
            InputStream resource = classLoader.getResourceAsStream(
                "files/list_packages_for_an_organization.json");
            InputStreamReader reader = new InputStreamReader(resource)
        ) {
            ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            ;

            TypeFactory typeFactory = objectMapper.getTypeFactory();
            List<Package> packages = objectMapper.readValue(reader,
                typeFactory.constructCollectionType(List.class, Package.class));

            assertThat(packages).hasSize(2);
            assertThat(packages.get(0).getName()).isEqualTo("hello_docker");
            assertThat(packages.get(0).getOwner()).isExactlyInstanceOf(Owner.class);
        }
    }
}
