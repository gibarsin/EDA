package tp_9.horse;

import tp_9.Board;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Gonzalo on 17/06/2015.
 */
public class HorseWalk {
    private static final int[][] moves = {{-1, -2}, {1, -2}, {-1, 2}, {1, 2}, {-2, -1}, {-2, 1}, {2, -1}, {2, 1}};
    private Board board;
    private List<Board> solutions;

    public HorseWalk(int rows, int cols) {
        board = new Board(rows, cols);
        solutions = new LinkedList<Board>();
    }

    public void solve() {
        int rows = board.getRowsSize();
        int cols = board.getColsSize();

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                solveRec(1, i, j);
            }
        }
    }

    private void solveRec(int step, int currX, int currY) {
        board.set(currX,currY, step);

        if(step == board.getSize()) {
            solutions.add(board.clone());
            System.out.println("--- SOLUTION FOUND ---");
        } else {
            for(int[] move : moves) {
                int nextX = currX + move[0];
                int nextY = currY + move[1];

                if(board.isWithinBounds(nextX, nextY) && board.get(nextX, nextY) == 0) {
                    solveRec(step + 1, nextX, nextY);
                }
            }
        }
        board.set(currX, currY, 0);
    }

    public Collection<Board> getSolutions() {
        return solutions;
    }
}
