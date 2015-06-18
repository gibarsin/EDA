package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graph<V, E extends ArcGraph> extends GraphAdjList<V, E> {

    @Override
    public void addArc(V v, V w, E e) {
        super.addArc(v, w, e);
        super.addArc(w, v, e);
    }

    @Override
    public void removeArc(V v, V w) {
        super.removeArc(v, w);
        super.removeArc(w, v);
    }

    public int degree(V v) {
        Node node = nodes.get(v);
        if (node != null) {
            return node.adj.size();
        }
        return 0;
    }

    public boolean isConnected() {
        if (isEmpty()) {
            return true;
        }
        clearMarks();
        List<Node> l = getNodes();
        List<V> laux = new ArrayList<V>();
        DFS(l.get(0), laux);
        for (Node node : l) {
            if (!node.visited) {
                return false;
            }
        }
        return true;
    }

    public int connectedComponents() {
        clearMarks();
        return pathCount();
    }

    private int pathCount() {
        int count = 0;
        Node node;
        while ((node = unvisited()) != null) {
            count++;
            DFS(node, new ArrayList<V>());
        }
        return count;
    }

    private Node unvisited() {
        for(Node node : getNodes()) {
            if (! node.visited )
                return node;
        }
        return null;
    }

    public boolean cutVertex(V vertex) {
        Node node = nodes.get(vertex);
        if (node == null || node.adj.size() == 0)
            return false;
        clearMarks();
        int components = pathCount();
        clearMarks();
        node.visited = true;
        return components != pathCount();
    }

    public boolean isBridge(V v, V w) {
        E e = isArc(v,w);
        if ( e == null)
            return false;
        int components = connectedComponents();
        removeArc(v, w);
        int newComponents = connectedComponents();
        addArc(v, w, e);
        return components != newComponents;

    }

    public Graph<V,ArcGraph> DijsktraGraph(V origin) {
        Node node = nodes.get(origin);
        if(node == null)
            return null;
        Graph<V,ArcGraph> g = new Graph<V, ArcGraph>();
        Queue<PQNode> pq = new PriorityQueue<PQNode>();

        clearMarks();
        pq.offer(new PQNode(node, null, null, 0));
        node.visited = true;
        while(!pq.isEmpty()) {
            PQNode pNode = pq.poll();
            System.out.print("NODE: " + pNode.node.info);
            g.addVertex(pNode.node.info);
            if(pNode.from != null) {    //FOR FIRST CASE
                System.out.print("\t FROM: " + pNode.from.info);
                g.addArc(pNode.from.info, pNode.node.info, pNode.arc);
            }
            System.out.print("\t ARC: " + pNode.arc);
            System.out.println("\t DISTANCE: " + pNode.distance);
            pNode.node.visited = true;
            pNode.node.tag = (int)pNode.distance;
            for(Arc e : pNode.node.adj) {
                if(!e.neighbor.visited) {
                    pq.offer(new PQNode(e.neighbor, pNode.node, e.info, pNode.distance + e.info.getValue()));
                    e.neighbor.visited = true;
                }
            }
        }
        return g;
    }

    private class PQNode implements Comparable<PQNode> {
        Node node;
        Node from;
        ArcGraph arc;
        double distance;

        public PQNode(Node node, Node from, ArcGraph arc, double distance) {
            this.node = node;
            this.from = from;
            this.arc = arc;
            this.distance = distance;
        }

        @Override
        public int compareTo(PQNode o) {
            return Double.valueOf(distance).compareTo(o.distance);
        }
    }
}
