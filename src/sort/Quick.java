package sort;

import java.util.Collections;
import java.util.List;

public class Quick {
    public static <T extends Comparable<T>> void sort(List<T> list) {
        if (list != null)
            sort(list, 0, list.size() - 1);
        else
            throw new IllegalArgumentException();
    }


    private static <T extends Comparable<T>> void sort(List<T> list, int p, int r) {
        if(p < r) {
            T pivot = list.get(r); //Ultimo elemento de la sublista
            int i = p;

            for (int j = p; j < r; j++) {
                if (list.get(j).compareTo(pivot) < 0) {
                    Collections.swap(list, i, j);
                    i++; //Aumento la posicion de la pared
                }
            }
            Collections.swap(list, i, r); //Coloca el pivot en la pared que separa los menores del pivot con los mayores del pivot

            sort(list, p, i - 1);   //Tomo la sublista menor
            sort(list, i + 1, r);    //Tomo la sublista mayor
        }
    }
}
