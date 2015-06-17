package tp_9.table;

/**
 * Created by Gonzalo on 17/06/2015.
 */
public class Main {
    public static void main(String[] args) {
        int people = 4;
//        TableCompatibility tables = new TableCompatibility(people);
//
//        tables.printCompatibilty();
//        tables.solve();
//        tables.printSolution();
        StrictTable tables;

        do {
            tables = new StrictTable(people);
        } while(!tables.solve());
    }
}
