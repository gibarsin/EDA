package misc.boggle;

/**
 * Created by Gonzalo on 22/06/2015.
 */
public class Trie {
    private Trie[] children = new Trie['Z' - 'A' + 1];
    private boolean end;

    public void add(String word) {
        word = word.toUpperCase();
        add(word.toCharArray(), 0);
    }

    private void add(char[] word, int from) {
        if (from == word.length) {
            end = true;
        } else {
            int index = word[from] - 'A';
            if (children[index] == null) {
                children[index] = new Trie();
            }
            children[index].add(word, from + 1);
        }
    }

    public Trie getChild(char c) {
        return children[c - 'A'];
    }

    public boolean isEnd() {
        return end;
    }
}
