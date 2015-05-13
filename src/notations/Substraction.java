package notations;

public class Substraction implements Operator {
    private static final Substraction instance = new Substraction();

    public static Substraction getInstance() {
        return instance;
    }

    public double execute(double op1, double op2) {
        return op1 - op2;
    }

    public String toString() {
        return "-";
    }
}
