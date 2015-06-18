package graphs;


/**
 * Created by Gonzalo on 18/06/2015.
 */
public class MainDigraph {
    public static void main(String[] args) {
        DiGraph<Integer, MyArc> g = new DiGraph<Integer, MyArc>();

        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);

        g.addArc(1,4, null);
        g.addArc(1,2, null);
        g.addArc(2,3, null);
        g.addArc(4,3, null);

        System.out.println(g.isStronglyConnected());
        System.out.println(g.hasCycles());
    }
}
