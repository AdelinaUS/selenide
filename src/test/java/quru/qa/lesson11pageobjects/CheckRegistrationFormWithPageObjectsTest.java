package quru.qa.lesson11pageobjects;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import quru.qa.base.TestBase;

@Tag("checked")
public class CheckRegistrationFormWithPageObjectsTest extends TestBase {

    @Test
    void successfulRegistration() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        String testFirstName = "Test first name";
        String testLastName = "Test last name";
        String testEmail = "Test@gmail.com";
        String[] subjects = {"Math", "Commerce"};
        String[] hobbies = {"Sports", "Reading"};

        registrationPage.openPage()
            .setFirstName(testFirstName)
            .setLastName(testLastName)
            .setTestEmail(testEmail)
            .setGender("Female")
            .setPhone("8927123456")
            .setBirthDate("003", "January", "1980")
            .setSubjects(subjects)
            .setHobbies(hobbies)
            .setImg("src/test/resources/Photo.png")
            .setCurrentAddress("Address1")
            .setState("Uttar Pradesh")
            .setCity("Agra");

        registrationPage.submit();

        registrationPage.verifyResultsModalAppears()
            .verifyResult("Student Name", testFirstName + " " + testLastName)
            .verifyResult("Student Email", "Test@gmail.com")
            .verifyResult("Gender", "Female")
            .verifyResult("Mobile", "8927123456")
            .verifyResult("Date of Birth", "03 January,1980")
            .verifyResult("Subjects", "Maths, Commerce")
            .verifyResult("Hobbies", "Sports, Reading")
            .verifyResult("Picture", "Photo.png")
            .verifyResult("Address", "Address1")
            .verifyResult("State and City", "Uttar Pradesh Agra")
        ;
    }

    @Test
    void unsuccessfulRegistration() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        registrationPage
            .openPage()
            .setFirstName("Ivan")
            .submit()
            .checkWasValidated();
    }
}

