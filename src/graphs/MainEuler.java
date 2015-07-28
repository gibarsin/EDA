package graphs;

/**
 * Created by Gonzalo on 20/06/2015.
 */
public class MainEuler {
    public static void main(String[] args) {
        Graph<Character,MyArc> g = new Graph<Character, MyArc>();

        g.addVertex('A');
        g.addVertex('B');
        g.addVertex('C');
        g.addVertex('D');
        g.addVertex('E');

        g.addArc('A', 'B', null);
        g.addArc('A', 'D', null);
        g.addArc('B', 'D', null);
        g.addArc('B', 'C', null);
        g.addArc('B', 'E', null);
        g.addArc('C', 'E', null);

        System.out.println();
    }
}
