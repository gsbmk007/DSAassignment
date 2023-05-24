import java.util.Arrays;

public class DSAHashTable {
    private static final double DEFAULT_LOAD_FACTOR = 0.7;
    private static final double DEFAULT_SHRINK_FACTOR = 0.3;

    private DSAHashEntry[] hashArray;
    private int count;
    private int capacity;
    private double loadFactor;
    private double shrinkFactor;

    
    public DSAHashTable(int initialCapacity) {
        hashArray = new DSAHashEntry[initialCapacity];
        Arrays.fill(hashArray, null);
        count = 0;
        capacity = initialCapacity;
        loadFactor = DEFAULT_LOAD_FACTOR;
        shrinkFactor = DEFAULT_SHRINK_FACTOR;
    }

    public void put(String key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        if ((double) count / capacity >= loadFactor) {
            resize(capacity+1);
        }

        int hashIdx = hash(key);

        while (hashArray[hashIdx] != null && !hashArray[hashIdx].isDeleted()) {
            if (hashArray[hashIdx].getKey().equals(key)) {
                hashArray[hashIdx].setValue(value);
                return;
            }
            hashIdx = (hashIdx + 1) % capacity;
        }

        hashArray[hashIdx] = new DSAHashEntry(key, value);
        count++;
    }

    public Object get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        int hashIdx = find(key);

        if (hashIdx == -1) {
            throw new IllegalArgumentException("Key not found.");
        }

        return hashArray[hashIdx].getValue();
    }

    public void remove(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        int hashIdx = find(key);

        if (hashIdx == -1) {
            throw new IllegalArgumentException("Key not found.");
        }

        hashArray[hashIdx].setDeleted(true);
        count--;

        if ((double) count / capacity <= shrinkFactor) {
            resize(capacity / 2);
        }
    }

    public int getCount() {
        return count;
    }

    private int hash(String key) {
        int hashVal = key.hashCode() % capacity;
        if (hashVal < 0) {
            hashVal += capacity;
        }
        return hashVal;
    }

    private int find(String key) {
        int hashIdx = hash(key);

        while (hashArray[hashIdx] != null) {
            if (hashArray[hashIdx].getKey().equals(key) && !hashArray[hashIdx].isDeleted()) {
                return hashIdx;
            }
            hashIdx = (hashIdx + 1) % capacity;
        }

        return -1;
    }

    private void resize(int newCapacity) {
        DSAHashEntry[] oldArray = hashArray;
        hashArray = new DSAHashEntry[newCapacity];
        Arrays.fill(hashArray, null);
        count = 0;
        capacity = newCapacity;

        for (DSAHashEntry entry : oldArray) {
            if (entry != null && !entry.isDeleted()) {
                put(entry.getKey(), entry.getValue());
            }
        }

        System.out.println("Resizing to "+newCapacity );
    }

    static class DSAHashEntry {
        private String key;
        private Object value;
        private boolean deleted;

        public DSAHashEntry(String key, Object value) {
            this.key = key;
            this.value = value;
            this.deleted = false;
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
        
            public boolean isDeleted() {
                return deleted;
            }
        
            public void setDeleted(boolean deleted) {
                this.deleted = deleted;
            }
        }
    
        public DSAHashEntry[] getHashArray() {
            return hashArray;
        }
    }
        