package tree.threadedbinary;

import java.util.Comparator;

public class ThreadedBinaryTree<T> {
    private Node<T> root;
    private Comparator<T> comp;

    public ThreadedBinaryTree(Comparator<T> comp) {
        this.comp = comp;
    }

    public void add(T elem) {
        root = addRec(elem, root, null, null);
    }

    private Node<T> addRec(T elem, Node<T> node, Node<T> leftThreaded, Node<T> rightThreaded) {
        if(node == null) {
            return new Node(elem, leftThreaded, rightThreaded);
        }
        int res = comp.compare(node.head, elem);
        if(res != 0) {
            if (res > 0) {
                node.left = addRec(elem, node.left, leftThreaded, node);
                node.leftThreaded = null;
            } else {
                node.right = addRec(elem, node.right, node, rightThreaded);
                node.rightThreaded = null;
            }
        }
        return node;
    }

    public void inOrder() {
        Node<T> curr = root;
        boolean visited = false;

        while (curr != null) {
            if (!visited && curr.left != null) {
                curr = curr.left;
            } else {
                System.out.println(curr.head + " ");
                if (curr.right != null) {
                    curr = curr.right;
                    visited = false;
                } else {
                    curr = curr.rightThreaded;
                    visited = true;
                }
            }
        }
    }

    public void print() {
        printRec(root);
    }

    private void printRec(Node<T> node) {
        if(node == null) {
            return;
        }
        System.out.println("Nodo actual: " + node.head + "  MemPos: " + node + "  Hijo izq.: " + node.left + "  Hijo der.: " + node.right + "  Left Thread: " + node.leftThreaded + "  Right Thread: " + node.rightThreaded);
        printRec(node.left);
        printRec(node.right);
    }

    private static class Node<T> {
        private T head;
        private Node<T> left;
        private Node<T> right;
        private Node<T> leftThreaded;
        private Node<T> rightThreaded;

        public Node(T head, Node<T> leftThreaded, Node<T> rightThreaded) {
            this.head = head;
            this.leftThreaded = leftThreaded;
            this.rightThreaded = rightThreaded;
        }
    }
}
