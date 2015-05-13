package tp_1.ej8;

import java.util.Random;

/**
 * Created by Gonzalo on 04/03/2015.
 */
public class Main {
    public static int N = 10;

    public static void main(String[] args) {
        int[][] mat = new int[N][N];
        int x = 2;
        int y = 4;

        fill(mat);
        paint(mat, x, y, 1);
    }

    /*Llena de 1 y 0 la matriz*/
    private static void fill(int[][] mat) {
        Random r = new Random();

        for(int i=0; i < mat.length; i++) {
            for(int j=0; j < mat.length; j++) {
                mat[i][j] = r.nextInt(2);
            }
        }
    }

    private static void paint(int[][] mat, int x, int y, int color) {

    }
}
