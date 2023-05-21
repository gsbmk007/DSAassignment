import java.util.Iterator;

public class DSAGraph {
    private DSALinkedList vertices;
    private DSALinkedListEdge edges;


    public DSAGraph() {
        this.vertices = new DSALinkedList();
        this.edges= new DSALinkedListEdge();
    }

    public void addNode(String label) throws Exception {
        if (!hasNode(label)) {
            addNode(label, null);
        } else {
            System.out.println("Node " + label + " already exists.");
        }
    }

    public void addNode(String label, Object value) throws Exception {
        DSAGraphNode node = new DSAGraphNode(label, value);
        vertices.insertLast(node);
        System.out.println("Added Node " + label);
    }

    public void addEdge(String label1, String label2, String edgeLabel, Double edgeValue) throws Exception {
        DSAGraphNode node1 = getNode(label1);
        DSAGraphNode node2 = getNode(label2);
        if (node1 != null && node2 != null) {
            DSAGraphEdge edge = new DSAGraphEdge(node1, node2, edgeLabel, edgeValue);
            System.out.println(edge.toString());
            node1.addEdge(node2);
            node2.addEdge(node1);
            edges.insertFirst(edge);
        }

    }

    public boolean hasNode(String label) {
        return getNode(label) != null;
    }

    public int getNodeCount() {
        return vertices.getCount();
    }

    public int getEdgeCount() {
        int count = 0;
        Iterator<DSAGraphNode> iter = vertices.iterator();
        while (iter.hasNext()) {
            DSAGraphNode node = (DSAGraphNode) iter.next();
            count += node.getAdjacent().getCount();
        }
        return count / 2; // divide by 2 since edges are counted twice in an undirected graph
    }

    public DSAGraphNode getNode(String label) {
        Iterator<DSAGraphNode> iter = vertices.iterator();
        while (iter.hasNext()) {
            DSAGraphNode node = (DSAGraphNode) iter.next();
            if (node.getLabel().equals(label)) {
                return node;
            }
        }
        return null;
    }

    public DSALinkedList getAdjacent(String label) {
        DSAGraphNode node = getNode(label);
        if (node != null) {
            return node.getAdjacent();
        }
        return null;
    }

