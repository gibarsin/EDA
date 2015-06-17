package tp_9.horse;

/**
 * Created by Gonzalo on 17/06/2015.
 */
public class Board {
    private int[][] board;
    private int rows, cols, size;

    public Board(int rows, int cols) {
        board = new int[rows][cols];
        this.rows = rows;
        this.cols = cols;
        size = rows * cols;
    }

    public int getRowsSize() {
        return rows;
    }

    public int getColsSize() {
        return cols;
    }

    public int getSize() {
        return size;
    }

    public void set(int i, int j, int value) {
        board[i][j] = value;
    }

    public int get(int i, int j) {
        return board[i][j];
    }

    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < rows && y >=0 && y < cols;
    }

    public Board clone() {
        Board copy = new Board(rows, cols);
        int value;

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                value = board[i][j];
                copy.set(i, j, value);
            }
        }

        return copy;
    }

    public void print() {
        System.out.println("------ PRINTING BOARD ------");
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("----------------------------");
    }
}
