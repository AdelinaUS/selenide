package qa.guru.lesson03selenide01;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// @todo Спросить у преподавателей как запускать браузер со своим HTML из файла,
//    чтобы можно было бы проверять какие-нибудь гепотизы. В частности 1 дополнительное задание из 03 урока.
public class SearchElementsTest {

    @BeforeEach
    final void openBlankPage() {
        if (hasWebDriverStarted()) {
            open("about:blank");
        }
    }

    @BeforeEach
    void setUp() {
        openFile("page_h1_div.html");
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void name() {
    }

    protected void openFile(String fileName) {
        open("/" + fileName + "?timeout=" + 10);
    }
}
