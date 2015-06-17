package tp_9.table;

import tp_9.Board;

import java.util.Random;

/**
 * Created by Gonzalo on 17/06/2015.
 */
public class TableCompatibility {
    private final int TABLES_NUMBER = 2;
    private Board compatibility;
    private Solution currSol, bestSol;
    private int bestEval;

    public TableCompatibility(int people) {
        currSol = new Solution(people);
        bestSol = new Solution(people);
        bestEval = 0;
        compatibility = new Board(people, people);

        Random rand = new Random();

        for(int i = 0; i < people; i++) {
            for(int j = 0; j < people; j++) {
                int value = i == j ? 1 : rand.nextInt((1 - 0) + 1) + 0;
                compatibility.set(i, j, value);
            }
        }
    }

    public void solve() {
        solveRec(0, 0);
    }

    private void solveRec(int guest, int guestSat) {
        if(guest == currSol.getSize()) {
            if(guestSat == currSol.getSize() / TABLES_NUMBER) {
                int currEval = currSol.eval();

                System.out.println("CURR = " + currEval + " BEST = " + bestEval);

                if(currEval > bestEval) {
                    bestSol = currSol.clone();
                    bestEval = currEval;
                }
            }
            return;
        }

        currSol.set(guest, 1);
        solveRec(guest + 1, guestSat + 1);
        currSol.set(guest, 0);
        solveRec(guest + 1, guestSat);
    }

    private Solution findBestNeighbour() {
        Solution sol = new Solution(currSol.getSize());

        return sol;
    }

    public void printCompatibilty() {
        System.out.println("MATRIZ DE COMPATABILIDAD");
        for(int i = 0; i < compatibility.getRowsSize(); i++) {
            for(int j = 0; j < compatibility.getColsSize(); j++) {
                System.out.print(compatibility.get(i, j) + "\t");
            }
            System.out.println();
        }
    }

    public void printSolution() {
        bestSol.print();
    }

    private class Solution {
        int[] sol;

        public Solution(int people) {
            sol = new int[people];
        }

        public int eval() {
            int[] tables = new int[TABLES_NUMBER];

            for (int i = 0; i < sol.length; i++) {
                for (int j = 0; j < sol.length; j++) {
                    if (sol[i] == sol[j]) {
                        tables[sol[i]] += compatibility.get(i, j);
                    }
                }
            }
            return tables[0] + tables[1];
        }

        public void set(int i, int value) {
            sol[i] = value;
        }

        public int getSize() {
            return sol.length;
        }

        public Solution clone() {
            Solution copy = new Solution(sol.length);

            copy.sol = this.sol.clone();
            return copy;
        }

        public void print() {
            System.out.println("SOLUCION");
            for(int i = 0; i < sol.length; i++) {
                System.out.print(sol[i] + "\t");
            }
            System.out.println();
        }
    }
}
