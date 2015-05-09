package map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SingleMapOpenImpl<K,V> implements SingleMap<K,V> {
    //Podría haber sido un vector de listas de Entry<K,V> sin que Entry tenga un puntero al siguiente
    private EntryNode<K,V>[] hashTable;
    private int overflowZoneSize;
    private double loadFactor;

    @SuppressWarnings("unchecked")
    public SingleMapOpenImpl(int hashTableSize, double loadFactor) {
        hashTable = new EntryNode[hashTableSize];
        this.overflowZoneSize = 0;
        this.loadFactor = loadFactor;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % hashTable.length;
    }

    @Override
    public void put(K key,V value) {
        if(key ==  null || value == null)
            throw new IllegalArgumentException();
        if(overflowZoneSize / hashTable.length > loadFactor)
            resizeTable(hashTable.length * 2);
        int keyIndex = getIndex(key);
        EntryNode<K,V> node = hashTable[keyIndex];
        while(node != null) {
            if(node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.tail;
        }
        hashTable[keyIndex] = new EntryNode<K,V>(key, value, hashTable[keyIndex]);
        overflowZoneSize++;
    }

    private void resizeTable(int newSize) {
        EntryNode[] oldHashTable = hashTable;
        hashTable = new EntryNode[newSize];
        overflowZoneSize = 0;

        for(EntryNode<K,V> node : oldHashTable) {
            while(node != null) {
                put(node.key, node.value);
            }
        }
    }

    @Override
    public V get(K key) {
        if(key ==  null)
            throw new IllegalArgumentException();
        EntryNode<K,V> node = hashTable[getIndex(key)];
        while(node != null) {
            if(node.key.equals(key))
                return node.value;
            node = node.tail;
        }
        return null;
    }

    @Override
    public void remove(K key) {
        if(key == null)
            throw new IllegalArgumentException();
        EntryNode<K,V> node = hashTable[getIndex(key)];
        EntryNode<K,V> prev = node;

        while(node != null) {
            if(node.key.equals(key)) {
                overflowZoneSize--;
                prev.tail = node.tail;
                if(prev == node)
                    hashTable[getIndex(key)] = prev.tail;
                return;
            }
            prev = node;
            node = node.tail;
        }

    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<K>();

        for(EntryNode<K,V> node : hashTable) {
            while(node != null) {
                keySet.add(node.key);
                node = node.tail;
            }
        }
        return keySet;
    }

    @Override
    public Collection<V> values() {
        Collection<V> valuesCollection = new ArrayList<V>(); //Para que sea mas rapido al acceder a los elementos

        for(EntryNode<K,V> node : hashTable) {
            while(node != null) {
                valuesCollection.add(node.value);
                node = node.tail;
            }
        }
        return valuesCollection;
    }

    @Override
    public int size() {
        return overflowZoneSize;
    }

    public void print() {
        for(EntryNode<K,V> node : hashTable) {
            System.out.print("NODE: " + node);
            if(node != null) {
                System.out.print("\tKEY: " + node.key + "\tVALUE: " + node.value);
            }
            System.out.println();
        }
    }

    public class EntryNode<K,V> {
        private K key;
        private V value;
        private EntryNode<K,V> tail;

        public EntryNode(K key, V value, EntryNode<K,V> tail) {
            this.key = key;
            this.value = value;
            this.tail = tail;
        }
    }
}