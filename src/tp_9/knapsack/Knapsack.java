package tp_9.knapsack;

import java.util.List;

/**
 * Created by Gonzalo on 17/06/2015.
 */
public class Knapsack {
    private final int knapsackSize;
    private final List<Item> items;

    public Knapsack(List<Item> items, int knapsackSize) {
        this.items = items;
        this.knapsackSize = knapsackSize;
    }

    public int solveNaive() {
        return solveNaiveRec(0, knapsackSize);
    }

    private int solveNaiveRec(int itemIndex, int remainingKnapsackSize) {
        if (itemIndex == items.size())
            return 0;
        Item item = items.get(itemIndex);

        if (item.getWeight() > remainingKnapsackSize) {
            return solveNaiveRec(itemIndex + 1, remainingKnapsackSize);
        } else {
            return Math.max(item.getValue() + solveNaiveRec(itemIndex + 1, remainingKnapsackSize - item.getWeight()),
                    solveNaiveRec(itemIndex + 1, remainingKnapsackSize));
        }
    }

    public int solveDP() {
        int[][] table = new int[items.size() + 1][knapsackSize + 1];
        Item item;

        for(int i = 0; i <= items.size(); i++) {
            for(int w = 0; w <= knapsackSize; w++) {

                if(i == 0 || w == 0) {
                    table[i][w] = 0;
                } else {
                    item = items.get(i-1);
                    if(item.getWeight() <= w) { //IF IT FITS
                        table[i][w] = Math.max(item.getValue() + table[i-1][w - item.getWeight()], table[i-1][w]); //GET THE MAX VALUE OF ADDING THIS VALUE TO THE VALUE OF
                    } else { //ELEMENT DOESN'T FIT IN KNAPSACK
                        table[i][w] = table[i-1][w];
                    }
                }
            }
        }
        return table[items.size()][knapsackSize];
    }
}
