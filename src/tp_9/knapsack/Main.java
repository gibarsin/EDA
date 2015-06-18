package tp_9.knapsack;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Gonzalo on 17/06/2015.
 */
public class Main {
    public static void main(String[] args) {
        List<Item> items = new LinkedList<Item>();

        items.add(new Item(60, 1));
        items.add(new Item(100, 2));
        items.add(new Item(120, 3));

        Knapsack knapsack = new Knapsack(items, 5);

        System.out.println(knapsack.solveNaive());
        System.out.println(knapsack.solveDP());
    }
}
