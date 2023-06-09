public class DSAGraphEdge {
    private DSAGraphNode from;
    private DSAGraphNode to;
    private String label;
    private Double value;

    public DSAGraphEdge(DSAGraphNode fromVertex, DSAGraphNode toVertex, String inLabel, Double inValue) {
        from = fromVertex;
        to = toVertex;
        label = inLabel;
        value = inValue;
    }

    public String getLabel() {
        return label;
    }


    public Double getValue() {
        return value;
    }

    public DSAGraphNode getFrom() {
        return from;
    }

    public DSAGraphNode getTo() {
        return to;
    }

    public boolean isDirected() {
        return from != to;
    }

    @Override
    public String toString() {
        String edgeStr = "From: " + from.getLabel() + " To: " + to.getLabel();
        if (label != null) {
            edgeStr += " Label: " + label;
        }
        if (value != null) {
            edgeStr += " Value: " + value;
        }
        return edgeStr;
    }
}
