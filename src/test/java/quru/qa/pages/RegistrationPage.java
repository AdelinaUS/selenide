package quru.qa.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import java.io.File;
import quru.qa.pages.components.RegistrationResultsModal;

public class RegistrationPage {

    RegistrationResultsModal registrationResultsModal = new RegistrationResultsModal();
    private final String titleText = "Practice form";
    public SelenideElement
        testFirstNameInput = Selenide.$("#firstName"),
        testLastNameInput = Selenide.$("#lastName");

    public RegistrationPage openPage() {
        Selenide.open("/automation-practice-form");

        Selenide.executeJavaScript("$('#fixedban').remove()");
        Selenide.executeJavaScript("$('footer').remove()");

        Selenide.$(".main-header").shouldHave(Condition.text(titleText));

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        Selenide.$("#firstName").setValue(value).shouldBe(Condition.value(value));

        return this;
    }


    public RegistrationPage setLastName(String value) {
        Selenide.$("#lastName").setValue(value);

        return this;
    }

    public RegistrationPage setTestEmail(String value) {
        Selenide.$("#userEmail").setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        Selenide.$("#genterWrapper").$(Selectors.byText(value)).click();

        return this;
    }

    public RegistrationPage setPhone(String value) {
        Selenide.$("#userNumber").setValue(value).shouldBe(Condition.value(value));

        return this;
    }

    public RegistrationPage setBirthDate(String day, String month, String year) {
        Selenide.$("#dateOfBirthInput").click();
        Selenide.$(".react-datepicker__month-select").selectOptionContainingText(month);
        Selenide.$(".react-datepicker__year-select").selectOptionByValue(year);
        Selenide.$(".react-datepicker__day--" + day).click();

        return this;
    }

    public RegistrationPage setSubjects(String[] subjects) {
        SelenideElement subjectInput = Selenide.$("#subjectsInput");

        for (String subject : subjects) {
            subjectInput.setValue(subject).pressEnter();
        }

        return this;
    }

    public RegistrationPage setHobbies(String[] hobbies) {
        SelenideElement hobbiesWrapper = Selenide.$("#hobbiesWrapper");

        for (String hobbie : hobbies) {
            hobbiesWrapper.$(Selectors.byText(hobbie)).click();
        }

        return this;
    }

    public RegistrationPage setImg(String path) {
        File cv = new File(path);

        Selenide.$("#uploadPicture").uploadFile(cv);

        return this;
    }

    public RegistrationPage setCurrentAddress(String currentAddress) {
        Selenide.$("#currentAddress").setValue(currentAddress);

        return this;
    }

    public RegistrationPage setState(String state) {
        Selenide.$("#state").click();
        Selenide.$("#stateCity-wrapper").$(Selectors.byText(state)).click();

        return this;
    }

    public RegistrationPage setCity(String city) {
        Selenide.$("#city").click();
        Selenide.$("#stateCity-wrapper").$(Selectors.byText(city)).click();

        return this;
    }

    public RegistrationPage submit() {
        Selenide.$("#submit").click();

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

    public void checkWasValidated() {
        Selenide.$(".was-validated").should(Condition.exist);
    }
}
