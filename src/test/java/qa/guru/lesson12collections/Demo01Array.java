package qa.guru.lesson12collections;

import java.util.Arrays;

public class Demo01Array {
    public static void main(String[] args) {
        // @todo Массив простых элементов
        Cat[] cats = new Cat[3];

        cats[0] = new Cat("Барсик");
        cats[1] = new Cat("Мурка");
        cats[2] = new Cat("Кити");

        cats[1] = null;

        System.out.println(Arrays.toString(cats));
    }
}
