package tp_9.knapsack;

/**
 * Created by Gonzalo on 17/06/2015.
 */
public class Item {
    private int value;
    private int weight;

    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }
    public int getWeight() {
        return weight;
    }
}
