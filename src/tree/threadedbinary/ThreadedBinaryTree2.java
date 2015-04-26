package tree.threadedbinary;

import java.util.Comparator;

public class ThreadedBinaryTree2<T> {
    private Node<T> root;
    private Comparator<T> comp;

    public ThreadedBinaryTree2(Comparator<T> comp) {
        this.comp = comp;
    }

    public void add(T elem) {
        root = addRec(elem, root, null, null);
    }

    private Node<T> addRec(T elem, Node<T> node, Node<T> leftThreaded, Node<T> rightThreaded) {
        if (node == null) { //Solo para la raiz?
            return new Node<T>(elem, leftThreaded, rightThreaded);
        }
        int res = comp.compare(node.head, elem);

        if (res != 0) {
            if (res > 0) {
                if (node.left != null) {
                    if (leftThreaded != null && node.left.head.equals(leftThreaded.head)) {
                        node.left = new Node<T>(elem, leftThreaded, node);
                    } else {
                        node.left = addRec(elem, node.left, leftThreaded, node);
                    }
                } else {
                    node.left = new Node<T>(elem, leftThreaded, node);
                }
            } else {
                if (node.right != null) {
                    if (rightThreaded != null && node.right.head.equals(rightThreaded.head)) {
                        node.right = new Node<T>(elem, node, rightThreaded);
                    } else {
                        node.right = addRec(elem, node.right, node, rightThreaded);
                    }
                } else {
                    node.right = new Node<T>(elem, node, rightThreaded);
                }
            }
        }
        return node;
    }

    public void inOrder() {
        Node<T> curr = root;


        }

    public void print() {
        printRec(root);
    }

    private void printRec(Node<T> node) {
        if(node == null) {
            return;
        }
        System.out.println("Nodo actual: " + node.head + "  MemPos: " + node + "  Hijo izq.: " + node.left + "  Hijo der.: " + node.right);
        printRec(node.left);
        printRec(node.right);
    }

    private static class Node<T> {
        private T head;
        private Node<T> left;
        private Node<T> right;

        public Node(T head, Node<T> leftThreaded, Node<T> rightThreaded) {
            this.head = head;
            this.left = leftThreaded;
            this.right = rightThreaded;
        }
    }
}
