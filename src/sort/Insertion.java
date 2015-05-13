package sort;

import java.util.Comparator;

public class Insertion {
    public static <T> void sort(T[] arr, Comparator<? super T> cmp) {
        if(cmp == null)
            throw new IllegalArgumentException();
        if(arr == null)
            return;
        for(int sortedTop = 1; sortedTop < arr.length; sortedTop++) {
            T elem = arr[sortedTop];
            int i = sortedTop;

            while(i > 0 && cmp.compare(arr[i-1], elem) > 0) {
                arr[i] = arr[i-1];
                i--;
            }
            arr[i] = elem;
        }
    }
}
