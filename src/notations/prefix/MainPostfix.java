package notations.prefix;

import java.util.ArrayList;
import java.util.List;

public class MainPostfix {
    public static void main(String[] args) {
        List<Character> l = new ArrayList<Character>();

        l.add('2');
        l.add('*');
        l.add('(');
        l.add('3');
        l.add('+');
        l.add('4');
        l.add(')');
        l.add('-');
        l.add('1');

        System.out.println(PostfixConverter.convertFromInFix(l));
    }
}
