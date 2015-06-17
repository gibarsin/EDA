package tp_9.horse;

/**
 * Created by Gonzalo on 17/06/2015.
 */
public class Main {
    public static void main(String[] args) {
        HorseWalk horseWalk = new HorseWalk(8, 8);

        horseWalk.solve();

        for(Board sol : horseWalk.getSolutions()) {
            sol.print();
        }
    }
}
