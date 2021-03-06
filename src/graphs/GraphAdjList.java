package graphs;

import java.util.*;

/**
 * @param <V>
 * @param <E>
 */
public abstract class GraphAdjList<V, E extends ArcGraph> {

    protected class Node {
        public V info;
        public boolean visited;
        public int depth;
        // coloreo, etc.
        public List<Arc> adj;
        public int tag;

        public Node(V info) {
            this.info = info;
            this.depth = 0;
            this.tag = 0;
            this.visited = false;
            this.adj = new ArrayList<Arc>();
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((info == null) ? 0 : info.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            final Node other = (Node) obj;
            if (info == null) {
                if (other.info != null)
                    return false;
            } else if (!info.equals(other.info))
                return false;
            return true;
        }
    }

    protected class Arc {
        public E info;
        public Node neighbor;
        public boolean visited;

        public Arc(E info, Node neighbor) {
            super();
            this.info = info;
            this.neighbor = neighbor;
        }
    }

    protected Map<V, Node> nodes; // Para ubicar los nodos r?pidamente
    protected List<Node> nodeList; // Para recorrer la lista de nodos

    public GraphAdjList() {
        this.nodes = new HashMap<V, Node>();
        this.nodeList = new ArrayList<Node>();
    }

    public boolean isEmpty() {
        return nodes.size() == 0;
    }

    public void addVertex(V vertex) {
        if (!nodes.containsKey(vertex)) {
            Node node = new Node(vertex);
            nodes.put(vertex, node);
            nodeList.add(node);
        }
    }

    public void removeVertex(V v) {
        Node node = nodes.get(v);
        if (node == null)
            return;

        // Primero removerlo de la lista de adyacencia de sus vecinos
        Arc e = new Arc(null, node);
        for (Node n : getNodes()) {
            if (!n.equals(node))
                n.adj.remove(e);
        }

        // Eliminar el nodo
        nodes.remove(v);
        nodeList.remove(node);
    }

    public void addArc(V v, V w, E e) {
        Node origin = nodes.get(v);
        Node dest = nodes.get(w);
        if (origin != null && dest != null && !origin.equals(dest)) {
            for (Arc arc : origin.adj) {
                if (arc.neighbor.info.equals(w)) {
                    return;
                }
            }
            origin.adj.add(new Arc(e, dest));
        }
    }

    public void removeArc(V v, V w) {
        Node origin = nodes.get(v);
        if (origin == null)
            return;

        for (int i = 0; i < origin.adj.size(); i++) {
            if ( origin.adj.get(i).neighbor.info.equals(w)) {
                origin.adj.remove(i);
                return;
            }
        }

    }

    public int arcCount() {
        int count = 0;
        for (Node n : getNodes())
            count += n.adj.size();
        return count;
    }

    public E isArc(V v, V w) {
        Node origin = nodes.get(v);
        if (origin == null)
            return null;

        for (Arc e : origin.adj) {
            if (e.neighbor.info.equals(w)) {
                return e.info;
            }
        }
        return null;

    }

    @SuppressWarnings("unchecked")
    public List<V> neighbors(V v) {
        Node node = nodes.get(v);
        if (node != null) {
            List<V> l = new ArrayList(node.adj.size());
            for (Arc e : node.adj) {
                l.add(e.neighbor.info);
            }
        }
        return null;
    }

    public int vertexCount() {
        return nodes.size();
    }

    protected List<Node> getNodes() {
        return nodeList;
    }

    protected void clearMarks() {
        for (Node n : getNodes()) {
            n.visited = false;
            n.depth = 0;
            n.tag = 0;
            for(Arc e : n.adj) {
                e.visited = false;
            }
        }
    }

    public List<V> DFS(V origin) {
        Node node = nodes.get(origin);
        if (node == null)
            return null;
        clearMarks();
        List<V> l = new ArrayList<V>();
        this.DFSIterative(node, l);
        return l;
    }

    protected void DFS(Node origin, List<V> l) {
        if (origin.visited)
            return;
        l.add(origin.info);
        origin.visited = true;
        for (Arc e : origin.adj)
            DFS(e.neighbor, l);
    }

    public List<V> BFS(V origin) {
        Node node = nodes.get(origin);
        if (node == null)
            return null;
        clearMarks();
        List<V> l = new ArrayList<V>();

        Queue<Node> q = new LinkedList<Node>();
        q.add(node);
        node.visited = true;

        while (!q.isEmpty()) {
            node = q.poll();
            l.add(node.info);
            for (Arc e : node.adj) {
                if (!e.neighbor.visited) {
                    e.neighbor.visited = true;
                    q.add(e.neighbor);
                }
            }
        }
        return l;
    }

