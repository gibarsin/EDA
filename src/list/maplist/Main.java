package list.maplist;

/**
 * Created by Gonzalo on 25/04/2015.
 */
public class Main {
    public static void main(String[] args) {
        MapList<Integer, String> list = new MapList<Integer, String>();

        list.add(1, "Hello");
        list.add(2, "World");
        list.add(2, "Darling");
        list.add(3, "Goodbye");

        list.print();

        System.out.println(list.get(3));

        list.print();
    }
}
