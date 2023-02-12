package qa.guru.lesson13junit;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Проверка поиска Яндекс.Музыка")
public class YandexMusicTest {

    @BeforeAll
    public static void init() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://music.yandex.ru";
    }

    @DisplayName("Проверка поиска и карточки исполнителя")
    @ParameterizedTest(name = "Заголовок {1} должен быть при поиске артиста {2}")
    @CsvSource({"Imagine Dragons, Imagine Dragons, ГЛАВНОЕ ТРЕКИ АЛЬБОМЫ КЛИПЫ ПОХОЖИЕ ИНФО",
        "Любэ, Любэ, ГЛАВНОЕ ТРЕКИ АЛЬБОМЫ КЛИПЫ КОНЦЕРТЫ ПОХОЖИЕ ИНФО"})
    void search(String artist, String title, String tabsText) {
        String[] tabs = tabsText.split(" ");

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
        $$("div.d-tabs div").filter(visible).shouldHave(texts(List.of(tabs)));
    }
}
