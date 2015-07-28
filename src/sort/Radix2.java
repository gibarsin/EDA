package sort;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Radix2 {
    private static Queue<Integer>[] digits;

    private static void createDigits() {
        digits = new LinkedList[10];
        for(int i=0; i < 10; i++) {
            digits[i] = new LinkedList<Integer>();
        }
    }

    public static void sort(List<Integer> list, int maxDigits) {
        if(digits == null) {
            createDigits();
        }

        int divisor = 1;

        for(int digitIndex=0; digitIndex < maxDigits; digitIndex++) {
            for (int i=0; i < list.size(); i++) {
                int number = list.get(i);
                digits[(number/divisor) % 10].add(number);
            }
            for(int i=0, j=0; i < digits.length; i++) {
                while(!digits[i].isEmpty()) { //Vacio las listas y rearmo la lista segun el digitIndex que use para ordenar
                    list.set(j++, digits[i].remove());
                }
            }
            divisor *= 10;
        }
    }
}
