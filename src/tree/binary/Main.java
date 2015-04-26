package tree.binary;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Gonzalo on 24/04/2015.
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<Integer>();
        BinaryTree<Integer> tree, bst;

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

//        tree = BinaryTree.buildFromList(list);
//        tree.print();
        bst = BinaryTree.buildFromSortedList(list);
        bst.print();
    }
}
