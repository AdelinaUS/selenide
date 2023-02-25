package quru.qa.JUnit.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SuccesfullTest {
    @DisplayName("Metanit site headers should be present by selenide query")
    @Test
    void successfulSearchTest() {
        open("https://metanit.com/");

        $("#header").shouldHave(text("METANIT.COM Сайт о программировании"));
    }
}