    public boolean isPath(V origin, V dest) {
        List<V> l = DFS(origin);
        if (l == null)
            return false;
        return l.contains(dest);
    }

    /**
     * Construye una lista donde para cada nodo informa la m?nima distancia al
     * origen
     *
     * @param origin
     */
    public Map<V, Double> Dijkstra(V origin) {
        Node nodeOrigin = nodes.get(origin);
        if (nodeOrigin == null)
            return null;

        clearMarks();
        // Para cada nodo guardar cu?l es la distancia acumulada
        Map<V, Double> distance = new HashMap<V, Double>(nodes.size());

        // Creamos una lista de nodos visitados
        List<Node> visited = new ArrayList<Node>(nodes.size());
        distance.put(origin, 0.0);
        nodeOrigin.visited = true;
        visited.add(nodeOrigin);

        Arc min;
        Double minDist;
        do {
            min = null;
            minDist = 0.0;
            for (Node node : visited) { //Para todos los nodos a los cuales ya encontr� su camino m�nimo
                Double distToNode = distance.get(node.info); //Tomo la distancia del origen hasta el nodo actual
                for (Arc e : node.adj) { //Para todos sus adyacentes
                    if (!e.neighbor.visited //Si no encontr� un camino m�nimo para este vecino
                            && (min == null || e.info.getValue() + distToNode < minDist)) { //min es solo para el primer caso o que los vecinos hasta ahora ya hayan sido visitados, la otra condici�n es para comparar las distancias minimas encontradas hasta ahora
                        min = e; //Para guardar en el mapa el nodo y marcar a este posible candidato como visitado en caso de que se haya encontrado el camino m�nimo para �ste.
                        minDist = e.info.getValue() + distToNode;
                    }
                }
            }
            if (min != null) {
                visited.add(min.neighbor);
                min.neighbor.visited = true;
                distance.put(min.neighbor.info, minDist);
            }
        } while (min != null);

        return distance; //Mapa con todos los valores y sus distancias al origen.
    }

    private void DFSIterative(Node origin, List<V> l) {
        Node node = origin;
        Deque<Node> stack = new LinkedList<Node>();

        clearMarks();
        stack.addFirst(node);
        node.visited = true;
        while(!stack.isEmpty()) {
            node = stack.removeFirst();
            l.add(node.info);
            for(Arc e : node.adj) {
                if(!e.neighbor.visited) {
                    stack.addFirst(e.neighbor);
                    e.neighbor.visited = true;
                }
            }
        }
    }

    public Graph<V, ArcGraph> spanningTree(V origin) {
        if(origin == null)
            return null;
        Node node = nodes.get(origin);
        if(node == null)
            return null;

        Graph<V, ArcGraph> g = new Graph<V, ArcGraph>();
        Queue<Node> q = new LinkedList<Node>();

        clearMarks();
        q.add(node);
        g.addVertex(node.info);
        node.visited = true;
        while(!q.isEmpty()) {
            node = q.poll();
            for(Arc e : node.adj) {
                if(!e.neighbor.visited) {
                    g.addVertex(e.neighbor.info);
                    g.addArc(node.info, e.neighbor.info, null);
                    q.offer(e.neighbor);
                    e.neighbor.visited = true;
                }
            }
        }
        return g;
    }

    public boolean sixGradesSeparation() {
        for(Node node : nodeList) {
            clearMarks();
            if(!limitBFS(node, 6))
                return false;
        }
        return true;
    }

    protected boolean limitBFS(Node origin, int depth) {
        Queue<Node> q = new LinkedList<Node>();
        boolean maxDepthReached = false;
        q.offer(origin);
        origin.depth = 0;
        origin.visited = true;

        while(!q.isEmpty() && !maxDepthReached) {
            Node node = q.poll();
            if(node.depth <= depth) {
                for(Arc e : node.adj) {
                    if(!e.neighbor.visited) {
                        e.neighbor.depth = node.depth + 1;
                        e.neighbor.visited = true;
                        q.offer(e.neighbor);
                    }
                }
            } else {
                maxDepthReached = true;
            }
        }
        return q.isEmpty();
    }

