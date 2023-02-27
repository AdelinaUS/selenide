package quru.qa.lesson15faker;

import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import quru.qa.base.TestBase;

public class CheckRegistrationFormWithPageObjectsTest extends TestBase {

    @Test
    void successfulRegistration() {
        String[] monthName = {"January", "February",
            "March", "April", "May", "June", "July",
            "August", "September", "October", "November",
            "December"};

        Calendar calendar = Calendar.getInstance();

        String firstName = testData.getFirstName();
        String lastName = testData.getLastName();
        String email = testData.getEmail();
        String[] subjects = testData.getSubjects();
        String[] hobbies = testData.getHobbies();
        String gender = testData.getGender();
        String phone = testData.getPhone();
        Date birthdate = testData.getBirthdate();
        String address = testData.getAddress();
        String state = testData.getState();
        String city = testData.getCity();

        calendar.setTime(birthdate);

        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        String year = String.valueOf(calendar.get(Calendar.YEAR));

        String nameOfMonth = monthName[calendar.get(calendar.get(Calendar.MONTH))];

        registrationPage.openPage()
            .setFirstName(firstName)
            .setLastName(lastName)
            .setTestEmail(email)
            .setGender(gender)
            .setPhone(phone)
            .setBirthDate(StringUtils.leftPad(day, 3, "0"), month, year)
            .setSubjects(subjects)
            .setHobbies(hobbies)
            .setImg("src/test/resources/Photo.png")
            .setCurrentAddress(address)
            .setState(state)
            .setCity(city);

        registrationPage.submit();

        registrationPage.verifyResultsModalAppears()
            .verifyResult("Student Name", firstName + " " + lastName)
            .verifyResult("Student Email", email)
            .verifyResult("Gender", gender)
            .verifyResult("Mobile", phone)
            .verifyResult("Date of Birth", day + " " + nameOfMonth + "," + year)
            .verifyResult("Subjects", String.join(", ", subjects))
            .verifyResult("Hobbies", String.join(", ", hobbies))
            .verifyResult("Picture", "Photo.png")
            .verifyResult("Address", address)
            .verifyResult("State and City", state + " " + city)
        ;
    }

    @Test
    void unsuccessfulRegistration() {
        String firstName = testData.getFirstName();

        registrationPage
            .openPage()
            .setFirstName(firstName)
            .submit()
            .checkWasValidated();
    }
}

