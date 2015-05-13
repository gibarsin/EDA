package notations;

public class Sum implements Operator {

    private static final Sum instance = new Sum();

    public static Sum getInstance() {
        return instance;
    }

    public double execute(double op1, double op2) {
        return op1 + op2;
    }

    public String toString() {
        return "+";
    }
}
