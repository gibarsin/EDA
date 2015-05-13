package misc.keysbalance;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by Gonzalo on 05/04/2015.
 */
public class KeysBalance {
    public static boolean isBalanced(char[] exp) {
        Deque<Character> stack = new LinkedList<Character>();

        for(Character c : exp) {
            if(isKey(c)) {
                if(isClosedKey(c)) {
                    if (!stack.isEmpty() && !matchKeys(c, stack.peek())) {
                        stack.pop();
                    }
                    if (stack.isEmpty()) {
                        return false;
                    } else {
                        stack.pop();
                    }
                } else { //Opening key
                    stack.push(c);
                }
            } else if(!isDigit(c)) {
                throw new IllegalArgumentException();
            }
        }
        if(!stack.isEmpty()) {
            return false;
        }
        return true;
    }

    private static boolean isDigit(Character c) {
        return c >= '0' && c <= '9';
    }

    private static boolean matchKeys(Character closing, Character opening) {
        if(closing == ')' && opening == '(') {
            return true;
        } else if(closing == ']' && opening == '[') {
            return true;
        } else if(closing == '}' && opening == '{') {
            return true;
        }
        return false;
    }

    private static boolean isClosedKey(Character c) {
        return c == ')' || c == ']' || c == '}';
    }

    private static boolean isKey(Character c) {
        return c == '(' || c == ')' || c == '[' || c == ']' || c == '{' || c == '}';
    }
}
