package tree.bspecial;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class BSpecialTree<T> {
    private Node<T> root;
    private int keysNumber;
    private Comparator<? super T> cmp;

    public BSpecialTree(Comparator<? super T> cmp, int keysNumber) {
        this.cmp = cmp;
        this.keysNumber = keysNumber;
    }

    public boolean belongs(T key) {
        if(key == null) {
            return root == null;
        }
        return root.belongs(key);
    }

    private interface Node<T> {
        boolean belongs(T key);
    }

    //No es estática porque sino no podría usar el comparador
    //No posee <T> porque estaría introduciendo su propio tipo T y no podría usar Comparator
    private class internalNode implements Node<T> {
        private T key;
        private Node<T> left, right;

        public internalNode(T key) {
            this.key = key;
        }

        public boolean belongs(T key) {
            if(cmp.compare(this.key, key) > 0)
                return left.belongs(key);
            return right.belongs(key);
        }
    }

    private class leafNode implements Node<T> {
        private List<T> keys;

        public leafNode() {
            keys = new ArrayList<T>(keysNumber);
        }

        public boolean belongs(T key) {
            return keys.indexOf(key) != -1;
        }
    }
}
