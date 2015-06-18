package tp_9.rhombus;

/**
 * Created by Gonzalo on 18/06/2015.
 */
public class RhombusMatrix {
    public static int[][] m = {
            {0, 1, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0},
            {1, 0, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1},
            {0, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1}
    };

    public static void findLargestRhombus() {

    }

    public static void print() {
        for(int i = 0; i < m.length; i++) {
            for(int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
