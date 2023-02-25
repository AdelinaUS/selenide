package quru.qa.lesson02tools;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Проверка поиска в Google")
public class SearchTest {

    @BeforeAll
    static void setConfiguration() {
        Configuration.baseUrl = "https://www.google.com";
    }

    @DisplayName("Проверка поиска по слову")
    @ParameterizedTest(name = "Поиск сайта ru.selenide.org в браузере {0}")
    @ValueSource(strings = {"chrome", "firefox"})
    void successfulSearchTest(String browser) {
        closeWebDriver();
        Configuration.browser = browser;

        open("/");
        $("[name=q]").setValue("selenide").pressEnter();
        $("[id=search]").shouldHave(text("https://ru.selenide.org"));
    }
}