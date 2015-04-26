package tp_1.ej7;

/**
 * Created by Gonzalo on 04/03/2015.
 */
public class Main2 {
    public static void main(String[] args) {
        char[] arr = {'A', 'A', 'A', 'C'};
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
                if(!isRepeated(arr, arr[i], index, i)) { //Reviso si existe algún valor repetido a la izquierda de mi pos. "i" actual para no hacer un swap innecesario
                    swap(arr, i, index);
                    permutations(arr, index+1);
                    swap(arr, i, index);
                }
            }
        }
    }

    private static boolean isRepeated(char[] arr, char c, int start, int end) {
        for(int i=start; i<end; i++) {
            if(arr[i] == c) {
                return true;
            }
        }
        return false;
    }

    private static void swap(char[] arr, int i, int j) {
        char aux = arr[i];
        arr[i] = arr[j];
        arr[j] = aux;
    }


}
