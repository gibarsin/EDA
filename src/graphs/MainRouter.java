package graphs;

import java.util.Map;

/**
 * Created by Gonzalo on 22/06/2015.
 */
public class MainRouter {
    public static void main(String[] args) {
        Graph<String, MyArc> g = new Graph<String, MyArc>();

        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("D");
        g.addVertex("E");
        g.addVertex("F");
        g.addVertex("G");

        g.addArc("A", "B", new MyArc(2));
        g.addArc("A", "C", new MyArc(2));
        g.addArc("B", "D", new MyArc(4));
        g.addArc("C", "D", new MyArc(4));
        g.addArc("D", "E", new MyArc(1));
        g.addArc("C", "F", new MyArc(1));
        g.addArc("F", "G", new MyArc(2));
        g.addArc("E", "F", new MyArc(1));

        for(Map.Entry<String, String> entry : g.route("A").entrySet()) {
            System.out.println("DEST: " + entry.getKey() + "\t GATEWAY: " + entry.getValue());
        }
    }
}
