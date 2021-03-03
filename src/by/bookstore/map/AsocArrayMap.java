package by.bookstore.map;

import java.util.Arrays;

public class AsocArrayMap<K, V> implements Map<K, V> {
    public static void main(String[] args) {
        AsocArrayMap<String, String> arrayMap = new AsocArrayMap<>();
        arrayMap.put("1", "Hello 1");
        arrayMap.put("2", "Hello 2");
        arrayMap.put("3", "Hello 3");
        arrayMap.put("4", "Hello 4");
        arrayMap.put("5", "Hello 5");
        System.out.println(arrayMap);

        arrayMap.remove("3");

        System.out.println(arrayMap);

        System.out.println(Arrays.toString(arrayMap.entries()));

//        String s = arrayMap.get("9");

//        System.out.println(s);
    }

    private int size;
    private Object[] arrayKey = new Object[10];
    private Object[] arrayValue = new Object[10];


    @Override
    public V put(K key, V value) {
        for (int i = 0; i < size; i++) {
            if (arrayKey[i].equals(key)) {
                Object old = arrayValue[i];
                arrayValue[i] = value;
                return (V) old;
            }
        }
        arrayKey[size] = key;
        arrayValue[size] = value;
        size++;
        return value;
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (arrayKey[i].equals(key)) {
                return (V) arrayValue[i];
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        for (int i = 0; i < size; i++) {
            if (arrayKey[i].equals(key)) {
                arrayValue[i] = null;
                arrayKey[i] = null;
                size--;
                for (int j = i; j <= size ; j++) {
                    arrayKey[j] = arrayKey[j + 1];
                    arrayValue[j] = arrayValue[j + 1];

                }
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
        return (V[]) arrayValue;
    }

    @Override
    public K[] keys() {
        return (K[]) arrayKey;
    }

    @Override
    public Entry<K, V>[] entries() {
        Node[] newArray = new Node[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = new Node(arrayKey[i], arrayValue[i]);

        }
        return newArray;
    }

    @Override
    public boolean containsKey(K key) {
        for (int i = 0; i < size; i++) {
            if (arrayKey[i].equals(key))
                return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < size; i++) {
            if (arrayValue[i].equals(value))
                return true;
        }
        return false;
    }

    @Override
    public String toString() { //[1 = Hello 1, 2 = Hello 2, ..]
            StringBuilder v = new StringBuilder("[");
            v.append("size = ").append(size).append(" , ");
            for (int i = 0; i < arrayKey.length; i++) {
                if (arrayKey[i] == null) break;
                if (arrayKey[i + 1] != null ) {
                    v.append(arrayKey[i].toString()).append(" = ").append(arrayValue[i].toString()).append(", ");
                } else v.append(arrayKey[i].toString()).append(" = ").
                        append(arrayValue[i].toString());
            }
            v.append("]");
            return v.toString();
        }




    private static class Node<K, V> implements Map.Entry<K, V>{
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
            this.value = value;
            return null;
        }

        @Override
        public String toString() {
            return  key + " = " + value;
        }
    }
}