    public boolean isAdjacent(String label1, String label2) {
        DSALinkedList adjacent = getAdjacent(label1);
        if (adjacent != null) {
            Iterator<DSAGraphNode> iter = adjacent.iterator();
            while (iter.hasNext()) {
                DSAGraphNode node = (DSAGraphNode) iter.next();
                if (node.getLabel().equals(label2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getNodeIndex(String label) {
        Iterator<DSAGraphNode> iter = vertices.iterator();
        int index = 0;
        while (iter.hasNext()) {
            DSAGraphNode node = (DSAGraphNode) iter.next();
            if (node.getLabel().equals(label)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public void displayAsList() {
        Iterator<DSAGraphNode> iter = vertices.iterator();
        while (iter.hasNext()) {
            DSAGraphNode node = (DSAGraphNode) iter.next();
            System.out.print(node.getLabel() + ": ");

            DSALinkedList adjacent = node.getAdjacent();
            Iterator<DSAGraphNode> adjIter = adjacent.iterator();
            while (adjIter.hasNext()) {
                DSAGraphNode adjNode = (DSAGraphNode) adjIter.next();
                System.out.print(adjNode.getLabel());
                if (adjIter.hasNext()) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    public void displayAsMatrix() {
        int n = getNodeCount();
        String[][] matrix = new String[n + 1][n + 1]; // Increase the matrix size by 1
    
        Iterator<DSAGraphNode> iter = vertices.iterator();
        int i = 1; // Start from index 1 instead of 0
        while (iter.hasNext()) {
            DSAGraphNode node = iter.next();
            int index = getNodeIndex(node.getLabel());
            matrix[i][0] = node.getLabel(); // Assign label to the first column
            matrix[0][i] = node.getLabel(); // Assign label to the first row
            i++;
        }
    
        iter = vertices.iterator();
        while (iter.hasNext()) {
            DSAGraphNode node = iter.next();
            DSALinkedList adjacent = node.getAdjacent();
            Iterator<DSAGraphNode> adjIter = adjacent.iterator();
            while (adjIter.hasNext()) {
                DSAGraphNode adjNode = adjIter.next();
                int row = getNodeIndex(node.getLabel()) + 1; // Increment row index by 1
                int col = getNodeIndex(adjNode.getLabel()) + 1; // Increment column index by 1
                matrix[row][col] = "1";
            }
        }
    
        for (int row = 0; row <= n; row++) { // Include the first row
            for (int col = 0; col <= n; col++) { // Include the first column
                System.out.print((matrix[row][col] == null ? "0" : matrix[row][col]) + " ");
            }
            System.out.println();
        }
    }
    
    public void depthFirstSearch(DSAGraphNode startNode) {
        if (startNode == null) {
            return;
        }
        
        startNode.setVisited();
        System.out.print(startNode.getLabel() + "->");
        DSALinkedList adjacent = startNode.getAdjacent();
        Iterator<DSAGraphNode> iter = adjacent.iterator();
        while (iter.hasNext()) {
            DSAGraphNode adjNode = (DSAGraphNode) iter.next();
            if (!adjNode.getVisited()) {
                depthFirstSearch(adjNode);
            }
        }
    }


    // Test code 
    public void printEdges() {
        Iterator<DSAGraphEdge> iter = edges.iterator();
        while (iter.hasNext()) {
            DSAGraphEdge edge = iter.next();
            System.out.println("Edge: " + edge.toString());
        }
    }

    public void breadthFirstSearch(String startNodeLabel) {
        DSAGraphNode startNode = getNode(startNodeLabel);
        if (startNode == null) {
            System.out.println("Start node not found.");
            return;
        }

        DSAGraphNode[] visited = new DSAGraphNode[getNodeCount()];
        int visitedCount = 0;

        DSAGraphNode[] queue = new DSAGraphNode[getNodeCount()];
        int front = 0;
        int rear = 0;

        visited[visitedCount++] = startNode;
        queue[rear++] = startNode;

        System.out.print("BFS Traversal: ");

        while (front != rear) {
            DSAGraphNode currentNode = queue[front++];
            System.out.print(currentNode.getLabel() + "->");

            DSALinkedList adjacent = currentNode.getAdjacent();
            if (adjacent != null) {
                Iterator<DSAGraphNode> iter = adjacent.iterator();
                while (iter.hasNext()) {
                    DSAGraphNode adjNode = iter.next();
                    boolean isVisited = false;
                    for (int i = 0; i < visitedCount; i++) {
                        if (visited[i] == adjNode) {
                            isVisited = true;
                            break;
                        }
                    }
                    if (!isVisited) {
                        visited[visitedCount++] = adjNode;
                        queue[rear++] = adjNode;
                    }
                }
            }
        }

        System.out.println();
    }

//   public void breadthFirstSearch(String startNodeLabel) {
//         DSAGraphNode startNode = graph.getNode(startNodeLabel);
//         if (startNode == null) {
//             System.out.println("Start node not found.");
//             return;
//         }

//         // Create a queue to store visited nodes
//         DSALinkedListQueue queue = new DSALinkedListQueue();

//         // Mark the start node as visited and enqueue it
//         startNode.setVisited();
//         queue.enqueue(startNode);

//         System.out.print("BFS Traversal: ");

//         while (!queue.isEmpty()) {
//             DSAGraphNode currentNode = (DSAGraphNode) queue.dequeue();
//             System.out.print(currentNode.getLabel() + " ");

//             // Get the adjacent nodes of the current node
//             DSALinkedList adjacent = graph.getAdjacent(currentNode.getLabel());
//             if (adjacent != null) {
//                 // Iterate through the adjacent nodes
//                 Iterator<DSAGraphNode> iter = adjacent.iterator();
//                 while (iter.hasNext()) {
//                     DSAGraphNode adjNode = iter.next();
//                     if (!adjNode.getVisited()) {
//                         // Mark the adjacent node as visited and enqueue it
//                         adjNode.setVisited();
//                         queue.enqueue(adjNode);
//                     }
//                 }
//             }
//         }

//         System.out.println();
//     }


}
