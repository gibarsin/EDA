package notations;

public class Division implements Operator {
    private static final Division instance = new Division();

    public static Division getInstance() {
        return instance;
    }

    public double execute(double op1, double op2) {
        return op1 / op2;
    }

    public String toString() {
        return "/";
    }
}
