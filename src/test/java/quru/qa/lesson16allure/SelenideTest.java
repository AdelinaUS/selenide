package quru.qa.lesson16allure;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import quru.qa.steps.WebSteps;

@Tag("checked")
@DisplayName("Allure Reports")
public class SelenideTest {

    public static final String REPOSITORY = "eroshenkoam/allure-example";
    public static final String ISSUE_TITLE = "e.sh";

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    @DisplayName("Чистый Selenide (с Listener)")
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        SelenideElement headerSearchInput = $(".header-search-input");
        headerSearchInput.click();
        headerSearchInput.sendKeys(REPOSITORY);
        headerSearchInput.submit();

        $(linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();
        $(withText(ISSUE_TITLE)).should(Condition.exist);
    }

    @Test
    @Feature("Issue в репозитории")
    @Story("Проверка Issue")
    @Owner("owner")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "Testing", url = "https://github.com")
    @DisplayName("Проверка Issue используя лямбда шаги через step (name, () -> {})")
    void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
            attachment("Source", webdriver().driver().source());
        });

        step("Ищем репозиторий " + REPOSITORY, () -> {
            SelenideElement headerSearchInput = $(".header-search-input");
            headerSearchInput.click();
            headerSearchInput.sendKeys(REPOSITORY);
            headerSearchInput.submit();
        });

        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Открываем таб Issue", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие Issue с заголовком " + ISSUE_TITLE, () -> {
            $(withText(ISSUE_TITLE)).should(Condition.exist);
        });
    }

    @Test
    void testAnnotatedStep() {
        Allure.getLifecycle().updateTestCase(
            t -> t.setName("Шаги с аннотацией @Step")
        );
        Allure.feature("Issue в репозитории");
        Allure.story("Проверка Issue");
        Allure.label("owner", "owner");
        Allure.label("severity", SeverityLevel.NORMAL.value());
        Allure.link("Testing", "https://github.com");

        SelenideLogger.addListener("allure", new AllureSelenide());

        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE_TITLE);
    }
}
