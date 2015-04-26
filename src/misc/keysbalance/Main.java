package misc.keysbalance;

/**
 * Created by Gonzalo on 05/04/2015.
 */
public class Main {
    public static void main(String[] args) {
        char[] arr = {'(','(','[',']',')','{','(',')','}', ')'};
        char[] arr2 = {'(', ']'};
        char[] arr3 = {')', '('};

        System.out.println(KeysBalance.isBalanced(arr));
        System.out.println(KeysBalance.isBalanced(arr2));
        System.out.println(KeysBalance.isBalanced(arr3));

    }
}
