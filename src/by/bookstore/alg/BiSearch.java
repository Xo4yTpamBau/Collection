package by.bookstore.alg;

import by.bookstore.list.ArrayList;
import by.bookstore.list.List;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class BiSearch {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("Hello 1");
        strings.add("Hello 2");
        strings.add("Hello 3");
        strings.add("Hello 4");
        strings.add("Hello 5");
        BiSearch biSearch = new BiSearch();
        int search = biSearch.search(strings, "Hello 1", String::compareTo);
        System.out.println(search);
    }


    public <E> int search(List<E> list, E element, Comparator<E> comparator) {
        int leftIndex = 0;
        int rightIndex = list.size() - 1;
        int index = list.size() / 2;
        if (comparator.compare(list.get(rightIndex), element) < 0) return -1;
        while (index < rightIndex & index > leftIndex) {
            if (element.equals(list.get(rightIndex))) return rightIndex;
            if (comparator.compare(list.get(index), element) > 0) {
                rightIndex = index;
                index -= index / 2;
            } else {
                leftIndex = index;
                index += index / 2;
            }
        }
        return leftIndex;
    }

    public <E> int search(List<? extends Comparable<E>> list, E element) {
        return -1;
    }

}
