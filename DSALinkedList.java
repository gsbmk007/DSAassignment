import java.io.Serializable;
import java.util.Iterator;

public class DSALinkedList implements Serializable, Iterable<DSAGraphNode> {

    private DSAListNode head;
    private DSAListNode tail;
    private int count;

    public DSALinkedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    public DSAListNode gethead(){
        return this.head;
    }

    public void insertFirst(DSAGraphNode newValue) throws Exception {
        DSAListNode fNode = new DSAListNode(newValue);

        if (isEmpty()) {
            this.head = fNode;
            this.tail = fNode;
        } else {
            fNode.setNext(head);
            head = fNode;
        }

        this.count++;
    }

    public boolean isEmpty() {
        if (this.head == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull() throws Exception {
        return false;
    }

    public void insertLast(DSAGraphNode input) throws Exception {
        DSAListNode newnode = new DSAListNode(input);
        if (isEmpty()) {
            this.head = newnode;
            this.tail = newnode;
        } else {
            tail.setNext(newnode);
            tail = newnode;
        }

        this.count++;
    }

    public Object peekFirst() throws Exception {
        if (!isEmpty()) {
            return this.head.getValue();
        } else {
            throw new IllegalStateException("List Empty");
        }
    }

    public Object peekLast() throws Exception {
        if (isEmpty()) {
            throw new IllegalStateException("List Empty");
        } else {
            return this.tail.getValue();
        }
    }

    public Object removeFirst() throws Exception {
        if (isEmpty()) {
            throw new IllegalStateException("List Empty");
        } else {
            Object Value = this.head.getValue();
            this.head = this.head.getNext();

            this.count--;

            return Value;
        }
    }

    public Object removeLast() throws Exception {
        Object Value = this.tail.getValue();

        if (isEmpty()) {
            throw new IllegalStateException("List Empty");
        } else if (this.head == this.tail) {
            this.head = null;
            this.tail = null;
        } else {
            DSAListNode currentNode = head;
            while (currentNode.getNext() != tail) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(null);
            this.tail = currentNode;
        }

        this.count--;

        return Value;
    }

    public int getCount() {
        return this.count;
    }

    @Override
    public Iterator<DSAGraphNode> iterator() {
        return new DSALinkedListIterator(this.head);
    }

    private class DSALinkedListIterator implements Iterator<DSAGraphNode> {

        private DSAListNode current;

        public DSALinkedListIterator(DSAListNode head) {
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public DSAGraphNode next() {
            if (!hasNext()) {
                throw new IllegalStateException("List Empty");
            } else {
                DSAGraphNode value = current.getValue();
                current = current.getNext();
                return value;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
