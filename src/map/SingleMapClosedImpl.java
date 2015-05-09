package map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class SingleMapClosedImpl<K,V> implements SingleMap<K,V> {
    private EntryNode<K,V>[] hashTable;
    private int currSize;

    @SuppressWarnings("unchecked")
    public SingleMapClosedImpl(int initSize) {
        this.hashTable = new EntryNode[initSize];
        this.currSize = 0;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % hashTable.length;
    }

    private void resizeTable(int newSize) {
        EntryNode[] oldHashTable = hashTable;
        hashTable = new EntryNode[newSize];

        for(EntryNode<K,V> node : oldHashTable) {
            put(node.key, node.value);
        }
    }

    @Override
    public void put(K key, V value) {
        EntryNode<K,V> node = new EntryNode<K,V>(key, value);


    }

    @Override
    public V get(K key) {
        if(key == null)
            throw new IllegalArgumentException();
        EntryNode<K,V> node;
        int currIndex = getIndex(key);
        boolean cycleComplete = false;

        while(!cycleComplete) {
            node = hashTable[currIndex];
            if(node.key.equals(key)) {
                if(!node.removed)
                    return node.value;
                else
                    return null;
            }
            currIndex = (currIndex+1)%currSize;
            if(currIndex == getIndex(key))
                cycleComplete = true;
        }
        return null;
    }

    @Override
    public void remove(K key) {
        // TODO Auto-generated method stub

    }

    @Override
    public Set<K> keySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<V> values() {
        Collection<V> valuesCollection = new ArrayList<V>();

        for(EntryNode<K,V> node : hashTable) {
            if(node != null)
                valuesCollection.add(node.value);
        }
        return valuesCollection;
    }

    @Override
    public int size() {
        return currSize;
    }

    public class EntryNode<K,V> {
        private K key;
        private V value;
        private boolean removed;

        public EntryNode(K key, V value) {
            this.key = key;
            this.value = value;
            removed = false;
        }
    }

}
