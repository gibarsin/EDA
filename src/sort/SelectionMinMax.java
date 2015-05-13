package sort;

public class SelectionMinMax {
    public static <T extends Comparable<T>> void sort(T[] v) {
        int min, max;

        for (int i = 0, k = v.length - 1; k > i; i++, k--) {
            min = i;
            max = k;

            for (int j = i + 1, m = k - 1; j < v.length && m >= 0; j++, m--) {
                if (v[j].compareTo(v[min]) < 0)
                    min = j;
                if (v[m].compareTo(v[max]) > 0)
                    max = m;
            }
            swap(v, i, min);
            if (max != i) {
                swap(v, k, max);
            }
        }
    }

    private static <T> void swap(T[] v, int i1, int i2) {
        T aux = v[i1];
        v[i1] = v[i2];
        v[i2] = aux;
    }

    public static void main(String[] args) {
        Integer[] v = {10,5,4,4,9,18,7,1};

        sort(v);

        for(Integer e: v)
            System.out.print(" " + e + " ");
    }
}
