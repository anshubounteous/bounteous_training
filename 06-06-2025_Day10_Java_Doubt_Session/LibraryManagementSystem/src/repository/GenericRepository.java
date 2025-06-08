package repository;

import java.util.*;

public class GenericRepository<K, V> {
    private final Map<K, V> store = new HashMap<>();

    public void add(K key, V value) {
        store.put(key, value);
    }

    public Optional<V> get(K key) {
        return Optional.ofNullable(store.get(key));
    }

    public void update(K key, V value) {
        if (store.containsKey(key)) {
            store.put(key, value);
        }
    }

    public void delete(K key) {
        store.remove(key);
    }

    public Map<K, V> getAll() {
        return store;
    }

    public boolean containsKey(K key) {
        return store.containsKey(key);
    }
}
