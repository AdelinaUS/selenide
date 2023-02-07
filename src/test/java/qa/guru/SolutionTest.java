package qa.guru;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.holdBrowserOpen;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SolutionTest {
    @Test
    void loadSolutionPageTest() {
        holdBrowserOpen = true;
        open("https://github.com");
        $(byText("Solutions")).hover();
        $(byText("Enterprise")).click();
        $$( "div.application-main h1").first().shouldHave(text("Build like the best"));
    }
}
