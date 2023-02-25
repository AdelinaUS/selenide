package quru.qa.lesson13junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import quru.qa.calculate.Numbers;
import quru.qa.calculate.Strings;
import java.time.Month;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ArgumentsSources;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Для того, чтобы попробовать параметризированные тесты - не обязательно их рассматривать на
 * UI-тестах. Рассмотрим обычные тестовые методы взятые с просторов Интернета.
 */
@DisplayName("Пример использования разных провайдеров данных")
public class ProviderExampleTest {

    @DisplayName("Провайдер данные @ArgumentsSource")
    @ParameterizedTest
    @ArgumentsSource(BlankStringsArgumentsProvider.class)
    void isBlank_ShouldReturnTrueForNullOrBlankStringsArgProvider(String input) {
        assertTrue(Strings.isBlank(input));
    }

    @DisplayName("Провайдер данные @ArgumentsSources")
    @ParameterizedTest
    @ArgumentsSources({ @ArgumentsSource(BlankStringsArgumentsProvider.class),
        @ArgumentsSource(BlankStringsArgumentsProvider2.class) })
    void isBlank_ShouldReturnTrueForNullOrBlankStringsArgProviders(String input) {
        assertTrue(Strings.isBlank(input));
    }

    @DisplayName("Провайдер данные @CsvFileSource")
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void toUpperCase_ShouldGenerateTheExpectedUppercaseValueCSVFile(
        String input, String expected) {
        String actualValue = input.toUpperCase();
        assertEquals(expected, actualValue);
    }

    @DisplayName("Провайдер данные @CsvSource")
    @ParameterizedTest
    @CsvSource({"task,TASK", "tAsk,TASK", "Math,MATH"})
    void toUpperCase_ShouldGenerateTheExpectedUppercaseValue(String input, String expected) {
        String actualValue = input.toUpperCase();
        assertEquals(expected, actualValue);
    }

    @DisplayName("Провайдер данные @EnumSource")
    @ParameterizedTest
    @EnumSource(Month.class)
    void getValueForAMonth_IsAlwaysBetweenOneAndTwelve(Month month) {
        int monthNumber = month.getValue();
        assertTrue(monthNumber >= 1 && monthNumber <= 12);
    }

    @DisplayName("Провайдер данные @MethodSource")
    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input, boolean expected) {
        assertEquals(expected, Strings.isBlank(input));
    }

    @DisplayName("Провайдер данные @ValueSource")
    @ParameterizedTest(name = "Значение {0} нечетное")
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE})
    void isOdd_ShouldReturnTrueForOddNumbers(int number) {
        assertTrue(Numbers.isOdd(number));
    }

    private static Stream<Arguments> provideStringsForIsBlank() {
        return Stream.of(
            Arguments.of(null, true),
            Arguments.of("", true),
            Arguments.of("  ", true),
            Arguments.of("not blank", false)
        );
    }
}

class BlankStringsArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
            Arguments.of((String) null),
            Arguments.of(""),
            Arguments.of("   ")
        );
    }
}

class BlankStringsArgumentsProvider2 implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
            Arguments.of((String) null),
            Arguments.of(""),
            Arguments.of("   ")
        );
    }
}