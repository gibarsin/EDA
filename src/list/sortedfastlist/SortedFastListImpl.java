package list.sortedfastlist;

import java.util.Comparator;

public class SortedFastListImpl<T> implements SortedFastList<T> {
    private Node<T> root;
    private Comparator<? super T> cmp;

    public SortedFastListImpl(Comparator<? super T> cmp) {
        this.cmp = cmp;
    }

    public void add(T elem) {
        if(elem == null)
            throw new IllegalArgumentException();
        root = addRec(root, elem);
    }

    private Node<T> addRec(Node<T> curr, T elem) {
        if (curr == null) {
            return new Node(elem);
        }
        int res = cmp.compare(elem, curr.head);

        if(res < 0) {
            Node<T> n = new Node<T>(elem);
            n.shortNext = curr;
            n.longNext = curr.shortNext;
            return n;
        } else if(res > 0) {
            if(curr.longNext == null) {
                curr.shortNext = addRec(curr.shortNext, elem);
                curr.longNext = curr.shortNext.shortNext;
                return curr;
            } else {
                if(cmp.compare(elem, curr.longNext.head) > 0) {
                    addRec(curr.longNext, elem);
                    curr.shortNext.longNext = curr.longNext.shortNext;
                } else if(cmp.compare(elem, curr.longNext.head) < 0) {
                    curr.shortNext = addRec(curr.shortNext, elem);
                    curr.longNext = curr.shortNext.shortNext;
                    return curr;
                }
            }
        }
        return curr;
    }

    public void print() {
        printDebug();

//        Node<T> curr = root;
//
//        while(curr != null) {
//            System.out.print(curr.head + " -> ");
//            curr = curr.shortNext;
//        }
//        System.out.println();
    }

    private void printDebug() {
        Node<T> curr = root;

        while(curr != null) {
            System.out.println("CURRENT: " + curr + " SHORTNEXT: " + curr.shortNext + " LONGNEXT: " + curr.longNext);
            curr = curr.shortNext;
        }
        System.out.println("---------------------------------------------------");
    }

    private static class Node<T> {
        private T head;
        private Node<T> shortNext, longNext;

        public Node(T head) {
            this.head = head;
        }
    }
}
