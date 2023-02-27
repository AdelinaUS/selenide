package quru.qa.base;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import quru.qa.data.TestData;
import quru.qa.pages.RegistrationPage;

public class TestBase {

    public TestData testData = new TestData();

    public RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }
}
