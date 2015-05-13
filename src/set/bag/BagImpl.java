package set.bag;

public class BagImpl<T> implements Bag<T> {
    private Node<T> first;

    public void add(T elem) {

    }

    public void remove(T elem) {

    }

    public void print() {
        Node<T> current = first;

        while(current != null) {
            System.out.println(current.head + " (" + current.num + ") ");
            current = current.next;
        }
    }

    private static class Node<T> {
        private T head;
        private int num;
        private Node<T> next;

        public Node(T head) {
            this.head = head;
            this.num = 1;
        }
    }
}
