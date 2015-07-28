package graphs;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Gonzalo on 19/06/2015.
 */
public class Main3 {
    public static void main(String[] args) {
        Graph<String, MyArc> g = new Graph<String, MyArc>();

        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("D");
        g.addVertex("E");

        g.addArc("A", "B", null);
        g.addArc("A", "D", null);
        g.addArc("B", "D", null);
        g.addArc("B", "C", null);
        g.addArc("B", "E", null);
        g.addArc("C", "E", null);

        List<String> values = new LinkedList<String>();

        values.add("A");
        values.add("D");
        values.add("B");
        values.add("C");
        values.add("E");

        System.out.println(g.isDFS(values));

        Graph<String, MyArc> g2 = new Graph<String, MyArc>();

        g2.addVertex("A");
        g2.addVertex("B");
        g2.addVertex("C");
        g2.addVertex("D");
        g2.addVertex("E");
        g2.addVertex("F");

        g2.addArc("A", "B", new MyArc(8));
        g2.addArc("A", "C", new MyArc(1));
        g2.addArc("A", "D", new MyArc(10));
        g2.addArc("B", "C", new MyArc(5));
        g2.addArc("B", "E", new MyArc(9));
        g2.addArc("C", "E", new MyArc(7));
        g2.addArc("C", "D", new MyArc(6));
        g2.addArc("C", "F", new MyArc(2));
        g2.addArc("E", "F", new MyArc(4));
        g2.addArc("F", "D", new MyArc(1));

        System.out.println("----------------------------");

        System.out.println(g2.subgraph("A", 5).DFS("A"));
    }
}
