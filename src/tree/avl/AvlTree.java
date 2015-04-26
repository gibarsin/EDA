package tree.avl;

public class AvlTree<T extends Comparable<T>> {
    private static final int HEIGHT_DIFFERENCE = 1;

    private Node<T> root;

    private static class Node<T> {
        private T elem;
        private Node<T> left;
        private Node<T> right;
        private int height;

        public Node(T elem) {
            this.elem = elem;
        }

        public Node(T elem, Node<T> left, Node<T> right) {
            this.elem = elem;
            this.left = left;
            this.right = right;
            this.height = 0;
        }
    }

    public void insert(T elem) {
        root = insertRec(elem, root);
    }

    private Node<T> insertRec(T elem, Node<T> node) {
        if (node == null) {
            return new Node<T>(elem);
        }
        int comp = elem.compareTo(node.elem);

        if (comp < 0) {
            node.left = insertRec(elem, node.left);
        } else if (comp > 0) {
            node.right = insertRec(elem, node.right);
        } //Supongo que no existen elementos duplicados
        return balance(node);
    }

    public void remove(T elem) {
        root = removeRec(elem, root);
    }

    private Node<T> removeRec(T elem, Node<T> node) {
        if(node == null) {
            return null;
        }
        int comp = elem.compareTo(node.elem);

        if(comp < 0) {
            node.left = removeRec(elem, node.left);
        } else if(comp > 0) {
            node.right = removeRec(elem, node.right);
        } else if(node.left != null && node.right != null) {
            node.elem = min(node.right);
            node.right = removeRec(node.elem, node.right);
        } else {
            if(node.left != null) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return balance(node);
    }

    private Node<T> balance(Node<T> node) {
        if(node == null) { //Si remuevo una hoja queda null
            return null;
        }

        if (height(node.left) - height(node.right) > HEIGHT_DIFFERENCE) {
            if (height(node.left.left) >= height(node.left.right)) {
                node = rotateClockWise(node);
            } else {
                node = doubleRotateCounterAndClock(node);
            }
        } else if (height(node.right) - height(node.left) > HEIGHT_DIFFERENCE) {
            if (height(node.right.right) >= height(node.right.left)) {
                node = rotateCounterClockWise(node);
            } else {
                node = rotateClockAndCounter(node);
            }
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    private Node<T> rotateClockWise(Node<T> node) {
        Node<T> aux = node.left;
        node.left = aux.right;
        aux.right = node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        aux.height = Math.max(height(aux.left), height(aux.right)) + 1;
        return aux;
    }

    private Node<T> rotateCounterClockWise(Node<T> node) {
        Node<T> aux = node.right;
        node.right = aux.left;
        aux.left = node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        aux.height = Math.max(height(aux.left), height(aux.right)) + 1;
        return aux;
    }

    private Node<T> doubleRotateCounterAndClock(Node<T> node) {
        node.left = rotateCounterClockWise(node.left); //Roto el nodo derecho del hijo de node
        return rotateClockWise(node); //Roto el nodo derecho con su hijo izquierdo
    }


    private Node<T> rotateClockAndCounter(Node<T> node) {
        node.right = rotateClockWise(node.right);
        return rotateCounterClockWise(node);
    }

    private T min(Node<T> node) {
        while(node != null)
            node = node.left;
        return node.elem;
    }

    private int height(Node<T> node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }

    public void print() {
        System.out.print("Arbol: ");
        printNode(root);
    }

    private void printNode(Node<T> node) {
        if (node != null) {
            printNode(node.left);
            System.out.print(" " + node.elem + " ");
            printNode(node.right);
        }
    }
}
