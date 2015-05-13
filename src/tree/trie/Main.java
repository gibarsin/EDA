package tree.trie;

/**
 * Created by Gonzalo on 26/04/2015.
 */
public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insertString("Hola");
        trie.insertString("Hoe");
        trie.insertString("Pedido");

        trie.print();
        System.out.println(trie.contains("Hola"));
        System.out.println(trie.contains("Hoe"));
        System.out.println(trie.contains("Ped"));
    }
}
