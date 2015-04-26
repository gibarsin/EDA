package tree.binarysearch;

import tree.binary.BinaryTree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


public class BinarySearchTree<T> {
    private Node<T> root;
    private Comparator<? super T> cmp;

    public BinarySearchTree(Comparator<? super T> cmp) {
        this.cmp = cmp;
    }

    public void add(T key) {
        if(key == null)
            throw new IllegalArgumentException();
        root = addRec(root, key);
    }

    private Node<T> addRec(Node<T> node, T key) {
        if(node == null) {
            return new Node<T>(key);
        }
        int res = cmp.compare(node.key, key);

        if(res > 0)
            node.left = addRec(node.left, key);
        else if(res < 0)
            node.right = addRec(node.right, key);
        return node;
    }

    public void remove(T key) {
        if(key == null)
            throw new IllegalArgumentException();
        root = removeRec(root, key);
    }

    private Node<T> removeRec(Node<T> node, T key) {
        if (node == null)
            return null;
        int res = cmp.compare(node.key, key);

        if (res > 0) {
            node.left = removeRec(node.left, key);
        } else if (res < 0) {
            node.right = removeRec(node.right, key);
        } else {
            if(node.left == null) {
                node = node.right;
            } else if(node.right == null) {
                node = node.left;
            } else {
                node.right = replaceForSuccessorInOrder(node, node.right);
            }
        }
        return node;
    }

    private Node<T> replaceForSuccessorInOrder(Node<T> nodeToRemove, Node<T> node) {
        if(node.left == null) {
            nodeToRemove.key = node.key;
            return node.right;
        }
        node.left = replaceForSuccessorInOrder(nodeToRemove, node.left);
        return node;
    }

    public boolean contains(T key) {
        if(key == null)
            throw new IllegalArgumentException();
        return containsRec(root, key);
    }

    private boolean containsRec(Node<T> node, T key) {
        if(node == null)
            return false;
        int res = cmp.compare(node.key, key);
        if(res > 0)
            return containsRec(node.left, key);
        else if(res < 0)
            return containsRec(node.right, key);
        return true;
    }

    public List<T> getRange(T inf, T sup) {
        if(inf == null || sup == null || cmp.compare(inf, sup) > 0)
            throw new IllegalArgumentException();
        List<T> list = new LinkedList<T>();

        getRangeRec(root, inf, sup, list);
        return list;
    }

    private void getRangeRec(Node<T> node, T inf, T sup, List<T> list) {
        if(node == null)
            return;
        int res1 = cmp.compare(node.key, inf);
        int res2 = cmp.compare(node.key, sup);

        if(res1 >=0) {
            getRangeRec(node.left, inf, sup, list);
        }
        if(res1 >= 0 && res2 <= 0) {
            list.add(node.key);
        }
        if(res2 <= 0) {
            getRangeRec(node.right, inf, sup, list);
        }
    }

    public int getLevel(T key) {
        if(key == null)
            throw new IllegalArgumentException();
        return getLevelRec(root, key);
    }

    private int getLevelRec(Node<T> node, T key) {
        if(node == null)
            return -1;
        int res = cmp.compare(node.key, key);
        int height;
        if(res > 0) {
            height = getLevelRec(node.left, key);
        } else if(res < 0) {
            height = getLevelRec(node.right, key);
        } else {
            return 0;
        }
        return height == -1 ? -1 : height + 1;
    }

    public int leaves() {
        return leavesRec(root);
    }

    private int leavesRec(Node<T> node) {
        if(node == null)
            return 0;
        if(node.left == null && node.right == null)
            return 1;
        else
            return leavesRec(node.left) + leavesRec(node.right);
    }

    public T maxNormalOrder() {
        if(root == null)
            return null;
        Node<T> curr = root;

        while(curr.right != null)
            curr = curr.right;
        return curr.key;
    }

    public void printAncestors(T key) {
        if(key == null)
            throw new IllegalArgumentException();
        printAncestorsRec(root, key);
    }

    private boolean printAncestorsRec(Node<T> node, T key) {
        if (node == null)
            return false;
        int res = cmp.compare(node.key, key);
        boolean found;

        if (res == 0) {
            return true;
        } else if(res > 0) {
            found = printAncestorsRec(node.left, key);
        } else {
            found = printAncestorsRec(node.right, key);
        }
        if(found)
            System.out.print(node.key + " ");
        return found;
    }

    public void printDescendants(T key) {
        if(key == null)
            throw new IllegalArgumentException();
        printDescendantsRec(root, key);
    }

    private void printDescendantsRec(Node<T> node, T key) {
        if(node == null)
            return;
        int res = cmp.compare(node.key, key);

        if(res == 0) {
            printRec(node.left);
            printRec(node.right);
        } else if(res > 0) {
            printDescendantsRec(node.left, key);
        } else {
            printDescendantsRec(node.right, key);
        }
    }

    public void invertList() {
        cmp = cmp.reversed();

        invertListRec(root);
    }

    private void invertListRec(Node<T> node) {
        if(node != null) {
            Node<T> aux = node.left;
            node.left = node.right;
            node.right = aux;

            invertListRec(node.left);
            invertListRec(node.right);
        }
    }

    public void print() {
        printRec(root);
        System.out.println("------------------------------------------");
    }

    private void printRec(Node<T> node) {
        if(node != null) {
            System.out.println("Key: " + node.key + "\t Current: " + node + "\t Hijo izquierdo: " + node.left + "\t Hijo derecho: " + node.right);
            printRec(node.left);
            printRec(node.right);
        }
    }

    public static <T> boolean isBST(BinaryTree<T> tree, Comparator<? super T> cmp) {
        return isBSTRec(tree,cmp, null, null);
    }

    private static <T> boolean isBSTRec(BinaryTree<T> tree, Comparator<? super T> cmp, T min, T max) {
        if(tree == null)
            return true;
        if(min != null && cmp.compare(tree.getValue(), min) < 0)
            return false;
        if(max != null && cmp.compare(tree.getValue(), max) > 0)
            return false;
        return isBSTRec(tree.getLeft(), cmp, min, tree.getValue()) && isBSTRec(tree.getRight(), cmp, tree.getValue(), max);
    }

    public static <T> boolean isAVL(BinarySearchTree<T> tree) {
        if(tree == null)
            throw new IllegalArgumentException();
        return isAVLRec(tree.root, tree.cmp, null, null) != -1;
    }

    private static <T> int isAVLRec(Node<T> node, Comparator<? super T> cmp, T min, T max) {
        if(node == null)
            return 0;
        System.out.println("estoy en : " + node.key);
        if(min != null && cmp.compare(node.key, min) < 0)
            return -1;
        if(max != null && cmp.compare(node.key, max) > 0)
            return -1;
        int hLeft = isAVLRec(node.left, cmp, min, node.key);
        if(hLeft == -1)
            return -1;
        int hRight = isAVLRec(node.right, cmp, node.key, max);
        if(hRight == -1)
            return -1;
        return Math.abs(hRight-hLeft) <= 1 ? Math.max(hRight, hLeft) + 1 : -1;
    }

    private static class Node<T> {
        T key;
        Node<T> left, right;

        public Node(T key) {
            this.key = key;
        }
    }
}