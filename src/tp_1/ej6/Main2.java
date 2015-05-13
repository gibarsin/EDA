package tp_1.ej6;

/**
 * Created by Gonzalo on 04/03/2015.
 */
public class Main2 {
    public static void main(String[] args) {
        char[] arr1 = {'a', 'b', 'c', 'd'};
        char[] arr2 = {'e'};
        char[] arr3;

        arr3 = intersectionWrapper(arr1, arr2);
        System.out.println(arr3);
    }

    public static char[] intersectionWrapper(char[] arr1, char[] arr2) {
        if(arr1.length < arr2.length) {
            return intersection(arr1, arr2);
        } else {
            return intersection(arr2, arr1);
        }
    }

    private static char[] intersection(char[] arr2, char[] arr1) {
        char[] arr3 = new char[arr1.length];
        int j=0, k = 0;
        double start = System.currentTimeMillis(), end;

        for (int i = 0; i < arr1.length;) {
            if(arr2[j] < arr1[i]) {
                j++;
            } else if(arr1[i] == arr2[j]) {
                arr3[k++] = arr1[i];
                i++;
                j++;
            } else {
                i++;
            }
        }

        end = System.currentTimeMillis();
        System.out.println(end - start + " miliseconds");

        return arr3;
    }
}
