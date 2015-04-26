package tree.binarysearch;

import java.util.Comparator;

/**
 * Created by Gonzalo on 25/04/2015.
 */
public class Main {
    public static void main(String[] args) {
        BinarySearchTree<String> tree = new BinarySearchTree<String>(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        BinarySearchTree<Integer> tree2 = new BinarySearchTree<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        tree.add("M");
        tree.add("J");
        tree.add("O");
        tree.add("B");
        tree.add("A");

        System.out.println(BinarySearchTree.isAVL(tree));

        tree2.add(5);
        tree2.add(2);
        tree2.add(10);

        System.out.println(BinarySearchTree.hasBranchWithSum(tree2, 12));
    }
}
