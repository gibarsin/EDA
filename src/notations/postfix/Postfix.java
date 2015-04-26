package notations.postfix;

import notations.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Postfix {
    public static double evaluate(List<String> tokens) {
        Deque<Double> stack = new LinkedList<Double>();

        for(String token: tokens) {
            Operator op = getOperator(token);

            if(op == null)
                stack.push(Double.valueOf(token));
            else {
                double op1 = stack.pop();
                double op2 = stack.pop();
                stack.push(op.execute(op1, op2)); //Podrian restarse al reves
            }
        }
        return stack.pop();
    }

    public static Operator getOperator(String token) {
        if(token.length() != 1)
            return null;
        switch(token.charAt(0)) {
            case '+':   return Sum.getInstance();
            case '-':   return Substraction.getInstance();
            case '*':   return Product.getInstance();
            case '/':   return Division.getInstance();
            default:    return null;
        }
    }
}
