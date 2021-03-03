package by.bookstore.map;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TreeMap<K, V> implements Map<K,V> {
    private int size;
    private Entry<K, V> root;
    private Comparator<K> comparator;
    private List<Entry<K, V>> entries = new ArrayList<>();

    public TreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }


    public static void main(String[] args) {
        TreeMap<Integer, Integer> integerTreeMap = new TreeMap<>(Integer::compareTo);
        integerTreeMap.put(5, 5);
        integerTreeMap.put(1, 1);
        integerTreeMap.put(2, 2);
        integerTreeMap.put(8, 8);
        integerTreeMap.put(9, 9);
        integerTreeMap.put(7, 7);

        integerTreeMap.put(8, 88);

        System.out.println(integerTreeMap.entries);

        System.out.println(integerTreeMap);

        integerTreeMap.remove(7);
        System.out.println(integerTreeMap);
    }

    @Override
    public V put(K key, V value) {
        if (isEmpty()) {
            this.root = new Entry<>(key, value, null,null,null);
        }else{
            Entry<K, V> temp = this.root;
            while(true) {
                if (comparator.compare(key, temp.key) == 0){
                    temp.setValue(value);
                    break;
                }
                if (comparator.compare(key, temp.key) < 0) {
                    if (temp.left == null){
                        temp.left = new Entry<>(key, value, temp,null,null);
                        break;
                    }
                    temp = temp.left;
                }
                if (comparator.compare(key, temp.key) > 0){
                    if (temp.right == null){
                        temp.right = new Entry<>(key, value, temp,null, null);
                        break;
                    }
                    temp = temp.right;
                }
            }
        }
        entries.add(new Entry<>(key, value, null, null, null));
        Object old = new Object();
        for (int i = 0; i < size; i++) {
            if (entries.get(i).getKey().equals(key)) {
                old = entries.get(i).getValue();
                entries.get(i).setValue(value);
                return (V) old;
            }
        }
        size++;
        return value;
    }

    @Override
    public V get(K key) {
        Entry<K,V> temp = this.root;
        if (temp == null) return null;
        while(true){
            if (comparator.compare(key, temp.key) == 0){
                return temp.value;
            }
            if (comparator.compare(key, temp.key) < 0 ){
                if (temp.left == null) return null;
                temp = temp.left;
            }
            if (comparator.compare(key, temp.key) > 0){
                if (temp.right == null) return null;
                temp = temp.right;
            }
        }
    }

    @Override
    public V remove(K key) {
        Entry<K,V> temp = this.root;
        Entry<K, V> old;
        Entry<K, V> parent;
        Entry<K, V> right;
        Entry<K, V> left;
        while(true){
            if (comparator.compare(key, temp.key) == 0){
                old = temp;
                parent = temp.parent;
                right = temp.right;
                left = temp.left;
                if (temp.left == null && temp.right == null){
                    if (comparator.compare(key, parent.key) < 0){
                        parent.left = null;
                    } else{
                        parent.right = null;
                    }
                }
                if (temp.left == null ^ temp.right == null){
                    if (temp.left == null){
                        right.parent = parent;
                        if (comparator.compare(key, parent.key) < 0){
                            parent.left = temp.right;
                        } else{
                            parent.right = temp.right;
                        }
                    }
                    if (    temp.right == null){
                        left.parent = parent;
                        if (comparator.compare(key, parent.key) < 0){
                            parent.left = temp.left;
                        } else{
                            parent.right = temp.left;
                        }
                    }
                }
                if (temp.left != null && temp.right != null){


                    left.parent = parent;
                    right.parent = left;
                    left.right = right;
                    if (comparator.compare(key, parent.key) < 0){
                        parent.left = temp.left;
                    } else{
                        parent.right = temp.left;
                    }
                }
                size--;


                return (V) old;
            }
            if (comparator.compare(key, temp.key) < 0){
                temp = temp.left;
            }
            if (comparator.compare(key, temp.key) > 0){
                temp = temp.right;
            }
        }
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
    public V[] values() {
        Object[] value = new Object[size];
        for (int i = 0; i < size; i++) {
            value[i] = entries.get(i).getValue();
        }
        return (V[]) value;
    }

    @Override
    public K[] keys() {
        Object[] key = new Object[size];
        for (int i = 0; i < size; i++) {
            key[i] = entries.get(i).getKey();
        }
         return (K[]) key;
    }

    @Override
    public Entry<K, V>[] entries() {
        Entry<K,V>[] array = new Entry[size];
        for (int i = 0; i < size; i++) {
            array[i] = entries.get(i);
        }
        return  array;
    }

    @Override
    public boolean containsKey(K key) {
        for (int i = 0; i < size; i++) {
            if (entries.get(i).getKey().equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < size; i++) {
            if (entries.get(i).getValue().equals(value)){
                return true;
            }
        }
        return false;
    }

    private static class Node<K, V> implements Map.Entry<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return value;
        }
    }

    @Override
    public String toString() {
        return "TreeMap{" +
                "size=" + size +
                ", root=" + root +
//                ", comparator=" + comparator +
                '}';
    }

    private static class Entry<K, V> implements Map.Entry<K, V>{
        K key;
        V value;
        Entry<K, V> parent;
        Entry<K, V> left;
        Entry<K, V> right;

        public Entry(K key, V value, Entry<K, V> parent, Entry<K, V> left, Entry<K, V> right) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
//                    ", parent=" + parent +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
