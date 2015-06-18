package tp_9.huffman;

import tree.binary.BinaryTree;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Gonzalo on 17/06/2015.
 */
public class HuffmanTree {
    public static BinaryTree<Integer> createTree(Map<String, Integer> frequencies) {
        BinaryTree<Integer> t;
        Queue<BinaryTree<Integer>> q = new PriorityQueue<BinaryTree<Integer>>(new Comparator<BinaryTree<Integer>>() {
            @Override
            public int compare(BinaryTree<Integer> o1, BinaryTree<Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        for(Map.Entry<String, Integer> entry : frequencies.entrySet()) {
            q.offer(new BinaryTree<Integer>(entry.getValue(), null, null));
        }

        while(!q.isEmpty()) {
            BinaryTree<Integer> first = q.poll();
            BinaryTree<Integer> second = q.poll();

            if(second == null) {
                return first;
            }

            BinaryTree<Integer> newTree = new BinaryTree<Integer>(first.getValue() + second.getValue(), first, second);
            q.offer(newTree);
        }

        return null;
    }


}
