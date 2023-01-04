package demoqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CheckRegistrationFormTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void successfulRegistration() {
        open("/automation-practice-form");

        $(".main-header").shouldHave(text("Practice Form"));
        $("#firstName").setValue("Test first name")
                .shouldBe(Condition.value("Test first name")); // @todo Или так будет корректно проверять ввод формы?
        $("#lastName").setValue("Test last name");
        $("#userEmail").setValue("Test@gmail.com");
        $("[for=gender-radio-2]").click();
        $("#userNumber").setValue("89271111111");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("10");
        $(".react-datepicker__year-select").selectOptionByValue("1980");
        $(".react-datepicker__day--003").click();
        $("#subjectsInput").setValue("Java");

        SelenideElement subject = $("#subjectsInput");
        subject.setValue("M").pressEnter();
        subject.setValue("Com");
        subject.sendKeys(Keys.ARROW_DOWN);
        subject.pressEnter();

        $("[for=hobbies-checkbox-1]").click();
        $("[for=hobbies-checkbox-2]").click();

        File cv = new File("src/test/resources/Photo.png");
        $("#uploadPicture").uploadFile(cv);

        $("#currentAddress").setValue("Address1");
        $("#state").click();
        $("#react-select-3-option-0").sibling(0).click();
        $("#city").click();
        $("#city").lastChild().find(byText("Agra")).click();
        $("#submit").pressEnter();
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));

        // @todo Может быть имеет смысл проверить на фактическое сохранение данных - то есть вывод данных в сплывающем окне?
    }

    @Test
    void unsuccessfulRegistration() {
        open("/automation-practice-form");

        $("#firstName").setValue("Ivan");

        $("#submit").click();

        $(".was-validated").should(exist);
    }
}
