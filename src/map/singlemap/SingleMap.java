package map.singlemap;

import java.util.Collection;
import java.util.Set;

public interface SingleMap<K,V> {
    /**
     * Agrega un par clave/valor a la tabla. Si la clave ya existe,
     * actualiza el valor.
     */
    void put(K key, V value);
    /**
     * Obtiene el valor asociado a una clave. Si no existe la clave retorna null.
     */
    V get(K key);
    /**
     * Elimina un par clave/valor de la tabla. Si la clave no existe no hace nada.
     */
    void remove(K key);
    /**
     * Retorna todas las claves insertadas en la tabla.
     */
    Set<K> keySet();
    /**
     * Retorna todos los valores insertados en la tabla.
     */
    Collection<V> values();
    /**
     * Obtiene la cantidad de pares clave/valor insertados en la tabla.
     */
    int size();
}