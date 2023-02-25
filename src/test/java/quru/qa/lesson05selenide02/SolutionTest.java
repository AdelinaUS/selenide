package quru.qa.lesson05selenide02;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

import org.junit.jupiter.api.Test;

public class SolutionTest extends BaseTest {

    @Test
    void loadSolutionPageTest() {
        open("https://github.com");

        $(byText("Solutions")).hover();
        $(byText("Enterprise")).click();
        $$("div.application-main h1").first().shouldHave(text("Build like the best"));
    }
}
