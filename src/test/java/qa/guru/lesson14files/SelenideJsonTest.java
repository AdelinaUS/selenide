package qa.guru.lesson14files;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SelenideJsonTest {

    @Test
    void parseJsonTest() throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get("src/test/resources/files/list_packages_for_an_organization.json"));

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);;

//        Employee emp = objectMapper.readValue(jsonData, Employee.class);

        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<Package> someClassList = objectMapper.readValue(jsonData, typeFactory.constructCollectionType(List.class, Package.class));
        String t = "1";


    }

}
