package by.bookstore.list;

import java.util.Iterator;
import java.util.ListIterator;

public class LinkedList<T> implements List<T> {
    private int size;
    private Node<T> root;

    public LinkedList(){
    }

    public LinkedList(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }


    public static void main(String[] args) {
        LinkedList<String> myLinkedList = new LinkedList<>();
        myLinkedList.add("Hello 1");
        myLinkedList.add("Hello 2");
        myLinkedList.add("Hello 3");
        myLinkedList.add("Hello 4");
        myLinkedList.add("Hello 5");

        for (String s : myLinkedList) {
            System.out.println(s);
        }

//        myLinkedList.clear();

//        System.out.println(myLinkedList);

//        MyLinkedList m = new MyLinkedList(myLinkedList);

//        System.out.println(m);


//        myLinkedList.remove("Hello 3");

//        myLinkedList.add(2, "Hello 2.5");

//        System.out.println(myLinkedList.set(2, "Hello 33"));

//        System.out.println(myLinkedList.contains("Hello 4"));
//        System.out.println(myLinkedList.contains("Hello 5"));

//        System.out.println(myLinkedList.toString());
    }


    @Override
    public boolean add(T o) {
        if (isEmpty()){
            this.root = new Node<T>(o, null);
            size++;
        } else {
            Node<T> temp = this.root;
            while (true){
                if (temp.next == null){
                    temp.next = new Node<>(o, null);
                    size++;
                    return true;
                }
                temp = temp.next;
            }
        }
        return false;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListItr(this.root);
    }

    private class ListItr extends Itr implements ListIterator<T>{

        private int cursor2 = 0;


        public ListItr(Node<T> root) {
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
        public T previous() {
            for (int i = 0; i < cursor2; i++) {
                cursor = cursor.next;
            }
            cursor2--;
            return (T) cursor;
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
        public void set(T t) {
            cursor.element = t;

        }

        @Override
        public void add(T t) {
            for (int i = 0; i < cursor2; i++) {
                cursor = cursor.next;
            }
            Node<T> temp = new Node<>(t, cursor.next.next);
            cursor.next = temp;
        }
    }

    @Override
    public void remove(int index) {
        Node<T> temp = this.root;
        for (int i = 0; i < size; i++) {
            if (i == index - 1){
                Node<T> removeNode = temp.next;
                Node<T> right = temp.next.next;
                temp.next = right;
                removeNode.next = null;
                removeNode.element = null;
                size--;
                break;
            }
            temp = temp.next;
        }
    }

    @Override
    public void remove(T o) {
        Node<T> temp = this.root;
        for (int i = 0; i < size; i++) {
            if (temp.next.element.equals(o)){
                Node<T> removeNode = temp.next;
                Node<T> right = temp.next.next;
                temp.next = right;
                removeNode.next = null;
                removeNode.element = null;
                size--;
                break;
            }
            temp = temp.next;
        }
    }



    @Override
    public T get(int index) {
        Node<T> temp = this.root;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return (T) temp.element;
            }
            temp = temp.next;
        }
        return null;
    }

    @Override
    public T set(int index, T o) {
        Node<T> temp = this.root;
        for (int i = 0; i < size; i++) {
            if (i == index){
                Object old = temp.element;
                temp.element = o;
                return (T) old;
            }
            temp = temp.next;
        }
        return null;
    }

    @Override
    public void add(int index, T o) {
        Node<T> temp = this.root;
        for (int i = 0; i < size; i++) {
            if (i == index - 1){
                temp.next = new Node<>(o, temp.next);
                size++;
            }
            temp = temp.next;
        }
    }

    @Override
    public int indexOf(T o) {
        Node<T> temp = this.root;
        for (int i = 0; i < size; i++) {
            if (temp.element.equals(o)){
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public boolean contains(T o) {
        Node<T> temp = this.root;
        while (temp != null){
            if (temp.element.equals(o)){
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public T[] toArray() {
        Node<T> temp = this.root;
        Object[] a = new Object[size];
        for (int i = 0; i < size; i++) {
          a[i] = temp.element;
          temp = temp.next;
        }
        return (T[]) a;
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
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
        return new Itr(this.root);
    }

    private static class Node<T>{
        private T element;
        private Node<T> next;

        public Node(T element, Node<T> next) {
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

    private class Itr implements Iterator<T>{

        protected Node<T> cursor;

        public Itr(Node<T> root) {
            this.cursor = root;
        }

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
            cursor = null;
            cursor = temp.next;
        }
    }
}
