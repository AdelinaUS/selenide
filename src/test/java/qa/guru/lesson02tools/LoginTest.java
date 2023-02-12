package qa.guru.lesson02tools;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Проверка QA.GURU")
public class LoginTest {

    @BeforeAll
    static void setConfiguration() {
        Configuration.baseUrl = "https://qa.guru";
    }

    @DisplayName("Проверка аутентификации")
    @ParameterizedTest(name = "Вход в браузере {0} успешно выполнен")
    @ValueSource(strings = {"chrome", "firefox"})
    void successfulLoginTest(String browser) {
        closeWebDriver();
        Configuration.browser = browser;

        open("/cms/system/login");
        $(".login-form").shouldHave(text("Войти"));

        $("[name=email]").setValue("qagurubot@gmail.com");
        $("[name=password]").setValue("qagurupassword");
        $(".btn-success").click();

        $(".main-header__login").click();

        $(".logined-form").shouldHave(text("Здравствуйте, QA_GURU_BOT"));
    }

    @DisplayName("Проверка неудачной аутентификации")
    @ParameterizedTest(name = "Неверный логин или пароль в браузере {0}")
    @ValueSource(strings = {"chrome", "firefox"})
    void unsuccessfulLoginTest(String browser) {
        closeWebDriver();
        Configuration.browser = browser;

        open("/cms/system/login");
        $(".login-form").shouldHave(text("Войти"));

        $("[name=email]").setValue("qagurubot@gmail.com");
        $("[name=password]").setValue("qagurupasswordwrong");
        $(".btn-success").click();

        $(".btn-success").shouldHave(cssClass("btn-error"));
        $(".btn-success").shouldHave(text("Неверный пароль"));
    }
}