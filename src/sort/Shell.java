package sort;

public class Shell {
    public static <T extends Comparable<T>> void sort(T[] vec) {
        int i, j;
        int dist = vec.length / 2; //Distancia
        T aux;

        while(dist > 0) {
            for(i = dist; i < vec.length; i++) {
                aux = vec[i];
                j = i;

                while((j >= dist) && (aux.compareTo(vec[j-dist]) < 0)) {
                    vec[j] = vec[j-dist];
                    j = j - dist;
                }
                vec[j] = aux;
            }
            dist = dist/2;
        }
    }
}
