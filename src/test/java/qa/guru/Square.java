package qa.guru;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Square {
    @Test
    void searchExample() {
        Arithmetic s = new Arithmetic();
        double result = s.eq(10.0, 10.5);

        System.out.println(result);

        assertEquals(result, 20.5);
    }

    @Test
    void intSumOverflow() {
        Arithmetic s = new Arithmetic();
        int result = s.sum(2147483647, 1);

        System.out.println(result);
    }

    @Test
    void otherSumOverflow() {
        Arithmetic s = new Arithmetic();

        ArithmeticException thrown = Assertions.assertThrows(ArithmeticException.class, () -> {
            int result = s.sumOther(2147483647, 1);
        }, "NumberFormatException was expected");
    }
}










