package quru.qa.lesson13junit;

import java.lang.reflect.Method;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Дополнительное задание. Кастомный запуск тестов с обработкой аннотаций.
 */
public class MiniJUnit {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) throws Exception {
        DemoTest instance = DemoTest.class.getConstructor().newInstance();

        ArrayList<Method> beforeAllMethods = new ArrayList<Method>();
        ArrayList<Method> afterAllMethods = new ArrayList<Method>();
        ArrayList<Method> beforeEachMethods = new ArrayList<Method>();
        ArrayList<Method> afterEachMethods = new ArrayList<Method>();

        Method[] methods = DemoTest.class.getDeclaredMethods();

        for (Method method : methods) {
            BeforeAll beforeAllAnnotation = null;
            AfterAll afterAllAnnotation = null;
            BeforeEach beforeEachAnnotation = null;
            AfterEach afterEachAnnotation = null;

            beforeAllAnnotation = method.getAnnotation(BeforeAll.class);
            afterAllAnnotation = method.getAnnotation(AfterAll.class);
            beforeEachAnnotation = method.getAnnotation(BeforeEach.class);
            afterEachAnnotation = method.getAnnotation(AfterEach.class);

            if (beforeAllAnnotation != null) {
                beforeAllMethods.add(method);
            }

            if (afterAllAnnotation != null) {
                afterAllMethods.add(method);
            }

            if (beforeEachAnnotation != null) {
                beforeEachMethods.add(method);
            }

            if (afterEachAnnotation != null) {
                afterEachMethods.add(method);
            }
        }

        for (Method method : beforeAllMethods) {
            method.invoke(instance);
        }

        for (Method method : methods) {
            Test annotation = method.getAnnotation(Test.class);
            if (annotation != null) {
                for (Method methodInternal : beforeEachMethods) {
                    methodInternal.invoke(instance);
                }

                try {
                    method.invoke(instance);
                    System.out.println("TEST PASSED!!!");
                } catch (Exception e) {
                    System.out.println("TEST BROKEN!!!");

                    Throwable cause = e.getCause();
                    System.out.printf(ANSI_RED + "invocation of %s failed: %s%n" + ANSI_RESET,
                        method,
                        cause.getMessage());
                }

                for (Method methodInternal : afterEachMethods) {
                    methodInternal.invoke(instance);
                }
            }
        }

        for (Method method : afterAllMethods) {
            method.invoke(instance);
        }
    }
}
