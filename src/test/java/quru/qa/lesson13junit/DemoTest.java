package quru.qa.lesson13junit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DemoTest {

    @BeforeAll
    static void beforeAll1() {
        System.out.println(
            "Метод с аннотацией @BeforeAll запускается один раз в начале всех тестов (1).");
    }

    @BeforeAll
    static void beforeAll2() {
        System.out.println(
            "Метод с аннотацией @BeforeAll запускается один раз в начале всех тестов (2).");
    }

    @AfterAll
    static void afterAll1() {
        System.out.println(
            "Метод с аннотацией @AfterEach запускается один раз после всех тестов (1).");
    }

    @AfterAll
    static void afterAll2() {
        System.out.println(
            "Метод с аннотацией @AfterAll запускается один раз после всех тестов (2).");
    }

    @BeforeEach
    void setUp1() {
        System.out.println("Метод с аннотацией @BeforeEach запускается перед каждым тестом (1).");
    }

    @BeforeEach
    void setUp2() {
        System.out.println("Метод с аннотацией @BeforeEach запускается перед каждым тестом (2).");
    }

    @AfterEach
    void tearDown1() {
        System.out.println("Метод с аннотацией @AfterEach запускается после каждого теста (1).");
    }

    @AfterEach
    void tearDown2() {
        System.out.println("Метод с аннотацией @AfterEach запускается после каждого теста (2).");
    }

    @Test
    void demo1() {
        System.out.println("Successful test");
        Assertions.assertTrue(true);
    }

    @Test
    void demo2() {
        System.out.println("Unsuccessful test");
        Assertions.assertTrue(false);
    }
}
