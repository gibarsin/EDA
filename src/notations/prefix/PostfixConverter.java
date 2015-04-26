package notations.prefix;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PostfixConverter {
    public static char[] operations = {'+','*','(','-','/',')'};

    public static List<Character> convertFromInFix(List<Character> v) {
        List<Character> prefix = new LinkedList<Character>();
        Deque<Character> opStack = new LinkedList<Character>();

        for(Character c: v) {
            if(isNumber(c))
                prefix.add(c);
            else if(isOperation(c)) {
                if(opStack.isEmpty())
                    opStack.push(c);
                else if(getPriority(c) > getPriority(opStack.peek())) {
                    if(c != ')')
                        opStack.push(c);
                    else { //Si cierra parentesis
                        while(opStack.peek() != '(')
                            prefix.add(opStack.pop());
                        opStack.pop();
                    }
                } else if(getPriority(c) <= getPriority(opStack.peek())) {
                    if(opStack.peek() != '(') {
                        prefix.add(opStack.pop());
                        opStack.push(c);
                    } else {
                        opStack.push(c);
                    }
                }
            } else
                throw new IllegalArgumentException();
            System.out.println("Resultado: " + prefix);
            System.out.println("Stack: " + opStack);
        }
        while(!opStack.isEmpty())
            prefix.add(opStack.pop());


        return prefix;
    }

    private static int getPriority(Character c) {
        int i=0;
        while(c != operations[i]) {
            i++;
        }
        return i%3;
    }

    private static boolean isOperation(Character c) {
        for(int i=0; i < operations.length; i++)
            if(operations[i] == c)
                return true;
        return false;
    }

    private static boolean isNumber(Character c) {
        return c >= '0' && c <= '9';
    }
}
