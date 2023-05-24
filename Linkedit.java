

import java.util.Iterator;

public class Linkedit implements Iterable {

    private DSAListNodeforQueue head;
    private DSAListNodeforQueue tail;

    public Linkedit() {
        this.head = null;
        this.tail = null;
    }

    public void insertFirst(Object newValue) throws Exception {
        DSAListNodeforQueue fNode = new DSAListNodeforQueue(newValue);

        if (isEmpty()) {
            this.head = fNode;
            this.tail = fNode;
        } else {
            fNode.setNext(head);
            head = fNode;
        }

    }

    public DSAListNodeforQueue gethead(){
        return this.head;
    }
    public DSAListNodeforQueue getTail(){
        return this.tail;
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

    public void insertLast(Object input) throws Exception {
        DSAListNodeforQueue newnode = new DSAListNodeforQueue(input);
        if (isEmpty()) {
            this.head = newnode;
            this.tail = newnode;
        } else {
            tail.setNext(newnode);
            tail = newnode;

        }
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
            DSAListNodeforQueue currentNode = head;
            while (currentNode.getNext() != tail) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(null);
            this.tail = currentNode;

        }
        return Value;

    }

    // @#####@@@@@@@@@@@@@@@@@@@@@@@@@

    public Iterator iterator() {
        // TODO Auto-generated method stub
        return new MyLinkedListIterator(this);
    }

    private class MyLinkedListIterator implements Iterator {

        private DSAListNodeforQueue iterNext;

        public MyLinkedListIterator(Linkedit theList) {

            iterNext = theList.head;
        }

        public boolean hasNext() {
            return (iterNext != null);
        }

        public DSAListNodeforQueue next() {

            DSAListNodeforQueue value;
            if (iterNext == null) {
                value = null;
            } else {
                value = iterNext;
                iterNext = iterNext.getNext();
            }
            return value;
        }

        public void remove() {
            throw new UnsupportedOperationException("Not supported");
        }
    }


    

}