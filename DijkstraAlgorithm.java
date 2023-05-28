import java.util.Iterator;

public class DijkstraAlgorithm {
    private static final int INFINITY = Integer.MAX_VALUE;

    // Dijkstra's algorithm to find the shortest path between two nodes in a graph
    public static void dijkstra(DSAGraph graph, String startNodeLabel, String endNodeLabel) {
        // Get the start and end nodes from the graph
        DSAGraphNode startNode = graph.getNode(startNodeLabel);
        DSAGraphNode endNode = graph.getNode(endNodeLabel);

        // Check if the start or end nodes are not found in the graph
        if (startNode == null || endNode == null) {
            System.out.println("One or both nodes not found in the graph.");
            return;
        }

        int numNodes = graph.getNodeCount();
        int[] distances = new int[numNodes];       // Array to store the distances from the start node
        boolean[] visited = new boolean[numNodes]; // Array to keep track of visited nodes
        DSAGraphNode[] previousNodes = new DSAGraphNode[numNodes]; // Array to store previous nodes in the shortest path

        // Initialize distances to infinity and visited flags for all nodes
        for (int i = 0; i < numNodes; i++) {
            distances[i] = INFINITY;
            visited[i] = false;
        }

        // Set distance of start node to 0
        distances[graph.getNodeIndex(startNodeLabel)] = 0;

        // Find the shortest path
        for (int i = 0; i < numNodes; i++) {
            int minDistance = INFINITY;
            int minIndex = -1;

            // Find the node with the minimum distance among unvisited nodes
            for (int j = 0; j < numNodes; j++) {
                if (!visited[j] && distances[j] < minDistance) {
                    minDistance = distances[j];
                    minIndex = j;
                }
            }

            // If no reachable nodes remaining, exit the loop
            if (minIndex == -1) {
                break;
            }

            DSAGraphNode currentNode = graph.getNodeAtIndex(minIndex);
            visited[minIndex] = true;

            // Iterate over adjacent nodes of the current node
            DSALinkedListNode adjacentNodes = currentNode.getAdjacent();
            if (adjacentNodes != null) {
                Iterator<DSAGraphNode> iterator = adjacentNodes.iterator();
                while (iterator.hasNext()) {
                    DSAGraphNode adjacentNode = iterator.next();
                    int adjacentIndex = graph.getNodeIndex(adjacentNode.getLabel());

                    // If the adjacent node is unvisited
                    if (!visited[adjacentIndex]) {
                        int distanceThroughCurrent = distances[minIndex] + 1; // Assuming edge weight is 1

                        // If the new distance is smaller, update the distance and set the previous node
                        if (distanceThroughCurrent < distances[adjacentIndex]) {
                            distances[adjacentIndex] = distanceThroughCurrent;
                            previousNodes[adjacentIndex] = currentNode;
                        }
                    }
                }
            }
        }

        // Print the efficient path
        printEfficientPath(graph, previousNodes, startNode, endNode);
    }

    // Print the efficient path from the start to end node
    private static void printEfficientPath(DSAGraph graph, DSAGraphNode[] previousNodes, DSAGraphNode startNode, DSAGraphNode endNode) {
        DSAStack pathStack = new DSAStack();
        DSAGraphNode currentNode = endNode;

        // Traverse the previous nodes to build the efficient path
        while (currentNode != null) {
            pathStack.push(currentNode);
            currentNode = previousNodes[graph.getNodeIndex(currentNode.getLabel())];
        }

        System.out.print("Efficient Path: ");
        while (!pathStack.isEmpty()) {
            DSAGraphNode node = (DSAGraphNode) pathStack.pop();
            System.out.print(node.getLabel());
            if (node != endNode) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}
