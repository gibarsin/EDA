package tp_9.table;

import java.util.Random;

/**
 * Created by Gonzalo on 17/06/2015.
 */
public class StrictTable {
    private boolean[][] compatibility;
    private int people;
    private int tableSize;

    public StrictTable(int people) {
        this.people = people;
        this.tableSize = people/2;

        compatibility = new boolean[people][people];

        Random rand = new Random();
        for(int i = 0; i < people; i++) {
            for(int j = 0; j < people; j++) {
                boolean value = i == j || rand.nextBoolean();
                compatibility[i][j] = value;
            }
        }
    }

    public boolean solve() {
        Solution solution = new Solution();

        if(solveRec(solution, 0, 0)) {
            System.out.println("SOLUTION FOUND");
            return true;
        }
        return false;
    }

    private boolean solveRec(Solution solution, int guestNumber, int guestSat) {
        if(guestNumber == people) {
            if(guestSat == tableSize) {
                return solution.evaluate();
            }
            return false;
        }
        solution.setValue(guestNumber, true);
        if(!solveRec(solution, guestNumber + 1, guestSat + 1)) {
            solution.setValue(guestNumber, false);
            return solveRec(solution, guestNumber + 1, guestSat);
        }
        return true;
    }


    private class Solution {
        boolean[] solution;

        public Solution() {
            solution = new boolean[people];
        }

        public boolean evaluate() {
            for(int i = 0; i < people; i++) {
                for(int j = 0; j < people; j++) {
                    if(solution[i] == solution[j] && !compatibility[i][j]) {    //SAME TABLE AND DON'T LIKE
                        return false;
                    }
                }
            }
            return true;
        }

        public void setValue(int guestNumber, boolean b) {
            solution[guestNumber] = b;
        }

        public void print() {
            System.out.println("PRINTING SOLUTION...");
            for(int i = 0; i < people; i++) {
                System.out.print(solution[i] + "\t");
            }
            System.out.println();
            System.out.println();
        }
    }
}
