package by.bookstore.queue;

import by.bookstore.list.ArrayList;
import by.bookstore.list.List;
import by.bookstore.stack.ArrayStack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E> {

    private int size;
    Object[] array = new Object[10];

    public ArrayQueue(List<E> list) {
        int i = list.size();
        this.array = new Object[i];
        for (int i1 = 0; i1 < array.length; i++) {
            array[i1] = list.get(i);
        }
        size = i;
    }

    public ArrayQueue() {

    }
    
    public static void main(String[] args) {
        ArrayQueue<String> arrayQueue = new ArrayQueue<>();
        arrayQueue.add("Hello 1");
        arrayQueue.add("Hello 2");
        arrayQueue.add("Hello 3");
        arrayQueue.add("Hello 4");


        System.out.println(arrayQueue.element());
        System.out.println(arrayQueue.peek());

        System.out.println(arrayQueue.contains("Hello 5"));

        System.out.println(arrayQueue);

        for (String s : arrayQueue) {
            System.out.println(s);
        }


    }


    private void grow(){
        int i = size + (size / 2);
        array = Arrays.copyOf(array, i);
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr(this.array);
    }

    private class ListItr extends Itr implements ListIterator<E>{


        public ListItr(Object[] array) {
            super(array);
        }

        @Override
        public boolean hasPrevious() {
            if (cursor < 0) return false;
            return array[cursor] != null;
        }

        @Override
        public E previous() {
            return (E) array[--cursor];
        }

        @Override
        public int nextIndex() {
            return cursor++;
        }

        @Override
        public int previousIndex() {
            return cursor--;
        }

        @Override
        public void set(E t) {
            array[cursor] = t;

        }

        @Override
        public void add(E t) {
            if (size == array.length) grow();
            for (int i = size; i < (size - cursor); i--) {
                array[i + 1] = array[i];
            }
            array[cursor] = t;
        }
    }

    @Override
    public boolean add(E e) {
        if (size == array.length) grow();
        if (size == array.length) throw new IllegalStateException();
        array[size++] = e;
        return true;
    }

    @Override
    public boolean offer(E e) {
        if (size == array.length) grow();
        if (size == array.length) return false;
        array[size++] = e;
        return true;
    }

    @Override
    public E remove() {
        if (isEmpty()) throw new NoSuchElementException();
        Object old = array[0];
        for (int i = 0; i < size; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return (E) old;
    }

    @Override
    public E poll() {
        if (isEmpty()) return null;
        Object old = array[0];
        for (int i = 0; i < size; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return (E) old;
    }

    @Override
    public E element() {
        if (isEmpty()) throw new NoSuchElementException();
        return (E) array[0];
    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
        return (E) array[0];
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
        for (int i = 0; i < size; i++) {
            if (array[i].equals(e)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder v = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) break;
            if (array[i + 1] != null) {
                v.append(array[i].toString()).append(", ");
            } else v.append(array[i].toString());
        }
        v.append("]");
        return v.toString();
    }
    public Iterator<E> iterator() {
        return new Itr(this.array);
    }

    private class Itr implements Iterator<E>{

        private Object[] arrayStack;
        protected int cursor;

        public Itr(Object[] array) {
            this.arrayStack = array;
        }


        @Override
        public boolean hasNext() {
            if (cursor < 0) return false;
            return arrayStack[cursor] != null;
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

