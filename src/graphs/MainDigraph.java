package graphs;


/**
 * Created by Gonzalo on 18/06/2015.
 */
public class MainDigraph {
    public static void main(String[] args) {
//        DiGraph<Integer, MyArc> g = new DiGraph<Integer, MyArc>();
//
//        g.addVertex(1);
//        g.addVertex(2);
//        g.addVertex(3);
//        g.addVertex(4);
//
//        g.addArc(1,4, null);
//        g.addArc(1,2, null);
//        g.addArc(2,3, null);
//        g.addArc(4,3, null);
//
//        System.out.println(g.isStronglyConnected());
//        System.out.println(g.hasCycles());
        DiGraph<String, MyArc> g = new DiGraph<String, MyArc>();

        g.addVertex("CI");
        g.addVertex("PL");
        g.addVertex("EI");
        g.addVertex("CII");
        g.addVertex("EII");
        g.addVertex("EIII");
        g.addVertex("PNL");
        g.addVertex("PE");

        g.addArc("CI", "CII", new MyArc(1));
        g.addArc("CI", "EI", new MyArc(1));
        g.addArc("PL", "PNL", new MyArc(1));
        g.addArc("PL", "PE", new MyArc(1));
        g.addArc("EI", "EII", new MyArc(1));
        g.addArc("EII", "EIII", new MyArc(1));
        g.addArc("EIII", "PE", new MyArc(1));
        g.addArc("CII", "EIII", new MyArc(1));
        g.addArc("CII", "PE", new MyArc(1));
        g.addArc("CII", "PNL", new MyArc(1));

        System.out.println(g.minGraduationTime());

    }
}
