package by.bookstore.queue;


import by.bookstore.list.LinkedList;
import by.bookstore.stack.LinkedStack;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedQueue<E> implements Queue<E> {
    private int size;
    private Node<E> head;
    private Node<E> tail;
    private int limit = 150;

    public static void main(String[] args) {
        LinkedQueue<String> linkedQueue = new LinkedQueue<>();
        linkedQueue.add("Hello 1");
        linkedQueue.add("Hello 2");
        linkedQueue.offer("Hello 3");
        linkedQueue.offer("Hello 4");

        System.out.println(linkedQueue);

//        linkedQueue.remove();
//        linkedQueue.poll();
//
//        System.out.println(linkedQueue.element());
//        System.out.println(linkedQueue.peek());
//
//        System.out.println(linkedQueue.contains("Hello 4"));
//
//        System.out.println(linkedQueue);

        for (String s : linkedQueue) {
            System.out.println(s);
        }
    }

    public LinkedQueue() {

    }

    public LinkedQueue(int limit) {
        this.limit = limit;
    }

    public LinkedQueue(Queue<E> elements){
        for (int i = 0; i < elements.size(); i++) {
            add(elements.remove());
        }
    }


    @Override
    public ListIterator<E> listIterator() {
        return new ListItr(this.head);
    }

    private class ListItr extends Itr implements ListIterator<E>{

        private int cursor2 = 0;


        public ListItr(Node<E> head) {
            super(head);
        }

        @Override
        public boolean hasPrevious() {
            for (int i = 0; i < cursor2; i++) {
                cursor = cursor.prev;
            }
            return cursor != null;
        }

        @Override
        public E previous() {
            for (int i = 0; i < cursor2; i++) {
                cursor = cursor.prev;
            }
            cursor2--;
            return (E) cursor;
        }

        @Override
        public int nextIndex() {
            return cursor2++;
        }

        @Override
        public int previousIndex() {
            return cursor2--;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(E t) {
            cursor.element = t;

        }

        @Override
        public void add(E t) {
            for (int i = 0; i < cursor2; i++) {
                cursor = cursor.prev;
            }
            Node<E> temp = new Node<>(t, cursor.prev.prev);
            cursor.prev = temp;
        }
    }

    @Override
    public boolean add(E e) {
        if (size == limit) throw new IllegalStateException();
        if (isEmpty()) {
            Node<E> tNode = new Node<>(e, null);
            this.head = tNode;
            this.tail = tNode;
        } else {
            Node<E> temp = this.tail;
            Node<E> prev = new Node<>(e, null);
            temp.prev = prev;
            this.tail = prev;
        }
        size++;
        return true;
    }

    @Override
    public boolean offer(E e) {
        if (size == limit) return false;
        if (isEmpty()) {
            Node<E> tNode = new Node<E>(e, null);
            this.head = tNode;
            this.tail = tNode;
        } else {
            Node<E> temp = this.tail;
            Node<E> prev = new Node<>(e, null);
            temp.prev = prev;
            this.tail = prev;

        }
        size++;
        return true;
    }

    @Override
    public E remove() {
        if (isEmpty()) throw new NoSuchElementException();
        Node<E> temp = this.head;
        this.head = temp.prev;
        size--;
        return temp.element;

    }

    @Override
    public E poll() {
        if (isEmpty()) return null;
        Node<E> temp = this.head;
        this.head = temp.prev;
        size--;
        return temp.element;
    }

    @Override
    public E element() {
        if (isEmpty()) throw new NoSuchElementException();
        return tail.element;
    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
        return tail.element;
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
        Node<E> temp = this.head;
        for (int i = 0; i < size; i++) {
            if (temp.element.equals(e)) {
                return true;
            }
            temp = temp.prev;
        }
        return false;
    }

    @Override
    public String toString() {
        Node<E> temp = this.head;
        StringBuilder v = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i < size - 1) {
                v.append(temp.element.toString()).append(", ");
            } else v.append(temp.element.toString());
            temp = temp.prev;
        }
        v.append("]");
        return v.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr(this.head);
    }

    private class Itr implements Iterator<E> {

        protected Node<E> cursor;

        public Itr(Node<E> head) {
            cursor = head;
        }


        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public E next() {
            E element = cursor.element;
            cursor = cursor.prev;
            return element;
        }
    }


    private static class Node<E> {
        public E element;
        public Node<E> prev;


        public Node(E element, Node<E> prev) {
            this.element = element;
            this.prev = prev;
        }


        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    ", prev=" + prev +
                    '}';
        }
    }

}





