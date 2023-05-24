public class DSAGraphNode {
    private String label;
    private Object value;
    private DSALinkedListNode links;
    private boolean visited;

    public DSAGraphNode(String inLabel) {
        this.label = inLabel;
        this.value = null;
        this.links = new DSALinkedListNode();
        this.visited = false;
    }

    public DSAGraphNode(String inLabel, Object inValue) {
        this.label = inLabel;
        this.value = inValue;
        this.links = new DSALinkedListNode();
        this.visited = false;
    }

    public String getLabel() {
        return this.label;
    }

    public void setValue(Object input) {

        this.value = input;
        // System.out.println("Done");
        // System.out.println((String)value);
        
    }

    public Object getValue() {
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
