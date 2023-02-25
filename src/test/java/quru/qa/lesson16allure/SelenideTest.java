package quru.qa.lesson16allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class SelenideTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        SelenideElement headerSearchInput = $(".header-search-input");
        headerSearchInput.click();
        headerSearchInput.sendKeys("eroshenkoam/allure-example");
        headerSearchInput.submit();

        $(linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();
        $(withText("#80")).should(Condition.exist);
    }

}
