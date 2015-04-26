package list.sortedfastlist;

import java.util.Comparator;

/**
 * Created by Gonzalo on 24/04/2015.
 */
public class Main {
    public static void main(String[] args) {
        SortedFastList<String> list = new SortedFastListImpl<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        list.add("A");
        list.print();
        list.add("E");
        list.print();
        list.add("B");
        list.print();
        list.add("X");
        list.print();
        list.add("P");
        list.print();
        list.add("C");
        list.print();
    }
}
