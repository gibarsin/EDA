package map.multimap;

import java.util.Collection;
import java.util.Set;

public interface MultiMap<K,V> {
    //Agrega un único valor a la colección de valores de Key
    void put(K key, V value);

    //Agrega todos los elementos a la colección de valores de Key
    void putAll(K key, Collection<V> values);

    //Reemplaza el valor anterior dentro de la colección de valores de Key por el nuevo valor
    void replace(K key, V oldValue, V newValue);

    //Devuelve un Set con todas las Keys del Mapa
    Set<K> keySet();

    //Devuelve una colección con todos los valores del Mapa
    Collection<V> values();

    //Devuelve la colección de valores de Key
    Collection<V> values(K key);

    //Remueve determinado elemento de la colección de valores de Key
    void remove(K key, V value);

    //Remueve la colección completa de valores de Key
    void removeAll(K key);

    //Devuelve el tamaño actual del Mapa
    int size();
}
