package by.bookstore.deque;

import by.bookstore.queue.Queue;
import by.bookstore.stack.Stack;

public interface Deque<E> extends Queue<E>, Stack<E>, Iterable<E> {
    boolean addFirst(E e);
    boolean addLast(E e);

    boolean offerFirst(E e);
    boolean offerLast(E e);

    E removeFirst();
    E removeLast();

    E pollFirst();
    E pollLast();

    E elementFirst();
    E elementLast();

    E peekFirst();
    E peekLast();

    int size();

    boolean isEmpty();

    boolean contains(E e);
}
