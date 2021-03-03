package by.bookstore.deque;

import by.bookstore.queue.ArrayQueue;
import by.bookstore.stack.Stack;

import java.util.*;

public class ArrayDeque<E> implements Deque<E> {

    private int size;
    private Object[] array = new Object[10];
    private int head;
    private int tail;

    public static void main(String[] args) {
        Deque<String> arrayDeque = new ArrayDeque<>();

        arrayDeque.addFirst("Hello 1");
        arrayDeque.addFirst("Hello 2");
        arrayDeque.addFirst("Hello 3");
        arrayDeque.addLast("Hello 4");
        arrayDeque.addLast("Hello 5");
        arrayDeque.addLast("Hello 6");


        System.out.println(arrayDeque);

        for (String s : arrayDeque) {
            System.out.println(s);
        }
    }

    @Override
    public boolean addFirst(E e) {
        if (size == array.length) throw new IllegalStateException();
        array[tail] = e;
        if (tail == array.length) tail = 0;
        else tail++;
        size++;
        return false;
    }

    @Override
    public boolean addLast(E e) {
        if (size == array.length) throw new IllegalStateException();
        if (head == 0) head = array.length - 1;
        else head--;
        array[head] = e;
        size++;
        return false;
    }

    @Override
    public boolean offerFirst(E e) {
        if (size == array.length) return false;
        array[tail] = e;
        if (tail == array.length) tail = 0;
        else tail++;
        size++;
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        if (size == array.length) return false;
        if (head == 0) head = array.length - 1;
        else head--;
        array[head] = e;
        size++;
        return false;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        if (head == tail) return null;
        array[head++] = null;
        size--;
        return null;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        if (head == tail) return null;
        array[tail--] = null;
        size--;
        return null;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) return null;
        if (head == tail) return null;
        array[head++] = null;
        size--;
        return null;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) return null;
        if (head == tail) return null;
        array[tail--] = null;
        size--;
        return null;
    }

    @Override
    public E elementFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        return (E) array[tail];
    }

    @Override
    public E elementLast() {
        if (isEmpty()) throw new NoSuchElementException();
        return (E) array[head];
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) return null;
        return (E) array[head];
    }

    @Override
    public E peekLast() {
        if (isEmpty()) return null;
        return (E) array[tail];
    }

    @Override
    public boolean add(E e) {
        return addLast(e);
    }

    @Override
    public boolean offer(E e) {
        return addLast(e);
    }

    @Override
    public E remove() {
        return removeFirst();
    }

    @Override
    public E poll() {
        return removeFirst();
    }

    @Override
    public E element() {
        return peekFirst();
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public void push(E e) {
        addFirst(e);
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public int search(E e) {
        for (int i = tail; i < head; i++) {
            if (i == array.length) i = 0;
            if (array[i].equals(e))
                return i;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E e) {
        for (int i = tail; i < head; i++) {
            if (i == array.length) i = 0;
            if (array[i].equals(e))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ArrayDeque{" +
                "size=" + size +
                ", array=" + Arrays.toString(array) +
                ", head=" + head +
                ", tail=" + tail +
                '}';
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr(this.array);
    }

    private class Itr implements Iterator<E>{

        private Object[] arrayStack;
        private int cursor;

        public Itr(Object[] array) {
            this.arrayStack = array;
        }


        @Override
        public boolean hasNext() {
            if (cursor < 0) return false;
            return cursor < size;
        }

        @Override
        public E next() {
            return (E) arrayStack[cursor++];
        }

        @Override
        public void remove() {
            arrayStack[cursor++] = null;

        }
    }
}
