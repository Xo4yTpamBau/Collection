package by.bookstore.list;

import java.util.ListIterator;

public interface List<T> extends Iterable<T> {
    boolean add(T o);

    ListIterator<T> listIterator();

    void remove(int index);

    void remove(T o);

    T get(int index);

    T set(int index, T o);

    void add(int index, T o);

    int indexOf(T o);

    boolean contains(T o);

    T[] toArray();

    void clear();

    void trimToSize();

    boolean isEmpty();

    int size();
}
