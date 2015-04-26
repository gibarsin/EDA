package notations;

/**
 * Created by Gonzalo on 25/04/2015.
 */
public class Product implements Operator {
    private static final Product instance = new Product();

    public static Product getInstance() {
        return instance;
    }

    public double execute(double op1, double op2) {
        return op1 * op2;
    }

    public String toString() {
        return "*";
    }
}
