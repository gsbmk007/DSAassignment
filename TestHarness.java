import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TestHarness {
    public static void main(String[] args) {
        DSAGraph graph = new DSAGraph();
        DSAShufflingQueue DFSqueue = new DSAShufflingQueue();
        DSAShufflingQueue BFSqueue = new DSAShufflingQueue();
        DSAShufflingQueue path = new DSAShufflingQueue();
        DSAGraphNode currentNode = new DSAGraphNode(null);
        Scanner scanner = new Scanner(System.in);

        try {
            String locationDataFile = "location.txt";
            String UAVdataFile = "UAVdata.txt";

            // Read location data file
            BufferedReader locationDataReader = new BufferedReader(new FileReader(locationDataFile));

            String line;

            // Read the CSV file line by line
            while ((line = locationDataReader.readLine()) != null) {
                String[] values = line.split(" ");

                if (values.length >= 3) {
                    String label1 = values[0];
                    String label2 = values[1];
                    double weight = Double.parseDouble(values[2]);

                    // Add nodes to the graph
                    graph.addNode(label1);
                    graph.addNode(label2);
                    // Add edges to the graph
                    graph.addEdge(label1, label2, label1.concat("").concat(label2), weight);
                }
            }

            locationDataReader.close();

            // graph.displayAsMatrix();
            // System.out.println("Print edges:");
            // graph.printEdges();
            System.out.println(" Prints the graph as list ");
            graph.displayAsList();

            // System.out.println("DFS:");
            // Perform DFS traversal
            // graph.depthFirstSearch("A", DFSqueue);

            // System.out.println("\nBFS:");
            // Perform BFS traversal
            // System.out.println(graph.breadthFirstSearch("A", BFSqueue));

            // Read UAV data file
            BufferedReader UAVdataReader = new BufferedReader(new FileReader(UAVdataFile));
            String dataLine;

            while ((dataLine = UAVdataReader.readLine()) != null) {
                String[] values = dataLine.split(" ");

                // Parse temperature, humidity, and wind speed values
                int temperature = Integer.parseInt(values[1]);
                int humidity = Integer.parseInt(values[2]);
                int windSpeed = Integer.parseInt(values[3]);

                // Create naturalElements object with the data
                naturalElements data = new naturalElements(temperature, humidity, windSpeed);
                // System.out.println(values[0]+":"+data.toString());
                // Set the data as the value of the corresponding graph node
                graph.getNode(values[0]).setValue(data);
            }

            UAVdataReader.close();
            String node;

            System.out.println("Choose Option with number");
            System.out.println("1.Find shortest path Between Nodes");

            System.out.println("2.Insert node");
            System.out.println("3.Delete Node ");

            int option = scanner.nextInt();
            switch (option) {
                case 2:
                    System.out.println("Enter Node to add");
                    node = scanner.next();
                    System.out.println("Enter Value for node " + node);
                    System.out.print("Value: ");
                    Object value = scanner.next();

                    if (graph.addNode(node)) {
                        System.out.println("Node Added Successfully ");
                        graph.getNode(node).setValue(value);

                    }

                    break;
                case 1:

                    System.out.println("Enter the Node to be found");
                    System.out.print("Start: ");

                    node = scanner.next();
                    System.out.print("End: ");

                    String end = scanner.next();

                    while (!graph.hasNode(end)) {
                        System.out.println("Please enter a valid node");
                        System.out.print("End: ");
                        end = scanner.next();

                    }

                    // Find the shortest path from node A to node F usin2g DFS
                    try {
                        if (graph.getNode(node) != null) {
                            path = graph.getShortestPath(node, end, DFSqueue);

                            boolean reached = false;

                            while (!reached) {
                                currentNode = (DSAGraphNode) path.dequeue();

                                System.out.println("Location: " + currentNode.getLabel() + ": "
                                        + ((naturalElements) currentNode.getValue()).toString());

                                path.enqueue(currentNode);

                                if (currentNode.getLabel().equals(end))
                                    reached = true;
                            }
                        } else {
                            System.out.println("Cannot find " + node + "Try again ");
                        }

                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    break;
                case 3:
                    System.out.println("Enter the Node to Be deleted  ");
                    System.out.println("Node: ");
                    node = scanner.next();
                    while (!graph.hasNode(node)) {
                        System.out.println("Please enter a valid node");
                        System.out.print("End: ");
                        node = scanner.next();

                    }
                    if (graph.hasNode(node)) {
                        System.out.println("Deleting node " + node);
                    }

                default:
                    break;
            }
            // Task 3 searn inser and delte

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
