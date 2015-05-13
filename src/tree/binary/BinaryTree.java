package tree.binary;

import java.util.*;

/**
 * Created by Gonzalo on 24/04/2015.
 */
public class BinaryTree<T> {
    private T value;
    private BinaryTree<T> left, right;

    public BinaryTree(T value, BinaryTree<T> left, BinaryTree<T> right) {
        if(value == null)
            throw new IllegalArgumentException();
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public static <T> BinaryTree<T> buildFromList(List<T> values) {
        if(values == null)
            return null;
        Iterator<T> it = values.iterator();
        Queue<BinaryTree<T>> queue = new LinkedList<BinaryTree<T>>();
        int currLevelSize = 2;

        BinaryTree<T> root = new BinaryTree<T>(it.next(), null, null);
        queue.add(root);

        while(it.hasNext()) {
            for(int j=0; it.hasNext() && j < currLevelSize; j+=2) {
                BinaryTree<T> currTree = queue.remove();

                currTree.left = new BinaryTree<T>(it.next(), null, null);
                queue.add(currTree.left);
                if (it.hasNext()) {
                    currTree.right = new BinaryTree<T>(it.next(), null, null);
                    queue.add(currTree.right);
                }
            }
            currLevelSize *= 2;
        }
        return root;
    }

    public static <T> T commonAncestor(BinaryTree<T> tree, T value1, T value2) {
        if (tree == null)
            return null;
        if(tree.value.equals(value1) || tree.value.equals(value2)) //Encontre un valor, con lo cual el otro puede ser un hijo de este o puede estar en otra rama, con lo cual entraria en el 3er. if de la funcion
            return tree.value;
        T left = commonAncestor(tree.left, value1, value2);
        T right = commonAncestor(tree.right, value1, value2);

        if(left != null && right != null)   //Cada valor en una rama diferente
            return tree.value;
        return left != null ? left: right;  //Solo uno de los valores en alguna de las ramas

    }


    public static BinaryTree<Integer> buildFromSortedList(List<Integer> values) {
        if(values == null)
            throw new IllegalArgumentException();
        return buildFromSortedListRec(values, 0, values.size()-1);
    }

    private static BinaryTree<Integer> buildFromSortedListRec(List<Integer> values, int min, int max) {
        if(max < min)
            return null;
        else if(min == max)
            return new BinaryTree<Integer>(values.get(min), null, null);
        int index = (min+max)/2;
        return new BinaryTree<Integer>(values.get(index), buildFromSortedListRec(values, min, index - 1), buildFromSortedListRec(values, index + 1, max));
    }

    public void print() {
        System.out.println( "VALUE: " + this.value + "\tPOSITION: " + this);
        if(left != null)
            left.print();
        if(right != null)
            right.print();
    }

    public T getValue() {
        return value;
    }

    public BinaryTree<T> getLeft() {
        return left;
    }

    public BinaryTree<T> getRight() {
        return right;
    }
}
