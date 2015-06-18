package graphs;

public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Graph<Integer, MyArc> g = new Graph<Integer, MyArc>();

        assert g.isEmpty();

        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);
        g.addVertex(6);
        g.addVertex(7);
        g.addVertex(8);

        g.addArc(1, 2, new MyArc(12));
        g.addArc(1, 6, new MyArc(16));
        g.addArc(2, 3, new MyArc(23));
        g.addArc(3, 4, new MyArc(34));
        g.addArc(3, 5, new MyArc(35));
        g.addArc(4, 5, new MyArc(45));
        g.addArc(6, 7, new MyArc(67));
        g.addArc(6, 8, new MyArc(68));
//        g.addArc(1, 2, null);
//        g.addArc(1, 6, null);
//        g.addArc(1, 4, null);
//        g.addArc(2, 4, null);
//        g.addArc(2, 3, null);
//        g.addArc(3, 6, null);
//        g.addArc(3, 7, null);
//        g.addArc(4, 6, null);
//        g.addArc(4, 5, null);
//        g.addArc(5, 6, null);
//        g.addArc(6, 7, null);

//        System.out.println(g.possibleFriends(1,2));
        System.out.println(g.DijsktraGraph(2).BFS(2));

    }
}
