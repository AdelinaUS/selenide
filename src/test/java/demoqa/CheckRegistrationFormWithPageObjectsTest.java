package demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CheckRegistrationFormWithPageObjectsTest extends TestBase {


    @Test
    void successfulRegistration() {
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
                .setBirthDate("003", "10", "1980")
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .setImg("src/test/resources/Photo.png")
                .setCurrentAddress("Address1")
                .setState("Uttar Pradesh")
                .setCity("Agra");

        registrationPage
                .submit()
                .checkSubmit();

        registrationPage.verifyResultsModalAppears()
                .verifyResult("Student Name", testFirstName + " " + testLastName)
                .verifyResult("Student Email", "Test@gmail.com")
                .verifyResult("Gender", "Female")
                .verifyResult("Mobile", "8927123456")
                .verifyResult("Date of Birth", "03 November,1980")
                .verifyResult("Subjects", "Maths, Commerce")
                .verifyResult("Hobbies", "Sports, Reading")
                .verifyResult("Picture", "Photo.png")
                .verifyResult("Address", "Address1")
                .verifyResult("State and City", "Uttar Pradesh Agra")
        ;
    }

    @Test
    void unsuccessfulRegistration() {
        registrationPage
                .openPage()
                .setFirstName("Ivan")
                .submit()
                .checkWasValidated();
    }
}

