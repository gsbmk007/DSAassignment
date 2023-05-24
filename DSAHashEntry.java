public class DSAHashEntry {
    private String key;
    private Object value;
    private int state;

    public DSAHashEntry(String key, Object value, int state) {
        this.key = key;
        this.value = value;
        this.state = state;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
