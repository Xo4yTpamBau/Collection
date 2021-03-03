package by.bookstore.map;

import java.util.Arrays;

public class ArrayMap<K, V> implements Map<K, V> {

    private int size;
    private Node[] entries = new Node[10];

    public static void main(String[] args) {
        String a = "Hello 1";
        int i = a.hashCode();
        int size = 16;

        int index = i % size;

        System.out.println(Math.abs(index));
    }


    @Override
    public V put(K key, V value) {
        for (int i = 0; i < size; i++) {
            if (entries[i] == null) break;
            if (entries[i].getKey().equals(key)){
                Node<K, V> old = entries[i];
                entries[i].setValue(value);
                return (V) old;
            }
        }
        Node<K, V> temp = new Node<>(key,value);
        entries[size] = temp;
        size++;
        return null;
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (entries[i].getKey().equals(key)){
                return (V) entries[i].value;
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        for (int i = 0; i < size; i++) {
            if (entries[i].getKey().equals(key)){
                entries[i] = null;
                for (int j = i; j < size; j++) {
                    entries[j] = entries[j + 1];
                }
                size--;
            }
        }
        return null;
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
        Object[] val = new Object[size];
        for (int i = 0; i < size; i++) {
            val[i] = entries[i].getValue();
        }
        return (V[]) val;
    }

    @Override
    public K[] keys() {
        Object[] key = new Object[size];
        for (int i = 0; i < size; i++) {
            key[i] = entries[i].getKey();
        }
        return (K[]) key;
    }

    @Override
    public Entry<K, V>[] entries() {
        Entry<K,V>[] newArray = new Entry[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = entries[i];
        }
        return newArray;
    }

    @Override
    public boolean containsKey(K key) {
        for (int i = 0; i < size; i++) {
            if (entries[i].getKey().equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < size; i++) {
            if (entries[i].getValue().equals(value)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "[ " + "size = " + size + ", " + Arrays.toString(entries) + ']';
    }

    private static class Node<K, V> implements Map.Entry<K, V> {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
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
            V old = this.value;
            this.value = value;
            return old;
        }

        @Override
        public String toString() {
            return  key + " = " + value;
        }
    }
}
