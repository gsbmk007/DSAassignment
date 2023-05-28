import java.util.Iterator;
import java.util.Queue;

import javax.swing.text.html.parser.Element;

import java.lang.String;

public class DSAGraph {
    public DSALinkedListNode vertices;
    public DSALinkedListEdge edges;

    public DSAGraph() {
        this.vertices = new DSALinkedListNode();
        this.edges = new DSALinkedListEdge();
    }

    public boolean addNode(String label) throws Exception {
        if (!hasNode(label)) {
            insertNode(label, null);
            return true;
        } else {
            // System.out.println("Node " + label + " already exists.");
            return false;
        }
    }

    public void insertNode(String label, naturalElements value) throws Exception {

        DSAGraphNode node = new DSAGraphNode(label, value);
        vertices.insertLast(node);
        // System.out.println("Added Node " + label);
    }

    public void addEdge(String label1, String label2, String edgeLabel, Double edgeValue) throws Exception {
        DSAGraphNode node1 = getNode(label1);
        DSAGraphNode node2 = getNode(label2);
        if (node1 != null && node2 != null) {
            DSAGraphEdge edge = new DSAGraphEdge(node1, node2, edgeLabel, edgeValue);
            // System.out.println(edge.toString());
            node1.addEdge(node2);
            node2.addEdge(node1);
            edges.insertFirst(edge);
            System.out.println("Added Edge "+edgeLabel+" "+edgeValue);
        }

    }

