package guru.qa;

public class Arithmetic {
    public double eq(double a, double b) {
        return a + b;
    }

    public int sum(int a, int b) {
        return a + b;
    }

    public int sumOther(int a, int b) {
        return Math.addExact(a, b);
    }
}
