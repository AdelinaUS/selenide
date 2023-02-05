package guru.qa.lesson13junit;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Проверка поиска Яндекс.Музыка")
public class SimpleTest {
    @BeforeAll
    public static void init() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://music.yandex.ru";
        Configuration.holdBrowserOpen = true;
    }

    @ParameterizedTest(name = "Заголовок {1} должен быть при поиске артиста {2}")
    @CsvSource({
            "Imagine Dragons, Imagine Dragons",
            "Любэ, Любэ"
    })
    void search(String artist, String title) {
        open("/");

        SelenideElement close = $(".js-close");
        if (close.exists()) {
            close.click();
        }
        $(".d-search__button").click();
        $("input.deco-input_suggest").setValue(artist);
        Selenide.sleep(2000); // waitUntil
        $("div.d-suggest__items_type_artist").$$(".d-suggest__item-artist").first().click();

        $("h1.page-artist__title").shouldBe(text(title));
        $$("div.d-tabs div")
                .filter(visible)
                .shouldHave(texts(List.of("ГЛАВНОЕ", "ТРЕКИ", "АЛЬБОМЫ", "КЛИПЫ", "ПОХОЖИЕ", "ИНФО")));
    }
}