    public void getEdge() {

        Iterator<DSAGraphEdge> iter = edges.iterator();
        while (iter.hasNext()) {
            DSAGraphEdge edge = (DSAGraphEdge) iter.next();
            System.out.println(edge.getLabel() + " " + edge.getValue());
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
        // System.out.println("Getting"+label);
        Iterator<DSAGraphNode> iter = vertices.iterator();
        while (iter.hasNext()) {
            DSAGraphNode node = (DSAGraphNode) iter.next();
            if (node.getLabel().equals(label)) {
                return node;
            }
        }
        return null;
    }

    public DSALinkedListNode getAdjacent(String label) {
        DSAGraphNode node = getNode(label);
        if (node != null) {
            return node.getAdjacent();
        }
        return null;
    }

    public boolean isAdjacent(String label1, String label2) {
        DSALinkedListNode adjacent = getAdjacent(label1);
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

    public int getNodeIndex(String label) {
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
    public DSAGraphNode getNodeAtIndex(int index) {
        if (index < 0 || index >= vertices.getCount()) {
            return null;
        }
    
        Iterator<DSAGraphNode> iter = vertices.iterator();
        int currentIndex = 0;
    
        while (iter.hasNext()) {
            DSAGraphNode node = iter.next();
            if (currentIndex == index) {
                return node;
            }
            currentIndex++;
        }
    
        return null;
    }
    public void displayAsList() {
        Iterator<DSAGraphNode> iter = vertices.iterator();
        while (iter.hasNext()) {
            DSAGraphNode node = (DSAGraphNode) iter.next();
            System.out.print(node.getLabel() + ": ");

            DSALinkedListNode adjacent = node.getAdjacent();
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
            DSALinkedListNode adjacent = node.getAdjacent();
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

    public void depthFirstSearch(String start, DSAShufflingQueue queue) throws Exception {
        DSAGraphNode startNode = this.getNode(start);

        if (startNode == null) {
            return;
        }
        startNode.setVisited();
        System.out.print(startNode.getLabel() + "->");
        queue.enqueue(startNode);
        DSALinkedListNode adjacent = startNode.getAdjacent();
        Iterator<DSAGraphNode> iter = adjacent.iterator();
        while (iter.hasNext()) {
            DSAGraphNode adjNode = (DSAGraphNode) iter.next();
            if (!adjNode.getVisited()) {
                depthFirstSearch(adjNode.getLabel(), queue);
            }
        }
    }

    // Test code
    public void printEdges() {
        DSAGraphEdge c;
        Iterator iter = edges.iterator();
        while (iter.hasNext()) {
            c = (DSAGraphEdge) iter.next();
            System.out.println("Edge: " + c.toString());
        }
    }

    public String breadthFirstSearch(String startNodeLabel, DSAShufflingQueue Queue) throws Exception {
        DSAGraphNode startNode = getNode(startNodeLabel);
        String str = "";
        if (startNode == null) {
            str = "Start node not found.";
            return str;
        }

        DSAGraphNode[] visited = new DSAGraphNode[getNodeCount()];
        int visitedCount = 0;

        DSAGraphNode[] queue = new DSAGraphNode[getNodeCount()];
        int front = 0;
        int rear = 0;

        visited[visitedCount++] = startNode;
        queue[rear++] = startNode;

        // System.out.print("BFS Traversal: ");

        while (front != rear) {
            DSAGraphNode currentNode = queue[front++];
            str += "" + currentNode.getLabel() + "->";
            Queue.enqueue(currentNode);

            DSALinkedListNode adjacent = currentNode.getAdjacent();
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
        return str;

    }

    public Object getvalue(String nodelable) {
        Object value = this.getNode(nodelable).getValue();

        return value;
    }

    public DSAStack depthFirstSearch(String startNodeLabel) throws Exception {
        DSAGraphNode startNode = getNode(startNodeLabel);

        if (startNode == null) {
            System.out.println("Start node not found.");
            return null;
        }

        DSAStack dfsStack = new DSAStack();

        // Initialize visited flag for all nodes
        Iterator<DSAGraphNode> nodeIter = vertices.iterator();
        while (nodeIter.hasNext()) {
            DSAGraphNode node = nodeIter.next();
            node.clearVisited();
        }

        dfsRecursive(startNode, dfsStack);

        return dfsStack;
    }

    public void dfsRecursive(DSAGraphNode currentNode, DSAStack dfsStack) {
        currentNode.setVisited();
        dfsStack.push(currentNode);

        DSALinkedListNode adjacent = currentNode.getAdjacent();
        if (adjacent != null) {
            Iterator<DSAGraphNode> iter = adjacent.iterator();
            while (iter.hasNext()) {
                DSAGraphNode adjNode = iter.next();
                if (!adjNode.getVisited()) {
                    dfsRecursive(adjNode, dfsStack);
                }
            }
        }
    }

    public DSAStack shortestPathBFS(String startNodeLabel, String endNodeLabel) throws Exception {
        DSAGraphNode startNode = getNode(startNodeLabel);
        DSAGraphNode endNode = getNode(endNodeLabel);

        if (startNode == null || endNode == null) {
            System.out.println("One or both nodes not found in the graph.");
            return null;
        }

        // Initialize visited flag for all nodes
        Iterator<DSAGraphNode> nodeIter = vertices.iterator();
        while (nodeIter.hasNext()) {
            DSAGraphNode node = nodeIter.next();
            node.clearVisited();
        }

        DSAStack shortestPathStack = new DSAStack();

        DSAGraphNode[] queue = new DSAGraphNode[getNodeCount()];
        int front = 0;
        int rear = 0;

        startNode.setVisited();
        queue[rear++] = startNode;

        while (front != rear) {
            DSAGraphNode currentNode = queue[front++];

            if (currentNode.equals(endNode)) {
                // Shortest path found, construct and return the path queue
                return constructPathQueue(startNode, endNode, shortestPathStack);
            }

            DSALinkedListNode adjacent = currentNode.getAdjacent();
            if (adjacent != null) {
                Iterator<DSAGraphNode> iter = adjacent.iterator();
                while (iter.hasNext()) {
                    DSAGraphNode adjNode = iter.next();
                    if (!adjNode.getVisited()) {
                        adjNode.setVisited();
                        adjNode.setPrevious(currentNode);
                        queue[rear++] = adjNode;
                    }
                }
            }
        }

        // No path found between the given nodes
        System.out.println("No path found between the given nodes.");
        return null;
    }

    public DSAStack constructPathQueue(DSAGraphNode startNode, DSAGraphNode endNode, DSAStack pathStack)
            throws Exception {
        DSAGraphNode currNode = endNode;

        while (currNode != null) {
            pathStack.push(currNode);
            System.out.println(currNode.toString());
            currNode = currNode.getPrevious();
        }

        // Reverse the queue to obtain the path in the correct order

        return pathStack;
    }

    public void removeNode(String value) throws Exception {
        vertices.removeNode(getNode(value));

    }

}
