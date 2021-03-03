package by.bookstore.tree;

public interface TreeSet<E> {
    void add(E e);

    E pollFirst();
    E pollLast();

    E first();
    E last();
    E remove(E e);

    boolean contains(E e);
    boolean isEmpty();
    int size();
    void clear();
}