    protected List<V> possibleFriends(V person, int common) {
        Node node = nodes.get(person);
        if(node == null)
            return null;

        List<V> l = new LinkedList<V>();

        node.visited = true;    //Mark myself
        for(Arc e : node.adj) {
            e.neighbor.visited = true;  //Mark my friends
        }
        for(Arc e : node.adj) {
            Node friend = e.neighbor;
            for (Arc eF : friend.adj) {
                Node possibleFriend = eF.neighbor;
                if (!possibleFriend.visited) {  //IF has not been added and is not me and is not a friend
                    possibleFriend.tag++;
                    if(possibleFriend.tag >= common) {
                        l.add(possibleFriend.info);
                        possibleFriend.visited = true;
                    }
                }
            }
        }
        return l;
    }

    protected boolean isBipartite() {
        clearMarks();

//        return isBipartiteRec(nodeList.get(0), -1);
        return isBipartiteBFS();
    }

    private boolean isBipartiteRec(Node node, int prev) {
        if(node.tag != 0) {
            return node.tag != prev;
        }
        node.tag = -prev;
        for(Arc e : node.adj) {
            if(!isBipartiteRec(e.neighbor, -prev))
                return false;
        }
        return true;
    }

    private boolean isBipartiteBFS() {
        Queue<Node> q = new LinkedList<Node>();
        Node node = nodeList.get(0);

        if(node == null)
            return true;
        q.offer(node);
        node.tag = 1;
        while(!q.isEmpty()) {
            node = q.poll();
            for(Arc e : node.adj) {
                if(e.neighbor.tag == 0) {
                    q.offer(e.neighbor);
                    e.neighbor.tag = -node.tag;
                } else if(e.neighbor.tag == node.tag) {
                    return false;
                }
            }
        }
        return true;
    }

    private int countCycles(V from, V to) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);

        if(fromNode == null || toNode == null)
            return 0;
        return countCyclesRec(fromNode, toNode);
    }

    protected int countCyclesRec(Node curr, Node to) {
        if(curr == to) {
            return 1;
        }
        int count = 0;
        curr.visited = true;

        for(Arc e : curr.adj) {
            if(!e.neighbor.visited) {
                count += countCyclesRec(e.neighbor, to);
            }
        }
        curr.visited = false;

        return count;
    }

    public List<V> isCaterpillar() {
        List<V> roots = new LinkedList<V>();

        for(Node origin : nodeList) {
            clearMarks();
            if(isCaterpillarRec(origin, null)) {
                roots.add(origin.info);
            }
        }
        return roots;
    }

    private boolean isCaterpillarRec(Node curr, Node prev) {
        boolean foundPath = false;

        curr.visited = true;
        for(Arc e : curr.adj) {
            if(e.neighbor.visited && prev != e.neighbor) {
                return false;
            }
            if(!e.neighbor.visited && e.neighbor.adj.size() > 1) {
                if(foundPath || !isCaterpillarRec(e.neighbor, curr)) {
                    return false;
                }
                foundPath = true;
            }
        }
        return true;
    }

    public double maxDistance(V origin, V to) {
        Node node = nodes.get(origin);
        if(origin == null)
            return -1;
        clearMarks();
        return maxDistanceRec(node, to, 0, -1);
    }

    protected double maxDistanceRec(Node node, V to, double currWeight, double maxWeight) {
        if(node.info.equals(to))
            return currWeight > maxWeight ? currWeight : maxWeight;
        node.visited = true;
        for(Arc e : node.adj) {
            if(!e.neighbor.visited) {
                maxWeight = maxDistanceRec(e.neighbor, to, currWeight + e.info.getValue(), maxWeight);
            }
        }
        node.visited = false;
        return maxWeight;
    }

    protected boolean isDFS(List<V> values) {
        if(values == null)
            throw new IllegalArgumentException();
        if(values.size() != nodeList.size())
            return false;
        clearMarks();
        return isDFSRec(values, 0, nodes.get(values.get(0)));
    }

    private boolean isDFSRec(List<V> values, int index, Node node) {
        if(index == values.size())
            return true;
        System.out.println("INDEX: " + index);
        System.out.println("NODE: " + node.info + "\t VALUE: " + values.get(index));
        if(values.get(index).equals(node.info)) {
            if(node.visited) {
                return false;
            }
            node.visited = true;
            for(Arc e : node.adj) {
                if(isDFSRec(values, index + 1, e.neighbor)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean nodesHaveEvenEdges() {
        for(Node node : nodeList) {
            if(node.adj.size() % 2 != 0)
                return false;
        }
        return true;
    }
}

