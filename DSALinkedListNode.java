import java.io.Serializable;
import java.util.Iterator;

public class DSALinkedListNode implements Serializable, Iterable<DSAGraphNode> {

    private DSAListNode head;
    private DSAListNode tail;
    private int count;

    public DSALinkedListNode() {
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

    public void removeNode(DSAGraphNode value) throws Exception {
        if (isEmpty()) {
            throw new IllegalStateException("List Empty");
        }
    
        if (head.getValue().equals(value)) {
            head = head.getNext();
            count--;
            return;
        }
    
        DSAListNode currentNode = head;
        DSAListNode prevNode = null;
    
        while (currentNode != null && !currentNode.getValue().equals(value)) {
            prevNode = currentNode;
            currentNode = currentNode.getNext();
        }
    
        if (currentNode == null) {
            throw new IllegalArgumentException("Node not found");
        }
    
        prevNode.setNext(currentNode.getNext());
    
        if (currentNode.getNext() == null) {
            tail = prevNode;
        }
    
        count--;
    }
 
    public int getCount() {
        return this.count;
    }

    public Iterator iterator() {
        return new DSALinkedListNodeIterator(this);

}
    private class DSALinkedListNodeIterator implements Iterator{
        private DSAListNode iterNext;
        public DSALinkedListNodeIterator(DSALinkedListNode theList){
            iterNext=theList.head;
        }

        public boolean hasNext() { return (iterNext != null); }

        public Object next() {
            Object value;
            if(iterNext==null){
                value=null;
            }else{
value=iterNext.getValue();
iterNext=iterNext.getNext();


            }
            return value;
        }
   

    } 
}
