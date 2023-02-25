package quru.qa.lesson05selenide02;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.holdBrowserOpen = true;
    }
}
