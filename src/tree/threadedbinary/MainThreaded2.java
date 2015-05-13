package tree.threadedbinary;

import java.util.Comparator;

/**
 * Created by Gonzalo on 14/04/2015.
 */
public class MainThreaded2 {
    public static void main(String[] args) {
        ThreadedBinaryTree2<Character> tree = new ThreadedBinaryTree2<Character>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return o1.compareTo(o2);
            }
        });

        tree.add('F');
        System.out.println("hola");
        tree.add('A');
        System.out.println("hola2");
        tree.add('D');
        tree.add('C');

        tree.inOrder();
        tree.print();
    }
}
