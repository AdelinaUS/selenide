package qa.guru.lesson12collections;

import java.util.ArrayList;

public class Demo02ArrayList {
    public static void main(String[] args) {
        ArrayList<Cat> cats = new ArrayList<Cat>();
        cats.add(new Cat("Кити"));
        cats.add(new Cat("Пушок"));
        cats.add(new Cat("Снежок"));

        System.out.println(cats.size());

        for (Cat cat : cats) {
            System.out.println(cat);
        }
    }
}
