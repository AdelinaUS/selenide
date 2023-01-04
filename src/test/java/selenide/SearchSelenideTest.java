package selenide;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SearchSelenideTest {
    @Test
    void searchExample() {
        Configuration.holdBrowserOpen = true;

        open("https://github.com/");
        $("#wiki-tab").click();
        $("#wiki-pages-box button").click();
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();
        $(".markdown-body").shouldHave(text("Using JUnit5 extend test class"));
    }
}

