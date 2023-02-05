package lesson12collections;

import java.util.*;

public class Demo06Map {
    public static void main(String[] args) {
        // HashMap
        HashMap<Integer, String> passportsAndNames = new HashMap<>();
        passportsAndNames.put(100200, "Иванов Иван Иванович");
        passportsAndNames.put(100200, "Иванов Иван Олегович");
        System.out.println(passportsAndNames);

        // LinkedHashMap

        // Hashtable

        // TreeMap
        TreeMap tm = new TreeMap();

        tm.put("Иван", 1020.35);
        tm.put("Дуся", 4020.40);

        System.out.println(tm);
    }
}
