package tp_1.ej6;

/**
 * Created by Gonzalo on 04/03/2015.
 */
public class Main {
    public static void main(String[] args) {
        char[] arr1 = {'a', 'b', 'c', 'f'};
        char[] arr2 = {'f', 'a'};
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

    private static char[] intersection(char[] arr1, char[] arr2) {
        char[] arr3 = new char[arr1.length];
        int k = 0;
        boolean found;

        for (int i = 0; i < arr1.length; i++) {
            found = false;
            for (int j = 0; j < arr2.length && !found; j++) {
                if (arr1[i] == arr2[j]) {
                    arr3[k++] = arr1[i];
                    found = true;
                }
            }
        }
        return arr3;
    }
}
