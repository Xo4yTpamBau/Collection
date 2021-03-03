package by.bookstore;


import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        List<String> strings2 = new LinkedList<>();

        strings.add(null);
        strings2.add(null);

        System.out.println(strings);
        System.out.println(strings2);

        Set<String> stringSet = new HashSet<>();
        Set<String> stringSet2 = new TreeSet<>();

        stringSet.add(null);
//        stringSet2.add(null);

        Map<String, String> stringStringMap = new HashMap<>();
        Map<String, String> stringStringMap2 = new TreeMap<>();

        stringStringMap.put(null, "Hello 1");
//        stringStringMap2.put(null, "Hello 1");

        Queue<String> strings1 = new LinkedList<>();
        Deque<String> strings3 = new ArrayDeque<>();

        strings1.add(null);
        strings3.add(null);

        System.out.println(strings1);
        System.out.println(strings3);
    }
}
