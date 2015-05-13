package list.maplist;

import java.util.Iterator;

public class MapList<S,T> implements Iterable<T> {
    private Node<S,T> first;

    public void add(S key, T value) {
        if(key == null || value == null)
            throw new IllegalArgumentException();
        first = addRec(first, key, value);
    }

    private Node<S, T> addRec(Node<S,T> node, S key, T value) {
        if(node == null)
            return new Node<S, T>(key, value);
        if(node.key.equals(key))
            node.value = value;
        else
            node.tail = addRec(node.tail, key, value);
        return node;
    }

    public T get(S key) {
        if(key == null)
            throw new IllegalArgumentException();

        Node<S,T> curr = first;
        Node<S,T> prev = null;
        boolean found = false;

        while(!found && curr!= null) {
            if(curr.key.equals(key)) {
                found = true;
            } else {
                prev = curr;
                curr = curr.tail;
            }
        }
        if(curr == null)
            return null;
        if(prev != null) { //No era el primero
            prev.tail = curr.tail;
            curr.tail = first;
            first = curr;
        }
        return first.value;
    }

    public void print() {
        for(T each : this) {
            System.out.print(each + " ");
        }
        System.out.println();
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<S,T> curr = first;
            public boolean hasNext() {
                return curr != null;
            }

            public T next() {
                T value = curr.value;
                curr = curr.tail;
                return value;
            }
        };
    }

    private static class Node<S,T> {
        private S key;
        private T value;
        private Node<S,T> tail;

        public Node(S key, T value) {
            this.key = key;
            this.value = value;
        }
    }
}
