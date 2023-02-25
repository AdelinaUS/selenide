package quru.qa.lesson12collections;

import java.util.*;

/**
 * Работа с двухсвязным списком LinkedList
 */
public class Demo03LinkedList {
    public static void main(String[] args) {
        new Demo03LinkedList().run();
    }

    public void run() {
        final int SEED = 110;

        List<Integer> randList = new LinkedList<Integer>();

        Random r = new Random(SEED);

        for (int i = 0; i < 10; i++) {
            randList.add(r.nextInt(22));
        }

        System.out.println(randList);

        if (randList.contains(22)) {
            System.out.println("randList содержит число 22");
        } else {
            System.out.println("randList не содержит число 22");
        }

        System.out.println(randList.get(3)  + " под индексом 3");

        randList.remove(6);

        randList.add(5, r.nextInt(100));

        System.out.println(randList);

        Iterator<Integer> itr = randList.iterator();
        System.out.println(itr);
        while (itr.hasNext()) {
            if (itr.next() % 2 == 0) {
                itr.remove();
            }
        }

        System.out.println(randList);
    }
}
