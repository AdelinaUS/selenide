package quru.qa.lesson19jenkinsproperties;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import quru.qa.base.TestBaseExtended;

import java.util.Calendar;
import java.util.Date;

import static io.qameta.allure.Allure.step;

public class CheckRegistrationFormWithPageObjectsTest extends TestBaseExtended {

    @Test
    @Tag("jenkins_properties")
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

        String nameOfMonth = monthName[calendar.get(Calendar.MONTH)];

        step("Заполняем форму", () -> {
            step("Открываем главную страницу", () -> {
                registrationPage.openPage();
            });

            step("Вводим имя", () -> {
                registrationPage.setFirstName(firstName);
            });

            step("Вводим фамилию", () -> {
                registrationPage.setLastName(lastName);
            });

            step("Вводим e-mail", () -> {
                registrationPage.setTestEmail(email);
            });

            step("Выбираем пол", () -> {
                registrationPage.setGender(gender);
            });

            step("Вводим номер телефона", () -> {
                registrationPage.setPhone(phone);
            });

            step("Указываем дату рождения", () -> {
                registrationPage.setBirthDate(StringUtils.leftPad(day, 3, "0"), nameOfMonth, year);
            });

            step("Выбираем темы", () -> {
                registrationPage.setSubjects(subjects);
            });

            step("Выбираем хобби", () -> {
                registrationPage.setHobbies(hobbies);
            });

            step("Загружаем фотографию", () -> {
                registrationPage.setImg("src/test/resources/Photo.png");
            });

            step("Вводим адрес", () -> {
                registrationPage.setCurrentAddress(address);
            });

            step("Выбираем штат", () -> {
                registrationPage.setState(state);
            });

            step("Выбираем город", () -> {
                registrationPage.setCity(city);
            });

            step("Отправляем форму", () -> {
                registrationPage.submit();
            });
        });

        step("Проверяем форму", () -> {
            step("Проверяем наличие модального окна", () -> {
                registrationPage.verifyResultsModalAppears();
            });

            step("Проверяем имя и фамилию", () -> {
                registrationPage.verifyResult("Student Name", firstName + " " + lastName);
            });

            step("Проверяем e-mail", () -> {
                registrationPage.verifyResult("Student Email", email);
            });

            step("Проверяем пол", () -> {
                registrationPage.verifyResult("Gender", gender);
            });

            step("Проверяем номер телефона", () -> {
                registrationPage.verifyResult("Mobile", phone);
            });

            step("Проверяем дату рождения", () -> {
                registrationPage.verifyResult("Date of Birth",
                    day + " " + nameOfMonth + "," + year);
            });

            step("Проверяем тему", () -> {
                registrationPage.verifyResult("Subjects", String.join(", ", subjects));
            });

            step("Проверяем хобби", () -> {
                registrationPage.verifyResult("Hobbies", String.join(", ", hobbies));
            });

            step("Проверяем фото", () -> {
                registrationPage.verifyResult("Picture", "Photo.png");
            });

            step("Проверяем адрес", () -> {
                registrationPage.verifyResult("Address", address);
            });

            step("Проверяем штат и город", () -> {
                registrationPage.verifyResult("State and City", state + " " + city);
            });
        });
    }

    @Test
    @Tag("jenkins_properties")
    void unsuccessfulRegistration() {
        String firstName = testData.getFirstName();

        step("Открываем главную страницу", () -> {
            registrationPage.openPage();
        });

        step("Вводим имя", () -> {
            registrationPage.setFirstName(firstName);

        });

        step("Отправляем форму", () -> {
            registrationPage.submit();

        });

        step("Заполнены не все поля или поля заполнены некорректно", () -> {
            registrationPage.checkWasValidated();
        });
    }
}

