package by.bookstore.map;

public interface Map<K, V> {
    V put(K key, V value);

    V get(K key);

    V remove(K key);

    int size();

    boolean isEmpty();

    V[] values();

    K[] keys();

    Entry<K, V>[] entries();

    boolean containsKey(K key);
    boolean containsValue(V value);

    interface Entry<K, V>{
        K getKey();
        V getValue();
        V setValue(V value);
    }
}
