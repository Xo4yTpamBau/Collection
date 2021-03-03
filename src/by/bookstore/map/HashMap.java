package by.bookstore.map;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {

    private int size;

    private Node[] array = new Node[16];

    private List<Entry<K, V>> entries = new ArrayList<>();

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "Hello 1");
        map.put("2", "Hello 2");
        map.put("3", "Hello 3");
        map.put("4", "Hello 4");
        map.put("5", "Hello 5");
        map.put("6", "Hello 6");
        map.put("7", "Hello 7");
        map.put("8", "Hello 8");
        map.put("9", "Hello 9");
        map.put("10", "Hello 10");
        map.put("11", "Hello 11");
        map.put("12", "Hello 12");
        map.put("13", "Hello 13");
        map.put("14", "Hello 14");
        map.put("15", "Hello 15");

        System.out.println(map);

        map.put("15", "Hello 155");

        System.out.println(map);
    }

    @Override
    public V put(K key, V value) {
        int i = key.hashCode();
        int index = i % array.length;
        V old = null;
        if (array[index] == null) {
            array[index] = new Node<>(i, key, value, null);
            size++;
        } else {
            Node<K, V> node = array[index];
            while (true) {
                if (node.getKey().equals(key)){
                    old = node.getValue();
                    node.setValue(value);
                    break;
                }
                if (node.next == null) {
                    node.next = new Node<>(i, key, value, null);
                    size++;
                    break;
                }
                node = node.next;
            }
        }
        entries.add(new Node<>(i, key, value, null));
        for (int j = 0; j < size; j++) {
            if (entries.get(j).getKey() == key) {
                old = entries.get(j).getValue();
                entries.get(j).setValue(value);
            }
        }
        return old;
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (entries.get(i).getKey() == key) {
                return entries.get(i).getValue();
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int index = key.hashCode() % 10;
        if (array[index].next == null) {
            array[index] = null;
            size--;
        } else {
            Node<K, V> temp = array[index];
            Node old;
            for (int i = 0; i < size; i++) {
                if (temp.getKey() == key) {
                    old = temp;
                    temp = null;
                    old.next = old.next.next;
                    size--;
                }
                if (temp.next == null) break;
                temp = temp.next;
            }
        }
        entries.remove(new Node<>(0, key, null, null));
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
        Entry<K, V>[] arrayEntries = new Entry[size];
        for (int i = 0; i < size; i++) {
            arrayEntries[i] = entries.get(i);
        }
        return arrayEntries;
    }

    @Override
    public boolean containsKey(K key) {
        for (int i = 0; i < size; i++) {
            if (entries.get(i).getKey().equals(key))
                return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < size; i++) {
            if (entries.get(i).getValue().equals(value))
                return true;
        }
        return false;
    }

    @Override
//    public String toString() {
//        return "HashMap{" +
//                "size=" + size +
//                ", array=" + Arrays.toString(array) +
////                ", entries=" + entries +
//                '}';
//    }
        public String toString() {
        StringBuilder v = new StringBuilder("[");
        v.append("size = ").append(size).append(" , ");
        for (int i = 0; i < size - 1; i++) {
            v.append(entries.get(i).hashCode()).append(" ").append(entries.get(i).getKey()).append(" = ").append(entries.get(i).getValue()).append(", ");
        }
        v.append(entries.get(size - 1).hashCode()).append(" ").append(entries.get(size - 1).getKey()).append(" = ").append(entries.get(size - 1).getValue()).append("] ");
        return v.toString();
    }

    private static class Node<K, V> implements Map.Entry<K, V> {
        int hash;
        K key;
        V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.hash = hash;
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "hash = " + hash +
                    ", key = " + key +
                    ", value = " + value +
                    ", next = " + next +
                    '}';
        }
    }
}

