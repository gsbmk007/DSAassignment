import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestHarness {
    public static void main(String[] args) {
        DSAGraph graph = new DSAGraph();

        try {
            String locationData = "location.txt";

            String UAVdata="UAVdata.txt";
            BufferedReader br = new BufferedReader(new FileReader(locationData));
           
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
            System.out.println("rint edges ");
            graph.printEdges();
            graph.displayAsList();
            System.out.print("DFS Traversal: ");

            graph.depthFirstSearch(graph.getNode("A"));
            System.out.println();
            graph.breadthFirstSearch("A");
            System.out.println(graph.getvalue("A"));

            BufferedReader UAVdataReader= new BufferedReader(new FileReader(UAVdata));
            String Data;

            while ((Data=UAVdataReader.readLine())!=null){
                String[] values = Data.split(" ");
                // System.out.println(values[0]+" "+values[1]+" "+values[2]+" "+values[3]);
                naturalElements data = new naturalElements(Integer .parseInt(values[1]),Integer.parseInt(values[2]),Integer.parseInt(values[3]));
                graph.getNode(values[0]).setValue(data);
                // System.out.println(data.toString());

               data=(naturalElements)graph.getNode(values[0]).getValue();
               System.out.println(data.toString());
            }

                System.out.println("F "+graph.getNode("F").getValue().toString());
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
