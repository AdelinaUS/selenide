package qa.guru.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CheckRegistrationFormTest {
    File cv = new File("src/test/resources/Photo.png");
    SelenideElement subject = $("#subjectsInput");

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void successfulRegistration() {
        String testFirstName = "Test first name";
        String testLastName = "Test last name";
        String testEmail = "Test@gmail.com";
        String testPhone = "8927123456";

        open("/automation-practice-form");

        $(".main-header").shouldHave(text("Practice Form"));
        $("#firstName").setValue(testFirstName).shouldBe(value(testFirstName));
        $("#lastName").setValue(testLastName);
        $("#userEmail").setValue(testEmail);
        $("#genterWrapper").$(byText("Female")).click();

        $("#userNumber").setValue(testPhone).shouldBe(value(testPhone));
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("10");
        $(".react-datepicker__year-select").selectOptionByValue("1980");
        $(".react-datepicker__day--003").click();

        subject.setValue("Math").pressEnter();
        subject.setValue("Commerce").pressEnter();

        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Reading")).click();


        $("#uploadPicture").uploadFile(cv);

        $("#currentAddress").setValue("Address1");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Uttar Pradesh")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Agra")).click();
        $("#submit").click();


        ElementsCollection dataTable = $$(".table-dark tr");
        dataTable.get(1).$("td", 1).shouldHave(text(testFirstName + " " + testLastName));
        dataTable.get(2).$("td", 1).shouldHave(text(testEmail));
        dataTable.get(3).$("td", 1).shouldHave(text("Female"));
        dataTable.get(4).$("td", 1).shouldHave(text(testPhone));
        dataTable.get(5).$("td", 1).shouldHave(text("03 November,1980"));
        dataTable.get(6).$("td", 1).shouldHave(text("Maths, Commerce"));
        dataTable.get(7).$("td", 1).shouldHave(text("Sports, Reading"));
        dataTable.get(8).$("td", 1).shouldHave(text("Photo.png"));
        dataTable.get(9).$("td", 1).shouldHave(text("Address1"));
        dataTable.get(10).$("td", 1).shouldHave(text("Uttar Pradesh Agra"));
    }

    @Test
    void unsuccessfulRegistration() {
        open("/automation-practice-form");

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('#footer').remove()");

        $("#firstName").setValue("Ivan");

        $("#submit").click();

        $(".was-validated").should(exist);
    }
}

