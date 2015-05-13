package tree.trie;

import java.util.*;

public class Trie {
    private Node root;

    private static class Node {
        Map<Character, Node> tails;

        public Node() {
            tails = new HashMap<Character, Node>();
        }
    }

    public Trie() {
        root = new Node();
    }

    public void insertString(String str) {
        if(str == null)
            return;
        insertStringRec(root, str, 0);
    }

    private void insertStringRec(Node node, String str, int index) {
        if(index == str.length())
            return;
        Node searchedNode = node.tails.get(str.charAt(index));

        if(searchedNode == null) {
            Node newNode = new Node();
            node.tails.put(str.charAt(index), newNode);
            insertStringRec(newNode, str, index+1);
        } else {
            insertStringRec(searchedNode, str, index+1);
        }
    }

    public boolean contains(String str) {
        if(str == null)
            return true;
        return containsRec(root, str, 0);
    }

    private boolean containsRec(Node node, String str, int index) {
        if(index == str.length()) {
            if (node.tails.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } else {
            Node searchedNode = node.tails.get(str.charAt(index));

            if (searchedNode == null)
                return false;
            else
                return containsRec(searchedNode, str, index + 1);
        }
    }

    public void print() {
        Deque<Character> list  = new LinkedList<Character>();
        printRec(root, list);
    }

    private void printRec(Node node,  Deque<Character> list) {
        if(node.tails.isEmpty())
            System.out.println(list);
        else {
            for (Map.Entry<Character, Node> each : node.tails.entrySet()) {
                list.addLast(each.getKey());
                printRec(each.getValue(), list);
                list.removeLast();
            }
        }
    }
}
