import java.util.*;

public class DSAHeap {
    public class DSAHeapEntry {
        public int priority;
        public Object value;

        public DSAHeapEntry(int priority, Object value) {
            this.priority = priority;
            this.value = value;
        }

        public String toString() {
            return (priority + "," + value);
        }
    }

    public DSAHeapEntry[] heap;
    public int count;

    public DSAHeap(int maxSize) {
        heap = new DSAHeapEntry[maxSize];
        count = 0;
    }

    public void add(int priority, Object value) {
        // System.out.println("Adding to heap");
        heap[count] = new DSAHeapEntry(priority, value);
        trickleUp(count);
        count++;
    }

    public Object remove() {
        Object removedValue = heap[0].value;
        count--;

        heap[0] = heap[count];
        heap[count] = null;
        trickleDown(0);

        return removedValue;
    }

    public int getCount() {
        return count;
    }

    public DSAHeapEntry[] heapSort(Object[][] inputArray) {
        DSAHeapEntry temp;
        count = inputArray.length;
        heap = new DSAHeapEntry[count];

        if (inputArray[0][0] instanceof Integer) {
            // The input array consists of 2D arrays with priority integers and values
            for (int i = 0; i < count; i++) {
                heap[i] = new DSAHeapEntry((int) inputArray[i][0], inputArray[i][1]);
            }
        } else {
            throw new IllegalArgumentException("The input array must be an array of 2D arrays " +
                "with priority integers and object values.");
        }

        // Heapify the array
        for (int i = (count / 2) - 1; i >= 0; i--) {
            trickleDown(i);
        }

        // Perform heap sort
        for (int i = count - 1; i >= 1; i--) {
            // Swap the top entry with the i'th entry
            temp = heap[0];
            heap[0] = heap[i];
            heap[i] = temp;

            // Set the count for trickleDown()
            count = i;

            // Trickle down the top entry
            trickleDown(0);
        }

        count = inputArray.length;

        return heap;
    }

    public void trickleUp(int index) {
        int parentIndex = getParentIndex(index);
        DSAHeapEntry temp;

        if ((index > 0) && (heap[index].priority > heap[parentIndex].priority)) {
            // Swap the current index with its parent
            temp = heap[parentIndex];
            heap[parentIndex] = heap[index];
            heap[index] = temp;

            // Recurse
            trickleUp(parentIndex);
        }
    }

    public void trickleDown(int index) {
        int leftChildIndex = getLeftChildIndex(index);
        int rightChildIndex = getRightChildIndex(index);
        int largestChildIndex;
        DSAHeapEntry temp;

        if (leftChildIndex < count) {
            // Find the largest child of the current index
            largestChildIndex = leftChildIndex;
            if (rightChildIndex < count) {
                if (heap[leftChildIndex].priority < heap[rightChildIndex].priority) {
                    largestChildIndex = rightChildIndex;
                }
            }
            if (heap[largestChildIndex].priority > heap[index].priority) {
                // Swap the current index with the largest child
                temp = heap[largestChildIndex];
                heap[largestChildIndex] = heap[index];
                heap[index] = temp;

                // Recurse
                trickleDown(largestChildIndex);
            }
        }
    }

    public int getLeftChildIndex(int index) {
        return (index * 2) + 1;
    }

    public int getRightChildIndex(int index) {
        return (index * 2) + 2;
    }

    public int getParentIndex(int index) {
        return (index - 1) / 2;
    }
}
