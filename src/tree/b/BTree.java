package tree.b;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Gonzalo on 26/04/2015.
 */
public class BTree<T extends Comparable<? super T>> {
    private Node root;
    private int order;

    public BTree(int order) {
        this.order = order;
    }

    //No es estática pues sino no podría ver el orden(cantidad total de valores que se pueden insertar)
    private class Node {
        List<T> values;
        List<Node> pointers;

        public Node() {
            values = new LinkedList<T>();
            pointers = new LinkedList<Node>();
        }

        //Devuelve tamaño actual
        public int size() {
            return values.size();
        }

        public void range(T low, T upp, List<T> list) {
            int res1;
            int res2;
            //Se puede optimizar pasando el valor máximo que puede tener dado el nodo desde el que vino
            for(int i=0; i < values.size(); i++) {
                pointers.get(i).range(low, upp, list);
                res1 = values.get(i).compareTo(low);
                res2 = values.get(i).compareTo(upp);
                if(res1 >= 0 && res2 <= 0) {
                    list.add(values.get(i));
                } else if(res2 > 0){
                    return;
                }
            }
            pointers.get(values.size()+1).range(low,upp, list);
        }
    }

    public List<T> range(T low, T upp) {
        if(low == null || upp == null || low.compareTo(upp) > 0)
            throw new IllegalArgumentException();
        List<T> list = new LinkedList<T>();
        if(root != null)
            root.range(low, upp, list);
        return list;
    }
}
