public class DSAGraphNode {
    private String label;
    private naturalElements value;
    private DSALinkedListNode links;
    private boolean visited;
    private DSAGraphNode previous;
    private int distance;

    public DSAGraphNode(String inLabel) {
        this.label = inLabel;
        this.value = new naturalElements();
        this.links = new DSALinkedListNode();
        this.visited = false;
        this.distance = 0;
    }

    public DSAGraphNode(String inLabel, naturalElements inValue) {
        this.label = inLabel;
        this.value = inValue;
        this.links = new DSALinkedListNode();
        this.visited = false;
        this.distance = 0;

    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getLabel() {
        return this.label;
    }

    public void setValue(naturalElements input) {

        this.value = input;
        // System.out.println("Done");
        // System.out.println((String)value);

    }

    public void setPrevious(DSAGraphNode a) {

        this.previous = a;
    }

    public DSAGraphNode getPrevious() {

        return this.previous;
    }

    public naturalElements getValue() {
        return this.value;
    }

    public DSALinkedListNode getAdjacent() {
        return this.links;
    }

    public void addEdge(DSAGraphNode vertex) throws Exception {
        this.links.insertLast(vertex);
    }

    public void setVisited() {
        this.visited = true;
    }

    public void clearVisited() {
        this.visited = false;
    }

    public boolean getVisited() {
        return this.visited;
    }

    public String toString() {
        return this.label;
    }
}
