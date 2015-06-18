package graphs;

public class DiGraph<V, E extends ArcGraph> extends GraphAdjList<V, E> {

    public int inDegree(V v) {
        int count = 0;
        Node node = nodes.get(v);
        for (Node n : getNodes()) { // Recorremos lista de nodos
            if (!n.equals(node)) {
                for (Arc adj : n.adj)
                    // Recorremos lista de adyacencia
                    if (adj.neighbor.equals(node))
                        count++;
            }
        }
        return count;
    }

    public int outDegree(V v) {
        Node node = nodes.get(v);
        if (node != null) {
            return node.adj.size();
        }
        return 0;
    }

    public boolean hasCycles() {
        clearMarks();
        Node origin = nodeList.get(0);
        return hasCyclesRec(origin);
    }

    private boolean hasCyclesRec(Node node) {
        node.visited = true;

        for(Arc e : node.adj) {
            if(e.neighbor.visited || hasCyclesRec(e.neighbor)) {
                return true;
            }
        }
        node.visited = false;
        return false;
    }

    public boolean isStronglyConnected() {
        for(Node node : nodeList) {
            clearMarks();
            if(isStronglyConnectedRec(node) != nodeList.size())
                return false;
        }
        return true;
    }

    private int isStronglyConnectedRec(Node node) {
        node.visited = true;
        int count = 0;

        for(Arc e : node.adj) {
            if(!e.neighbor.visited) {
                count += isStronglyConnectedRec(e.neighbor);
            }
        }
        return count + 1;
    }


}
