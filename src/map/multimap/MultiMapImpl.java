package map.multimap;

import java.util.Collection;
import java.util.Set;

public class MultiMapImpl<K,V> implements MultiMap<K,V> {
    @Override
    public void put(K key, V value) {

    }

    @Override
    public void putAll(K key, Collection<V> values) {

    }

    @Override
    public void replace(K key, V oldValue, V newValue) {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Collection<V> values(K key) {
        return null;
    }

    @Override
    public void remove(K key, V value) {

    }

    @Override
    public void removeAll(K key) {

    }

    @Override
    public int size() {
        return 0;
    }
}
