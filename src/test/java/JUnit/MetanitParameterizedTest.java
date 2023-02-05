/*package JUnit;


import com.codeborne.selenide.CollectionCondition;
import guru.qa.data.Locale;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.open;

public class MetanitParameterizedTest {

    static Stream<Arguments> MetanitLocaleDataProvider(){
        return Stream.of(
                Arguments.of(Locale.Russian, List.of("АССЕМБЛЕР", "С#", "JAVA", "WEB", "C", "C++", "SQL", "MONGODB", "GO", "SWIFT","KOTLIN", "DART", "PHP", "RUST", "F","НАСТРОЙКИ", " "))

        );
    }


    @MethodSource("MetanitLocaleDataProvider")
    @Tag("CRITICAL")
    @ParameterizedTest(name = "Для локали {0} отображаются кнопки {1}")
    void MetanitWebsiteShouldContainAllOfButtonsForGivenLocale(
            Locale locale,
            List<String> lists) {


        open("https://metanit.com/");
        $$x("menu").find(text(locale.name())).click();
        $$x("menu" ).shouldHave(texts(lists));

    @DisplayName("Адрес https://metanit.com должен быть в выдаче гугла по запросу 'Metanit'")
            open ("https://metanit.com")



    }


}
*/
