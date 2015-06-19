package graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    public int minGraduationTime() {
        Queue<Node> q = new LinkedList<Node>();
        Node node = null;

        for(Node starter : nodeList) {
            if(inDegree(starter.info) == 0) {
                q.offer(starter);
            }
        }
        clearMarks();
        while(!q.isEmpty()) {
            node = q.poll();
            for(Arc e : node.adj) {
                e.neighbor.tag = (int) Math.max(e.neighbor.tag, node.tag + e.info.getValue());
                q.offer(e.neighbor);
            }
        }
        if (node != null) {
            return node.tag + 1;
        }
        return -1;
    }

    public List<V> unlockedCourses(List<V> passed) {
        clearMarks();
        List<V> unlocked = new LinkedList<V>();

        for(V course : passed) {
            Node courseNode = nodes.get(course);
            for(Arc e : courseNode.adj) {
                e.neighbor.tag++;
                if(e.neighbor.tag == inDegree(e.neighbor.info)) {
                    unlocked.add(e.neighbor.info);
                }
            }
        }
        return unlocked;
    }


}
