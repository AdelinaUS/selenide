package qa.guru.lesson04selenide01;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SearchSelenideTest {

    @BeforeEach
    void setUpConfiguration() {
        Configuration.baseUrl = "https://github.com";
    }

    @Test
    void searchExample() {
        open("/selenide/selenide");

        $("#wiki-tab").click();
        $("#wiki-pages-box button").click();
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();
        $(".markdown-body").shouldHave(text("Using JUnit5 extend test class"));
    }

    @Test
    void searchInputExample() {
        open("/");

        $(".js-site-search-focus").setValue("selenide").pressEnter();
        $(linkText("selenide/selenide")).click();

        $("#wiki-tab").click();
        $("#wiki-pages-box button").click();
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();
        $(".markdown-body").shouldHave(text("Using JUnit5 extend test class"));
    }
}

