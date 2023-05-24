


public abstract class DSAQueue {

    Linkedit list;
    Linkedit head;
    Linkedit tail;

    public DSAQueue() {
        this.list = new Linkedit();
        // this.head = list.gethead();
        // this.tail = list.getTail();

    }

    // public DSAQueue(){

    // this.queue=new Object[DEFAULT_VALUE];
    // this.size=DEFAULT_VALUE;
    // }

    public abstract boolean isEmpty();

    public DSAListNodeforQueue gethead() throws Exception{
        return (DSAListNodeforQueue) this.list.peekFirst();
    };

    public DSAListNodeforQueue getTail() throws Exception {
        return (DSAListNodeforQueue) this.list.peekLast();


    };

    public abstract void enqueue(Object val) throws Exception;

    public abstract Object dequeue() throws Exception;

    public abstract Object peek();

    public abstract void display();

}
