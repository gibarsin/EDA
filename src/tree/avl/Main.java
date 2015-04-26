package tree.avl;

public class Main {
    public static void main(String[] args) {
        AvlTree<Integer> tree = new AvlTree<Integer>();

        tree.insert(10);
        tree.insert(15);
        tree.insert(8);
        tree.insert(9);
        tree.insert(7);
        tree.insert(6);

        tree.remove(9);

        tree.print();
    }
}
