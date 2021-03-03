package by.bookstore.tree;

import java.util.Comparator;

public class LinkedTreeSet<E> implements TreeSet<E> {

    Node<E> root;
    private int size;
    private final Comparator<E> comparator;

    public LinkedTreeSet(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public static void main(String[] args) {
        LinkedTreeSet<Integer> treeSet = new LinkedTreeSet<>(Integer::compareTo);

        treeSet.add(100);
        treeSet.add(200);
        treeSet.add(150);
        treeSet.add(300);
        treeSet.add(50);
        treeSet.add(400);
        treeSet.add(350);
        treeSet.add(600);
        treeSet.add(370);
        treeSet.add(380);
        treeSet.add(390);


        System.out.println(treeSet);
        System.out.println(treeSet.remove(400));
        System.out.println(treeSet);
        treeSet.clear();
        System.out.println(treeSet);

    }

    @Override
    public void add(E e) {
        if (isEmpty()) {
            this.root = new Node<>(null, null, null, e);
        } else {
            Node<E> temp = this.root;
            while (true) {
                if (comparator.compare(e, temp.element) < 0) {
                    if (temp.left == null) {
                        temp.left = new Node<>(temp, null, null, e);
                        break;
                    }
                    temp = temp.left;
                }
                if (comparator.compare(e, temp.element) > 0) {
                    if (temp.right == null) {
                        temp.right = new Node<>(temp, null, null, e);
                        break;
                    }
                    temp = temp.right;
                }
            }
        }
        size++;
    }

    @Override
    public E pollFirst() {
        Node<E> temp = this.root;
        while (true) {
            if (temp.left == null) {
                E old = temp.element;
                temp = temp.parent;
                temp.left = null;
                return (E) old;
            }
            temp = temp.left;
        }
    }


    @Override
    public E pollLast() {
        Node<E> temp = this.root;
        while (true) {
            if (temp.right == null) {
                E old = temp.element;
                temp = temp.parent;
                temp.right = null;
                return (E) old;
            }
            temp = temp.right;
        }
    }

    @Override
    public E first() {
        Node<E> temp = this.root;
        while (true) {
            if (temp.left == null) {
                return temp.element;
            }
            temp = temp.left;
        }
    }

    @Override
    public E last() {
        Node<E> temp = this.root;
        while (true) {
            if (temp.right == null) {
                return temp.element;
            }
            temp = temp.right;
        }
    }

    @Override
    public E remove(E e) {
        Node<E> temp = this.root;
        Node<E> old;
        Node<E> right;
        Node<E> parent;
        Node<E> left;
        while(true){
            if (comparator.compare(e, temp.element) == 0){
                old = temp;
                parent = temp.parent;
                right = temp.right;
                left = temp.left;
                if (temp.left == null && temp.right == null){
                    if (comparator.compare(e, parent.element) < 0){
                        parent.left = null;
                    } else{
                        parent.right = null;
                    }
                }
                if (temp.left == null ^ temp.right == null){
                    if (temp.left == null){
                        right.parent = parent;
                        if (comparator.compare(e, parent.element) < 0){
                            parent.left = temp.right;
                        } else{
                            parent.right = temp.right;
                        }
                    }
                    if (temp.right == null){
                        left.parent = parent;
                        if (comparator.compare(e, parent.element) < 0){
                            parent.left = temp.left;
                        } else{
                            parent.right = temp.left;
                        }
                    }
                }
                if (temp.left != null && temp.right != null){
                    while(left.left != null || left.right != null){
                        left = left.right;
                    }
                    left.parent = parent;
                    right.parent = left;
                    left.right = right;
                    if (comparator.compare(e, parent.element) < 0){
                        parent.left = temp.left;
                    } else{
                        parent.right = temp.left;
                    }

                }
                size--;
                return (E) old;
            }
            if (comparator.compare(e, temp.element) < 0){
                temp = temp.left;
            }
            if (comparator.compare(e, temp.element) > 0){
                temp = temp.right;
            }

        }
    }

    @Override
    public boolean contains(E e) {
        Node<E> temp = this.root;
        while (true) {
            if (temp == null) return false;
            if (comparator.compare(e, temp.element) == 0) {
                return true;
            }
            if (comparator.compare(e, temp.element) < 0) {
                temp = temp.left;
            }
            if (comparator.compare(e, temp.element) > 0) {
                temp = temp.right;
            }
        }
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
    public void clear() {
        root.right = null;
        root.left = null;
        root = null;
        size = 0;

    }

    private Node<E> remove2(Node<E> left){
      while(left.left != null && left.right != null){
         left = left.right;
      }
      return left;
    }

    @Override
    public String toString() {
        return "LinkedTreeSet{" +
                "root=" + root +
                ", size=" + size +
//                ", comparator=" + comparator +
                '}';
    }

    private static class Node<E> {
        Node<E> parent;
        Node<E> right;
        Node<E> left;
        E element;

        public Node(Node<E> parent, Node<E> right, Node<E> left, E element) {
            this.parent = parent;
            this.right = right;
            this.left = left;
            this.element = element;
        }


        @Override
        public String toString() {
            return "Node{" +
                    "right=" + right +
                    ", left=" + left +
                    ", element=" + element +
                    '}';
        }
    }
}

