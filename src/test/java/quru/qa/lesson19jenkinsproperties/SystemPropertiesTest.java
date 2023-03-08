package quru.qa.lesson19jenkinsproperties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("jenkins_properties")
public class SystemPropertiesTest {

    @Test
    void simpleProperty() {
        String browserName = System.getProperty("browser", "firefox");
        System.out.println(browserName);
    }
}
