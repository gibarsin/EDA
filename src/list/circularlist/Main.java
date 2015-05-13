package list.circularlist;

/**
 * Created by Gonzalo on 05/04/2015.
 */
public class Main {
    public static void main(String[] args) {
        CircularList<String> l = new CircularListImpl<String>();

        l.addLast("A");
        l.addLast("B");
        l.addLast("C");

        l.print();

        l.addLast("D");
        l.addLast("E");
        l.addLast("F");

        l.print();

        l.reset();
        System.out.println(l.getNext());
        System.out.println(l.getNext());
        System.out.println(l.getNext());

        CircularList<String> l2 = l.split();

        l.print();
        l2.print();

        l2.reset();
        l2.getNext();
        l2.getNext();
        l2.getNext();

        CircularList<String> l3 = l2.split();

        l2.print();
        l3.print();
    }
}
