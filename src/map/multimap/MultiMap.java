package map.multimap;

import java.util.Collection;
import java.util.Set;

public interface MultiMap<K,V> {
    //Agrega un �nico valor a la colecci�n de valores de Key
    void put(K key, V value);

    //Agrega todos los elementos a la colecci�n de valores de Key
    void putAll(K key, Collection<V> values);

    //Reemplaza el valor anterior dentro de la colecci�n de valores de Key por el nuevo valor
    void replace(K key, V oldValue, V newValue);

    //Devuelve un Set con todas las Keys del Mapa
    Set<K> keySet();

    //Devuelve una colecci�n con todos los valores del Mapa
    Collection<V> values();

    //Devuelve la colecci�n de valores de Key
    Collection<V> values(K key);

    //Remueve determinado elemento de la colecci�n de valores de Key
    void remove(K key, V value);

    //Remueve la colecci�n completa de valores de Key
    void removeAll(K key);

    //Devuelve el tama�o actual del Mapa
    int size();
}
