package by.bookstore.stack;

import by.bookstore.list.LinkedList;
import by.bookstore.list.List;

import java.util.Iterator;
import java.util.ListIterator;

public class LinkedStack<E> implements Stack<E> {
    private int size;
    private Node<E> root;

    public LinkedStack(List<E> list) {
        for (int i = 0; i < list.size(); i++) {
            push(list.get(i));
        }
    }

    public static void main(String[] args) {
        LinkedStack<String> linkedStack = new LinkedStack<>();

        linkedStack.push("Hello 1");
        linkedStack.push("Hello 2");
        linkedStack.push("Hello 3");
        linkedStack.push("Hello 4");
        linkedStack.push("Hello 5");

        System.out.println(linkedStack);

        for (String s : linkedStack) {
            System.out.println(s);
        }

    }

    public LinkedStack() {
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr(this.root);
    }

    private class ListItr extends Itr implements ListIterator<E>{

        private int cursor2 = 0;


        public ListItr(Node<E> root) {
            super(root);
        }

        @Override
        public boolean hasPrevious() {
            for (int i = 0; i < cursor2; i++) {
                cursor = cursor.next;
            }
            cursor2--;
            return cursor != null;
        }

        @Override
        public E previous() {
            for (int i = 0; i < cursor2; i++) {
                cursor = cursor.next;
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
        public void set(E t) {
            cursor.element = t;

        }

        @Override
        public void add(E t) {
            for (int i = 0; i < cursor2; i++) {
                cursor = cursor.next;
            }
            Node<E> temp = new Node<>(t, cursor.next.next);
            cursor.next = temp;
        }
    }

    public void push(E e) {
        if (empty()) {
            this.root = new Node<>(e, null);
        } else {
            this.root = new Node<>(e, this.root);
        }
        size++;
    }

    @Override
    public E pop() {
        this.root = this.root.next;
        size--;
        return this.root.element;

    }

    @Override
    public E peek() {
        return this.root.element;
    }

    @Override
    public boolean empty() {
       return size == 0;
    }

    @Override
    public int search(E e) {
        Node<E> temp = this.root;
        for (int i = 0; i < size; i++) {
            if (temp.element.equals(e)){
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr(this.root);
    }

    private class Itr implements Iterator<E>{

        protected Node<E> cursor;

        public Itr(Node<E> root) {
            cursor = root;
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

        @Override
        public void remove() {

        }
    }


    private static class Node<E>{
        private E element;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    ", next=" + next +
                    '}';
        }
    }
    @Override
    public String toString() {
        Node temp = this.root;
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
}
