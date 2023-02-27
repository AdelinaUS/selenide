package quru.qa.data;

import com.github.javafaker.Faker;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TestData {

    List<String> gender = Arrays.asList("Male", "Female", "Other");

    Faker faker = new Faker();
    Faker ruFaker = new Faker(new Locale("ru"));
    String lastName = ruFaker.name().lastName();
    String email = faker.internet().emailAddress();

    public String getFirstName() {
        return ruFaker.name().firstName();
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return faker.address().streetAddress();
    }

    public String getGender() {
        return faker.options().nextElement(gender);
    }

    public String getState() {
        List<String> states = Arrays.asList("Uttar Pradesh");
        return faker.options().nextElement(states);
    }

    public String getCity() {
        List<String> cities = Arrays.asList("Agra");
        return faker.options().nextElement(cities);
    }

    public String getPhone() {
        return faker.numerify("##########");
    }

    public String[] getSubjects() {
        String[] subjects = {"Maths", "Commerce"};
        return subjects;
    }

    public String[] getHobbies() {
        String[] hobbies = {"Sports", "Reading"};
        return hobbies;
    }

    public Date getBirthdate() {
        return faker.date().birthday();
    }
}
