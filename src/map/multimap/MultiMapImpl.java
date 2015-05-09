package map.multimap;

import map.singlemap.SingleMap;
import map.singlemap.openhashing.SingleMapOpenImpl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MultiMapImpl<K,V> implements MultiMap<K,V> {
    SingleMap<K,Collection<V>> map;

    public MultiMapImpl() {
        map = new SingleMapOpenImpl<K, Collection<V>>(10, 2);
    }

    @Override
    public void put(K key, V value) {
        Collection<V> values = map.get(key);

        if(values == null) {
            Collection<V> set = new HashSet<V>();
            set.add(value);
            map.put(key, set);
        } else {
            values.add(value);
        }
    }

    @Override
    public void putAll(K key, Collection<V> values) {
        Collection<V> valuesKey = map.get(key);

        if(valuesKey == null)
            map.put(key, values);
        else
            valuesKey.addAll(values);
    }

    @Override
    public void replace(K key, V oldValue, V newValue) {
        Collection<V> values = map.get(key);

        if(values.contains(oldValue)) {
            values.remove(oldValue);
            values.add(newValue);
        }
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new HashSet<V>();

        for(Collection<V> collection : map.values()) {
            values.addAll(collection);
        }
        return values;
    }

    @Override
    public Collection<V> values(K key) {
        return map.get(key);
    }

    @Override
    public void remove(K key, V value) {
        Collection<V> values = map.get(key);

        if (values != null) {
            values.remove(value);
            if (values.isEmpty()) //Si era el último elemento de la colección
                map.remove(key);
        }
    }

    @Override
    public void removeAll(K key) {
        map.remove(key);
    }

    @Override
    public int size() {
        return map.size();
    }
}
