package by.bookstore.deque;

import by.bookstore.list.DeqLinkedList;
import by.bookstore.queue.LinkedQueue;

import java.util.Iterator;
import java.util.ListIterator;

public class LinkedDeque<E> implements Deque<E> {

    private int size;
    private Node<E> head;
    private Node<E> tail;

    public static void main(String[] args) {
        LinkedDeque<String> linkedDeque = new LinkedDeque<>();

        linkedDeque.addFirst("Hello 1");
        linkedDeque.addFirst("Hello 2");
        linkedDeque.offerFirst("Hello 3");
        linkedDeque.offerFirst("Hello 4");
        linkedDeque.offerFirst("Hello 5");
        linkedDeque.offerFirst("Hello 6");
        linkedDeque.addLast("Hello 7");
        linkedDeque.offerLast("Hello 8");

        System.out.println(linkedDeque);

//        linkedDeque.removeFirst();
//        linkedDeque.removeLast();
//
//        linkedDeque.pollFirst();
//        linkedDeque.pollLast();
//
//        System.out.println(linkedDeque.elementFirst());
//        System.out.println(linkedDeque.elementLast());
//        System.out.println(linkedDeque.peekFirst());
//        System.out.println(linkedDeque.peekLast());
//
//
//        System.out.println(linkedDeque);

        for (String s : linkedDeque) {
            System.out.println(s);
        }
    }

    @Override
    public boolean addFirst(E e) {
        if (isEmpty()) {
            Node<E> temp = new Node<>(e, null, null);
            this.head = temp;
            this.tail = temp;
        } else {
            Node<E> temp = this.head;
            this.head = new Node<>(e, head, null);
            temp.prev = head;
        }
        size++;
        return false;
    }

    @Override
    public boolean addLast(E e) {
        if (isEmpty()) {
            Node<E> temp = new Node<>(e, null, null);
            this.head = temp;
            this.tail = temp;
        } else {
            Node<E> temp = this.tail;
            this.tail = new Node<>(e, null, tail);
            temp.next = tail;
        }
        size++;
        return false;
    }

    @Override
    public boolean offerFirst(E e) {
        if (isEmpty()) {
            Node<E> temp = new Node<>(e, null, null);
            this.head = temp;
            this.tail = temp;
        } else {
            Node<E> temp = this.head;
            this.head = new Node<>(e, head, null);
            temp.prev = head;
        }
        size++;
        return false;
    }


    @Override
    public boolean offerLast(E e) {
        if (isEmpty()) {
            Node<E> temp = new Node<>(e, null, null);
            this.head = temp;
            this.tail = temp;
        } else {
            Node<E> temp = this.tail;
            this.tail = new Node<>(e, null, tail);
            temp.next = tail;
        }
        size++;
        return false;
    }

    @Override
    public E removeFirst() {
        Node<E> old = tail;
        tail = old.prev;
        tail.next = null;
        size--;
        return (E) old;
    }

    @Override
    public E removeLast() {
        Node<E> old = head;
        head = old.next;
        head.prev = null;
        size--;
        return (E) old;
    }

    @Override
    public E pollFirst() {
        Node<E> old = tail;
        tail = old.prev;
        tail.next = null;
        size--;
        return (E) old;
    }

    @Override
    public E pollLast() {
        Node<E> old = head;
        head = old.next;
        head.prev = null;
        size--;
        return (E) old;
    }

    @Override
    public E elementFirst() {
        return (E) tail.element;
    }

    @Override
    public E elementLast() {
        return (E) head.element;
    }

    @Override
    public E peekFirst() {
        return (E) tail.element;
    }

    @Override
    public E peekLast() {
        return (E) head.element;
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return false;
    }

    @Override
    public boolean offer(E e) {
        offerLast(e);
        return false;
    }

    @Override
    public E remove() {
        removeLast();
        return null;
    }

    @Override
    public E poll() {
        pollLast();
        return null;
    }

    @Override
    public E element() {
        elementFirst();
        return null;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public void push(E e) {
        addLast(e);
    }

    @Override
    public E pop() {
        removeFirst();
        return null;
    }

    @Override
    public E peek() {
        peekFirst();
        return null;
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public int search(E e) {
        return 0;
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
            temp = temp.next;
        }
        v.append("]");
        return v.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr(this.head);
    }

    private class Itr implements Iterator<E> {

        private Node<E> cursor;

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
            cursor = cursor.next;
            return element;
        }
    }


    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> prev;

        public Node(E element, Node<E> next, Node<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    ", next=" + next +
                    ", prev=" + prev +
                    '}';
        }
    }
}
