package tp_9.huffman;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gonzalo on 17/06/2015.
 */
public class Main {
    public static void main(String[] args) {
        Map<String, Integer> freq = new HashMap<String, Integer>();

        freq.put("A", 15);
        freq.put("B", 7);
        freq.put("C", 6);
        freq.put("D", 6);
        freq.put("E", 5);

        HuffmanTree.createTree(freq).print();
    }
}
