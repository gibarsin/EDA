package tree.threadedbinary;

import java.util.Comparator;

public class MainThreaded {
    public static void main(String[] args) {
        ThreadedBinaryTree<Character> tree = new ThreadedBinaryTree<Character>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return o1.compareTo(o2);
            }
        });

        tree.add('F');
        tree.add('A');
        tree.add('D');
        tree.add('C');

        tree.print();
        tree.inOrder();
    }
}
