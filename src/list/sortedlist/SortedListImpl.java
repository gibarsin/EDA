package list.sortedlist;

import java.util.Comparator;

/**
 * Created by Gonzalo on 24/04/2015.
 */
public class SortedListImpl<T> implements SortedList<T> {
    private Node<T> root;
    private Node<T> lastAdded;
    private Comparator<? super T> cmp;

    public SortedListImpl(Comparator<? super T> cmp) {
        this.cmp = cmp;
    }

    @Override
    public void add(T elem) {
        if(elem == null)
            throw new IllegalArgumentException();
        root = addRec(root, elem);
    }

    private Node<T> addRec(Node<T> node, T elem) {
        if(node == null) {
            Node<T> newNode = new Node(elem, lastAdded);
            lastAdded = newNode;
            return newNode;
        }
        int res = cmp.compare(node.head, elem);
        if(res >= 0) {
            Node<T> newNode = new Node(elem, lastAdded);
            newNode.next = node;
            node.prev = newNode;
            lastAdded = newNode;
            return newNode;
        } else {
            node.next = addRec(node.next, elem);
            node.next.prev = node;
            return node;
        }
    }

    @Override
    public void undo() {
        if(lastAdded != null) {
            if(lastAdded.next != null)
                lastAdded.next.prev = lastAdded.prev;
            if(lastAdded.prev != null) {
                lastAdded.prev.next = lastAdded.next;
            } else {
                root = lastAdded.next;
            }
            lastAdded = lastAdded.last;
        }
    }

    @Override
    public void print() {
        //printDebug();

        Node<T> curr = root;

        while(curr != null) {
            System.out.print(curr.head + " -> ");
            curr = curr.next;
        }
        System.out.println();
    }

    private void printDebug() {
        Node<T> curr = root;

        while(curr != null) {
            System.out.println(curr.prev + " <- " + curr + " -> " + curr.next);
            curr = curr.next;
        }
        System.out.println("---------------------------------------------------");
    }

    private static class Node<T> {
        private T head;
        private Node<T> next, prev, last;

        public Node(T elem, Node<T> last) {
            this.head = elem;
            this.last = last;
        }
    }
}
