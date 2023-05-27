import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestHarness {
    public static void main(String[] args) {
        DSAGraph graph = new DSAGraph();
        DSAShufflingQueue DFSqueue = new DSAShufflingQueue();
        DSAShufflingQueue BFSqueue = new DSAShufflingQueue();
        DSAShufflingQueue path = new DSAShufflingQueue();
        DSAGraphNode currentNode = new DSAGraphNode(null);
        DSAHashTable hashTable = new DSAHashTable(1000);
        String os = System.getProperty("os.name");
        DSAHeap heap = new DSAHeap(1000); // No need to specify initial size
        // Object [][]array=new Object[11][2];
        // int i=0;



        Scanner scanner = new Scanner(System.in);

        try {
            String locationDataFile = "location.txt";
            String UAVdataFile = "UAVdata.txt";
            Path filePath = Paths.get(UAVdataFile);

            // Read location data file
            BufferedReader locationDataReader = new BufferedReader(new FileReader(locationDataFile));

            String line;
            int lineCount = (int) Files.lines(filePath).count();
            naturalElements[] elements = new naturalElements[100];

            lineCount = 0;
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
            System.out.println("\nTASK 1:\n Printing the Graph as Adjacency List \n\n");
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
                naturalElements data = new naturalElements(values[0], temperature, humidity, windSpeed);
                // System.out.println(values[0]+":"+data.toString());
                // Set the data as the value of the corresponding graph node
                graph.getNode(values[0]).setValue(data);

                    heap.add(data.riskInteger(), data);
                hashTable.put(values[0], data);
                // array[i][0]=data.riskInteger();
                // i++;
                // array[i][1]=data;
                // System.out.println("   " + hashTable.get(values[0]).toString());
                elements[lineCount] = data;
                lineCount++;
            }

            // i=0;
     
            
            UAVdataReader.close();
            String node;
            // System.out.println("Getting edges");
            // graph.getEdge();
            // System.out.println(hashTable.get("A"));
            // System.out.println("Getting sp");
            DSAStack stack = new DSAStack();
            // System.out.println(graph.getNode("A").getValue().toString());

            // stack.display();

            // System.out.println("done dequing ");
      
            // System.out.println("added Data to heap");



            while (true) {

                System.out.println("\nChoose Option with number");
                System.out.println("\nTask 2\n1.Find shortest path Between Nodes using BFS\n");
                System.out.println("9. See Output of DFS");

                System.out.println("Task3\n\n2.Insert node");
                System.out.println("3.Delete Node ");
                System.out.println("4.Find Node stats from array");
                System.out.println("5.Find Node stats with Hashing");
                System.out.println("6. Display Heap in order of Low-High risk places");
                System.out.println("7. Itenary");


                int option = scanner.nextInt();
                switch (option) {
                    case 2:
                        System.out.println("Enter Node to add");
                        node = scanner.next();
                        System.out.println("Enter Value for node " + node);
                        if (!graph.hasNode(node)) {

                            System.out.println("Enter Values for");
                            System.out.println("Temperature: ");
                            int temperature = scanner.nextInt();

                            boolean valueok=false;
                            if(temperature>=25&&temperature<=48)
                                valueok=true;

                            while(!valueok){
                            System.out.println("Enter a Valid Temp between 25-48");
                             temperature = scanner.nextInt();
                             if(temperature>=25&&temperature<=48)
                                valueok=true;

                            }

                            valueok=false;
                            System.out.println("Humidity: ");
                            int Humidity = scanner.nextInt();
                            if(Humidity>=15&&Humidity<=60)
                            valueok=true;
                            while(!valueok){
                                System.out.println("Enter a Valid Humidity between 15-60");
                                Humidity = scanner.nextInt();
                                 if(Humidity>=15&&Humidity<=60)
                                    valueok=true;
    
                                }
                                valueok=false;
                            System.out.println("WindSpeed: ");
                            int WindSpeed = scanner.nextInt();
                            if(WindSpeed>=30&&WindSpeed<=100)
                            valueok=true;
                            while(!valueok){
                                System.out.println("Enter a Valid WindSpeed between 30-100");
                                WindSpeed = scanner.nextInt();
                                 if(WindSpeed>=30&&WindSpeed<=100)
                                    valueok=true;
    
                                }
                            naturalElements value = new naturalElements(node, temperature, Humidity, WindSpeed);

                            if (graph.addNode(node)) {
                                System.out.println("Node Added Successfully ");
                                graph.getNode(node).setValue(value);
                                hashTable.put(node, value);
                                heap.add(value.riskInteger(), value);
                                elements[lineCount]=value;
                                lineCount++;
                            }
                            graph.displayAsList();
                            // hash
                        } else {
                            System.out.println("Enter a node that does not exist ");
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
                                stack = graph.shortestPathBFS(node, end);

                                while (!stack.isEmpty()) {

                                    String nodename = (stack.pop().toString());
                                    // System.out.print(nodename+"->");
                                    System.out.println(hashTable.get(nodename));
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
                        System.out.println("Deleting node " + node);
                        graph.removeNode(node);
                        hashTable.remove(node);
                        System.out.println("Deleted node: " + node);
                        try {
                            hashTable.get(node).toString();
                        } catch (Exception e) {
    
                        }
                        break;
                    case 4:
                        System.out.println("Enter the Node to Be Found  ");
                        System.out.println("Node: ");
                        node = scanner.next();
                        currentNode = null;

                        long startTime = System.nanoTime();
                        lineCount--;

                        while (lineCount != -1) {
                            if (elements[lineCount].getlabel().equals(node)) {
                                System.out.println(elements[lineCount].toString());
                                lineCount = -1;
                            } else {
                                lineCount--;
                            }
                        }
                        long endTime = System.nanoTime();
                        long elapsedTime = endTime - startTime;

                        System.out.println("Time Taken to find node using Array : " + elapsedTime);

                        break;
                    case 5:
                        System.out.println("Finding node using hash");
                        node = scanner.next();
                        startTime = System.nanoTime();

                        System.out.println("Location: " + node + " " + hashTable.get(node).toString());
                        endTime = System.nanoTime();
                        elapsedTime = endTime - startTime;
                        System.out.println("Time Taken to find node using Hash : " + elapsedTime);

                        // test code
                        break;
                    case 6:
                        System.out.println("Display heal");
                        Object[][] input = new Object[heap.getCount()][2];


                        for (int i = 0; i < heap.getCount(); i++) {
                            input[i][0] = heap.heap[i].priority;
                            input[i][1] = heap.heap[i].value;
                        }
                        DSAHeap.DSAHeapEntry[] sortesdArray = heap.heapSort(input);
                        for (DSAHeap.DSAHeapEntry entry : sortesdArray) {
                            naturalElements element = (naturalElements) entry.value;
                            System.out.println(element.toString());
                        }
                        break;

                    case 7:
                        System.out.println("Itenary ");
                    default:
                        break;
                }
            }
            // Task 3 searn inser and delte

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
