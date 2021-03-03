package by.bookstore.list;

import java.util.Iterator;
import java.util.ListIterator;

public class DeqLinkedList<T> implements List<T> {
    private int size;
    private Node<T> head;
    private Node<T> tail;

    public static void main(String[] args) {
        List<String> stringList = new DeqLinkedList<>();
        stringList.add("Hello 1");
        stringList.add("Hello 2");
        stringList.add("Hello 3");
        stringList.add("Hello 4");
        stringList.add("Hello 5");


//        System.out.println(stringMyList.get(0));

        stringList.add(0, "Hello 33");

//        stringMyList.remove(2);
        System.out.println(stringList);
    }


    @Override
    public boolean add(T o) {
        if (isEmpty()) {
            Node<T> tNode = new Node<>(o, null, null);
            this.head = tNode;
            this.tail = tNode;
        } else {
            Node<T> temp = this.tail;
            Node<T> next = new Node<>(o, null, this.tail);
            temp.next = next;
            this.tail = next;
        }
        size++;
        return false;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    // FIXME: 27.11.2020 !
    @Override
    public void remove(int index) {
        int i = size / 2;
        if (index <= i) {
            Node<T> temp = this.head;
            for (int j = 0; j < size; j++) {
                if (j == index) {
                    Node<T> right = temp.next;
                    Node<T> left = temp.prev;
                    right.prev = left;
                    if (left != null) {
                        left.next = right;
                    } else {
                        head = right;
                    }
                }
                temp = temp.next;
            }
        } else {
            Node<T> temp = this.tail;
            for (int j = size - 1; j > 0; j--) {
                if (j == index) {
                    Node<T> right = temp.next;
                    Node<T> left = temp.prev;
                    if (right != null) {
                        right.prev = left;
                    } else {
                        left.next = right;
                        tail = left;
                    }
                    break;
                }
                temp = temp.prev;
            }
        }
        size--;
    }


    @Override
    public void remove(T o) {
        Node<T> temp = this.head;
        for (int i = 0; i < size; i++) {
            if (temp.element.equals(o)) {
                Node<T> right = temp.next;
                Node<T> left = temp.prev;
                right.prev = left;
                left.next = right;
                size--;
                break;
            }
            temp = temp.next;
        }
    }


    @Override
    public T get(int index) {
        int i = size / 2;
        if (index <= i) {
            Node<T> temp = this.head;
            for (int j = 0; j < size - 1; j++) {
                if (j == index) {
                    return temp.element;
                }
                temp = temp.next;
            }
        } else {
            Node<T> temp = this.tail;
            for (int j = size - 1; j > 0; j--) {
                if (j == index) {
                    return temp.element;
                }
                temp = temp.prev;
            }
        }
        return null;
    }

    @Override
    public T set(int index, T o) {
        int i = size / 2;
        if (index <= i) {
            Node<T> temp = this.head;
            for (int j = 0; j < size; j++) {
                if (j == index) {
                    T old = temp.element;
                    temp.element = o;
                    return old;
                }
                temp = temp.next;
            }
        } else {
            Node<T> temp = this.tail;
            for (int j = size - 1; j > 0; j--) {
                if (j == index) {
                    T old = temp.element;
                    temp.element = o;
                    return old;
                }
                temp = temp.prev;
            }
        }
        return null;
    }

    @Override
    public void add(int index, T o) {
        int i = size / 2;
        Node<T> temp = this.head;
        for (int j = 0; j < size; j++) {
            if (j == index) {
                Node<T> left = temp.prev;
                Node<T> tNode = new Node<>(o, temp, left);
                if (left == null) {
                    head = tNode;
                } else {
                    left.next = tNode;
                }
                temp.prev = tNode;
            }
            temp = temp.next;
        }
        size++;
    }

    @Override
    public int indexOf(T o) {
        Node<T> temp = this.head;
        for (int i = 0; i < size; i++) {
            if (temp.equals(o)) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public boolean contains(T o) {
        Node<T> temp = this.head;
        for (int i = 0; i < size; i++) {
            if (temp.equals(o)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public T[] toArray() {
        Node<T> temp = this.head;
        Object[] o = new Object[size];
        for (int i = 0; i < size; i++) {
            o[i] = temp;
            temp = temp.next;
        }
        return (T[]) o;
    }

    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    @Override
    public void trimToSize() {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr(this.head);
    }

    private static class Node<T> {
        private T element;
        private Node<T> next;
        private Node<T> prev;

        public Node(T element, Node<T> next, Node<T> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    ", next=" + next +
//                    ", prev=" + prev +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MyDeqLinkedList{" +
                "size=" + size +
                ", head=" + head +
                ", tail=" + tail +
                '}';
    }
    private class Itr implements Iterator<T>{

        public Itr(Node<T> head) {
            this.cursor = head;
        }

         private  Node<T> cursor;



        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public T next() {
            T element = cursor.element;
            cursor = cursor.next;
            return element;
        }

        @Override
        public void remove() {
            Node<T> temp = cursor;
            cursor.next = cursor.next.next;
            cursor = temp.next;
        }
    }
}
