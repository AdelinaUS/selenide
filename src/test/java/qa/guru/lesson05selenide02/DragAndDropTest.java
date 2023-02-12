package qa.guru.lesson05selenide02;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.holdBrowserOpen;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.junit.jupiter.api.Test;

public class DragAndDropTest extends BaseTest {

    @Test
    void swappingRectangles() {
        open("https://the-internet.herokuapp.com/drag_and_drop");

        $("#column-a").dragAndDropTo("#column-b");
        $("#column-b").shouldHave(text("A"));
        $("#column-a").shouldHave(text("B"));

    }
}
