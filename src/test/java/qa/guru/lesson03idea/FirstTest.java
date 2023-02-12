package qa.guru.lesson03idea;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FirstTest {

    @Test
    void shouldOpen() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.parse("28.11.2020", formatter);

        Assertions.assertEquals(DayOfWeek.SATURDAY, localDate.getDayOfWeek());
    }
}
