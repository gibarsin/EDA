package list.sortedlist;

import java.util.Comparator;

/**
 * Created by Gonzalo on 24/04/2015.
 */
public class Main {
    public static void main(String[] args) {
        SortedList<String> list = new SortedListImpl<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        list.add("C");
        list.print();
        list.add("A");
        list.print();
        list.add("D");
        list.print();
        list.add("B");
        list.print();
        list.undo();
        list.print();
        list.undo();
        list.undo();
        list.print();

    }
}
