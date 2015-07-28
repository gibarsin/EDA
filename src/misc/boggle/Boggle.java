package misc.boggle;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gonzalo on 22/06/2015.
 */
public class Boggle {
    private static final int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static Set<String> solve(char[][] board, Trie dictionary) {
        Set<String> found = new HashSet<String>();

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                solveRec(board, dictionary, i, j, found, new StringBuffer());
            }
        }

        return found;
    }

    private static void solveRec(char[][] board, Trie dictionary, int i, int j, Set<String> found, StringBuffer word) {
        if(dictionary.isEnd()) {
            found.add(String.valueOf(word));
        }
        Trie subWord = dictionary.getChild(board[i][j]);

        if(subWord == null) {
            return;
        }
        word.append(board[i][j]);
        for (int[] move : moves) {
            int nextI = i + move[0];
            int nextJ = j + move[1];
            if(withinLimits(board, nextI, nextJ)) {
                solveRec(board, subWord, nextI, nextJ, found, word);
            }
        }
        word.deleteCharAt(word.length()-1);
    }

    private static boolean withinLimits(char[][] board, int nextI, int nextJ) {
        return nextI >= 0 && nextJ >= 0 && nextI < board.length && nextJ < board[0].length;
    }
}