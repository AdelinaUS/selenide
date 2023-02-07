package qa.guru.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import qa.guru.pages.components.RegistrationResultsModal;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPage {
    RegistrationResultsModal registrationResultsModal = new RegistrationResultsModal();
    private final String titleText = "Practice form";
    public SelenideElement
            testFirstNameInput = $("#firstName"),
            testLastNameInput = $("#lastName");

    public RegistrationPage openPage() {
        open("/automation-practice-form");

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('#footer').remove()");

        $(".main-header").shouldHave(text(titleText));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('#footer').remove()");

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        $("#firstName").setValue(value).shouldBe(value(value));

        return this;
    }


    public RegistrationPage setLastName(String value) {
        $("#lastName").setValue(value);

        return this;
    }

    public RegistrationPage setTestEmail(String value) {
        $("#userEmail").setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        $("#genterWrapper").$(byText(value)).click();

        return this;
    }

    public RegistrationPage setPhone(String value) {
        $("#userNumber").setValue(value).shouldBe(value(value));

        return this;
    }

    public RegistrationPage setBirthDate(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue(month);
        $(".react-datepicker__year-select").selectOptionByValue(year);
        $(".react-datepicker__day--" + day).click();

        return this;
    }

    public RegistrationPage setSubjects(String[] subjects) {
        SelenideElement subjectInput = $("#subjectsInput");

        for (String subject : subjects) {
            subjectInput.setValue(subject).pressEnter();
        }

        return this;
    }

    public RegistrationPage setHobbies(String[] hobbies) {
        SelenideElement hobbiesWrapper = $("#hobbiesWrapper");

        for (String hobbie : hobbies) {
            hobbiesWrapper.$(byText(hobbie)).click();
        }

        return this;
    }

    public RegistrationPage setImg(String path) {
        File cv = new File(path);

        $("#uploadPicture").uploadFile(cv);

        return this;
    }

    public RegistrationPage setCurrentAddress(String currentAddress) {
        $("#currentAddress").setValue(currentAddress);

        return this;
    }

    public RegistrationPage setState(String state) {
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();

        return this;
    }

    public RegistrationPage setCity(String city) {
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        return this;
    }

    public RegistrationPage submit() {
        $("#submit").click();

        return this;
    }

    public RegistrationPage verifyResultsModalAppears() {
        registrationResultsModal.verifyModalAppears();

        return this;
    }

    public RegistrationPage verifyResult(String key, String value) {
        registrationResultsModal.verifyResult(key, value);

        return this;
    }

    public void checkSubmit() {
        String testFirstName = "Test first name";
        String testLastName = "Test last name";
        String testEmail = "Test@gmail.com";
        String testPhone = "8927123456";

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

    public void checkWasValidated() {
        $(".was-validated").should(exist);
    }
}





