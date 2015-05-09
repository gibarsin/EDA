package map;

import java.util.*;

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
        currSize = 0; //Si no lo colocase, en la nueva tabla solo podría colocar  la mitad de los elementos de la anterior tabla y se volvería a crear una nueva

        for(EntryNode<K,V> node : oldHashTable)
            if(node != null && !node.removed)
                put(node.key, node.value);
    }

    @Override
    public void put(K key, V value) {
        if(key == null)
            throw new IllegalArgumentException();
        EntryNode<K,V> curr;
        int cutIndex = getIndex(key);
        int currIndex = cutIndex;
        boolean cycleComplete = false;

        if(currSize != hashTable.length) {
            while (!cycleComplete) { //Quizas no justifica tener un booleano ya que nunca se cumpliria la condicion, saldria antes
                curr = hashTable[currIndex];
                if (curr == null) {
                    hashTable[currIndex] = new EntryNode<K, V>(key, value);
                    currSize++;
                    return;
                } else if(curr.key.equals(key)) {
                    if(curr.removed) {
                        curr.removed = false;
                        currSize++;
                    }
                    curr.value = value;
                    return;
                } else if (curr.removed) { //La key no es igual pero está removido
                    hashTable[currIndex] = new EntryNode<K, V>(key, value);
                    currSize++;
                    return;
                } else {
                    currIndex = (currIndex + 1) % hashTable.length;
                    if (currIndex == cutIndex)
                        cycleComplete = true;
                }
            }
        }
        resizeTable(currSize * 2);
        put(key, value);
        currSize++;
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
            if(node != null && node.key.equals(key)) {
                if(!node.removed)
                    return node.value;
                else
                    return null;
            }
            currIndex = (currIndex+1)%hashTable.length;
            if(currIndex == getIndex(key))
                cycleComplete = true;
        }
        return null;
    }

    @Override
    public void remove(K key) {
        if(key == null)
            throw new IllegalArgumentException();
        EntryNode<K,V> node;
        int currIndex = getIndex(key);
        boolean cycleComplete = false;

        while(!cycleComplete) {
            node = hashTable[currIndex];
            if(node != null && node.key.equals(key)) {
                if(!node.removed)
                    currSize--;
                node.removed = true;
                return;
            } else {
                currIndex = (currIndex+1)%hashTable.length;
                if(currIndex == getIndex(key))
                    cycleComplete = true;
            }
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<K>();

        for(EntryNode<K,V> node : hashTable)
            if(node != null && !node.removed)
                keySet.add(node.key);
        return keySet;
    }

    @Override
    public Collection<V> values() {
        Collection<V> valuesCollection = new ArrayList<V>();

        for(EntryNode<K,V> node : hashTable) {
            if(node != null && !node.removed)
                valuesCollection.add(node.value);
        }
        return valuesCollection;
    }

    @Override
    public int size() {
        return currSize;
    }

    @Override
    public void print() {
        for(EntryNode<K,V> node : hashTable) {
            System.out.print("NODE: " + node);
            if(node != null) {
                System.out.print(" \t\tKEY: " + node.key + "\t\tVALUE: " + node.value);
            }
            System.out.println();
        }
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
