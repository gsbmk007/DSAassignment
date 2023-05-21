import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestHarness {
    public static void main(String[] args) {
        DSAGraph graph = new DSAGraph();

        try {
            String csvFile = "location.txt";
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line;

            // Read the CSV file line by line
            while ((line = br.readLine()) != null) {
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
            graph.displayAsMatrix();
            graph.printEdges();
            graph.displayAsList();
            System.out.print("DFS Traversal: ");

            graph.depthFirstSearch(graph.getNode("A"));
            System.out.println();
            graph.breadthFirstSearch("A");
           
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Perform operations on the graph
        // ...
    }
}
