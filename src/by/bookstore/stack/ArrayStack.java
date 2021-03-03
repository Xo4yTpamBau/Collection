package by.bookstore.stack;

import by.bookstore.list.ArrayList;
import by.bookstore.list.List;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

public class ArrayStack<E> implements Stack<E> {
    private int size;
    private Object[] arrayStack = new Object[10];

    public ArrayStack(List<E> list) {
        int i = list.size();
        this.arrayStack = new Object[i];
        for (int i1 = 0; i1 < arrayStack.length; i++) {
            arrayStack[i1] = list.get(i);
        }
        size = i;
    }

    public ArrayStack() {

    }

    public static void main(String[] args) {
        ArrayStack<String> arrayStack = new ArrayStack<>();

        arrayStack.push("Hello 1");
        arrayStack.push("Hello 2");
        arrayStack.push("Hello 3");
        arrayStack.push("Hello 4");
        arrayStack.push("Hello 5");
        arrayStack.push("Hello 6");


        for (String s : arrayStack) {
            System.out.println(s);
        }
    }



    private void grow() {
        int i = size + (size / 2);
        arrayStack = Arrays.copyOf(arrayStack, i);
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr(this.arrayStack, this.size);
    }

    private class ListItr extends Itr implements ListIterator<E>{


        public ListItr(Object[] arrayStack, int size) {
            super(arrayStack, size);
        }

        @Override
        public boolean hasPrevious() {
            if (cursor < 0) return false;
            return arrayStack[++cursor] != null;
        }

        @Override
        public E previous() {
            return (E) arrayStack[++cursor];
        }

        @Override
        public int nextIndex() {
            return cursor--;
        }

        @Override
        public int previousIndex() {
            return cursor++;
        }

        @Override
        public void set(E t) {
            arrayStack[cursor] = t;

        }

        @Override
        public void add(E t) {
            if (size == arrayStack.length) grow();
            for (int i = size; i < (size - cursor); i--) {
                arrayStack[i + 1] = arrayStack[i];
            }
            arrayStack[cursor] = t;
        }
    }



    @Override
    public void push(E e) {
        if (arrayStack.length == size) grow();
        arrayStack[size++] = e;
    }

    @Override
    public E pop() {
        Object old = arrayStack[size - 1];
        arrayStack[size - 1] = null;
        size--;
        return (E) old;
    }

    @Override
    public E peek() {
        return (E) arrayStack[size - 1];
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public int search(E e) {
        for (int i = size - 1, j = 0; i > 0; i--) {
            if (arrayStack[i] == null) break;
            if (arrayStack[i].equals(e)) {
                return j;
            }
            j++;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder v = new StringBuilder("[");
        for (int i = 0; i < arrayStack.length; i++) {
            if (arrayStack[i] == null) break;
            if (arrayStack[i + 1] != null) {
                v.append(arrayStack[i].toString()).append(", ");
            } else v.append(arrayStack[i].toString());
        }
        v.append("]");
        return v.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr(this.arrayStack, size);
    }

    private class Itr implements Iterator<E>{

        private Object[] arrayStack;
        protected int cursor;

        public Itr(Object[] arrayStack, int size) {
            this.arrayStack = arrayStack;
            this.cursor = size - 1;
        }


        @Override
        public boolean hasNext() {
            if (cursor < 0) return false;
            return arrayStack[cursor--] != null;
        }

        @Override
        public E next() {
            return (E) arrayStack[cursor--];
        }

        @Override
        public void remove() {
            arrayStack[cursor--] = null;

        }
    }
}

