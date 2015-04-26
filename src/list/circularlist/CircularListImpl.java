package list.circularlist;

import java.util.NoSuchElementException;

public class CircularListImpl<T> implements CircularList<T> {
    private Node<T> last;
    private Node<T> current;

    public CircularListImpl() {

    }

    private CircularListImpl(Node<T> node) {
        last = node;
        current = last;
    }

    public void addLast(T elem) {
        Node<T> newNode = new Node<T>(elem);
        Node<T> aux = last;

        if(last != null) {
            newNode.next = last.next;
            last.next = newNode;
            last = newNode;
        } else {
            last = newNode;
            last.next = last;
        }
        if(aux == current) {
            current = last;
        }
    }

    public T getNext() {
        if(last == null) {
            throw new NoSuchElementException();
        } else {
            current = current.next;
            return current.head;
        }
    }

    public void reset() {
        current = last;
    }

    public CircularList<T> split() {
        if(current == last) {
            throw new IllegalStateException();
        }
        Node<T> first = current.next;
        current.next = last.next;
        last.next = first;
        return new CircularListImpl<T>(last);
    }

    public void print() {
        for(int i=0; i < 10; i++) {
            System.out.print(getNext() + " ");
        }
        System.out.println();
    }

    private static class Node<T> {
        private T head;
        private Node<T> next;

        public Node(T head) {
            this.head = head;
        }
    }
}
