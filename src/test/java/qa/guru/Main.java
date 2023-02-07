package qa.guru;

class Main {
    public static void main(String[] args) {
        byte newByte = -128;
        short newShort = 32767;
        int newInt = 1500;
        long newLong = 9999999;
        char newChar = 6553;
        double newDouble = 4.922222;
        float newFloat = 8.5F;

        System.out.println(newByte + newFloat);
        System.out.println(newShort - newDouble);
        System.out.println(newInt * newChar);
        System.out.println(newLong / newInt);
        System.out.println(newByte % newFloat);

        // вычисление с Int и Double
        System.out.print("How much this apples cost? ");
        System.out.print(newInt + newDouble);
        System.out.print(" RUB, ");

        boolean expression1 = 3 < 2;
        boolean expression2 = 5 > 4;

        if (expression1) {
            System.out.println("true");
        } else {
            if (expression2) {
                System.out.println("first expression is false, second expression is true.");
            } else {
                System.out.println("first and second expressions are false");

            }
        }
    }
}