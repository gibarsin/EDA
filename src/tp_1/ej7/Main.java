package tp_1.ej7;

/**
 * Created by Gonzalo on 04/03/2015.
 */
public class Main {
    public static void main(String[] args) {
        char[] arr = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        double end, start = System.currentTimeMillis();
        permutations(arr, 0);
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void permutations(char[] arr, int index) {
        if (arr.length == index) {
            System.out.println(arr);
        } else {
            for (int i = index; i < arr.length; i++) {
                swap(arr, i, index);
                permutations(arr, index+1);
                swap(arr, i, index);
            }
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char aux = arr[i];
        arr[i] = arr[j];
        arr[j] = aux;
    }
}
