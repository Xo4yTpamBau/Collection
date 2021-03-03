package by.bookstore.queue;

import java.util.ListIterator;

public interface Queue<E> extends Iterable<E>{

    ListIterator<E> listIterator();

    boolean add(E e);

    boolean offer(E e);

    E remove();

    E poll();

    E element();

    E peek();

    int size();

    boolean isEmpty();

    boolean contains(E e);
}
