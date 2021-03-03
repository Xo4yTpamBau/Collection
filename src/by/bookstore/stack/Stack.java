package by.bookstore.stack;

import java.util.ListIterator;

public interface Stack<E> extends Iterable<E>{
    ListIterator<E> listIterator();
    void push(E e);
    E pop();
    E peek();
    boolean empty();
    int search(E e);
}
