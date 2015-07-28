package graphs;

/**
 * Created by Gonzalo on 22/06/2015.
 */
public class MainDikjstra {
    public static void main(String[] args) {
        Graph<String, MyArc> g = new Graph<String, MyArc>();

        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("D");
        g.addVertex("E");
        g.addVertex("F");

        g.addArc("A", "B", new MyArc(2));
        g.addArc("A", "C", new MyArc(25));
        g.addArc("B", "D", new MyArc(2));
        g.addArc("B", "C", new MyArc(1));
        g.addArc("B", "F", new MyArc(30));
        g.addArc("C", "E", new MyArc(26));
        g.addArc("E", "F", new MyArc(1));

        g.dikjstraNaive("A");
    }
}
