package sort;

import java.util.Comparator;


public class Bubble {
    public static <T> void sort(T[] arr, Comparator<? super T> cmp) {
        if (cmp == null)
            throw new IllegalArgumentException();
        if (arr == null)
            return;

        boolean sorted = false;

        do {
            for (int i = 0; i < arr.length && !sorted; i++) {
                sorted = true;
                for (int j = 0; j < arr.length - 1; j++)
                    if (cmp.compare(arr[j], arr[j + 1]) > 0) {
                        swap(arr, j, j + 1);
                        sorted = false;
                    }
            }
        } while(!sorted);
    }

    private static <T> void swap(T[] arr, int j, int i) {
        T aux = arr[j];
        arr[j] = arr[i];
        arr[i] = aux;
    }
}
