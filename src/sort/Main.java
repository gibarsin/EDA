package sort;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {5,8,10,90,1,4};

        //SORT
        Insertion.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        for(Integer elem : arr) {
            System.out.println(elem);
        }
    }
}
