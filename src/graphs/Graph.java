package graphs;

import java.util.*;

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
        E e = isArc(v, w);
        if ( e == null)
            return false;
        int components = connectedComponents();
        removeArc(v, w);
        int newComponents = connectedComponents();
        addArc(v, w, e);
        return components != newComponents;

    }

    public Graph<V,ArcGraph> DijkstraGraph(V origin) {
        Node node = nodes.get(origin);
        if(node == null)
            return null;
        Graph<V,ArcGraph> g = new Graph<V, ArcGraph>();
        Queue<PQNode> pq = new PriorityQueue<PQNode>();

        clearMarks();
        pq.offer(new PQNode(node, null, null, 0));
        while(!pq.isEmpty()) {
            PQNode pNode = pq.poll();
            if(!node.visited) {
                g.addVertex(pNode.node.info);
                if (pNode.from != null) {    //FOR FIRST CASE
                    g.addArc(pNode.from.info, pNode.node.info, pNode.arc);
                }
                pNode.node.visited = true;
                pNode.node.tag = (int) pNode.distance;
                for (Arc e : pNode.node.adj) {
                    if (!e.neighbor.visited) {
                        pq.offer(new PQNode(e.neighbor, pNode.node, e.info, pNode.distance + e.info.getValue()));
                    }
                }
            }
        }
        return g;
    }

    public void dikjstraNaive(V v) {
        Node node = nodes.get(v);
        if(v == null)
            return;
        Queue<PQNode> q = new PriorityQueue<PQNode>();
        q.offer(new PQNode(node, null, null, 0));

        while(!q.isEmpty()) {
            PQNode pqNode = q.poll();
            if(!pqNode.node.visited) {
                pqNode.node.visited = true;
                System.out.println("NODE: " + pqNode.node.info + "\t DISTANCE: " + pqNode.distance);
                pqNode.node.tag = (int)pqNode.distance;
                for(Arc e : pqNode.node.adj) {
                    if(!e.neighbor.visited) {
                        q.offer(new PQNode(e.neighbor, null, null, pqNode.distance + e.info.getValue()));
                    }
                }
            }
        }
    }

    public Map<V, V> route(V v) {
        Node origin = nodes.get(v);
        if(origin == null)
            return null;
        Queue<PQNode> q = new PriorityQueue<PQNode>();
        Map<V,V> table = new HashMap<V, V>();
        q.offer(new PQNode(origin, null, null, 0));

        table.put(v, null);

        while(!q.isEmpty()) {
            PQNode pNode = q.poll();
            if(!pNode.node.visited) {
                pNode.node.visited = true;
                if(pNode.from != null) {
                    table.put(pNode.node.info, pNode.from.info);
                }
                for(Arc e : pNode.node.adj) {
                    if(!e.neighbor.visited) {
                        Node from;
                        if(pNode.node == origin) {
                            from = e.neighbor;
                        } else {
                            from = pNode.from;
                        }
                        q.offer(new PQNode(e.neighbor, from, null, pNode.distance + e.info.getValue()));
                    }
                }
            }
        }
        return table;
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

    public int countSimpleCycles(V origin) {
        Node originNode = nodes.get(origin);

        if(originNode == null)
            return -1;
        return countSimpleCyclesRec(originNode, originNode, null);
    }

    private int countSimpleCyclesRec(Node curr, Node originNode, Node prev) {
        if(curr.visited) {
            return curr == originNode ? 1 : 0;
        }
        int cycles = 0;

        curr.visited = true;
        for (Arc e : curr.adj) {
            if(e.neighbor != prev) {
                cycles += countSimpleCyclesRec(e.neighbor, originNode, curr);
            }
        }
        curr.visited = false;

        return cycles;
    }

    public Graph<V, E> subgraph(V v, double n) {
        Node node = nodes.get(v);

        if(node == null || n <= 0) {
            return null;
        }
        Queue<PQNode> q = new PriorityQueue<PQNode>();
        Graph<V, E> g = new Graph<V, E>();
        q.offer(new PQNode(node, null, null, 0));

        while(!q.isEmpty()) {
            PQNode pNode = q.poll();
            System.out.println("NODE: " + pNode.node.info + "\t FROM: " + ((pNode.from == null) ? "NULL" : pNode.from.info) + "\t DISTANCE: " + pNode.distance);
            if(!pNode.node.visited) {
                pNode.node.visited = true;
                g.addVertex(pNode.node.info);
                for(Arc e : pNode.node.adj) {
                    if(!e.neighbor.visited) {
                        if(e.info.getValue() + pNode.distance <= n) {
                            q.offer(new PQNode(e.neighbor, pNode.node, e.info, e.info.getValue() + pNode.distance));
                        }
                    }
                }
            }
            if(pNode.arc != null) {
                g.addVertex(pNode.node.info);
                g.addArc(pNode.from.info, pNode.node.info, (E)pNode.arc);
            }
        }

        for(Node gNode : g.nodeList) {
            for(Node gNode2 : g.nodeList) {
                if(gNode != gNode2) {
                    E info = isArc(gNode.info, gNode2.info);
                    if (info != null) {
                        System.out.println("ADDING ARC: " + info.getValue());
                        g.addArc(gNode.info, gNode2.info, info);
                    }
                }
            }
        }
        return g;
    }
}
