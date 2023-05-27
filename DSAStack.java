
public class DSAStack {

    private Object[] stack;
    private int count;
    private int DEFAULT_CAPACITY = 100;

    public DSAStack(int size) {
        this.stack = new Object[size];
        this.count = 0;

    }

    public DSAStack() {
        this.stack = new Object[DEFAULT_CAPACITY];
        this.count = 0;

    }

    public int getCount() {
        return this.count;
    }

    // check if the stack is empty
    public boolean isEmpty() {

        return (this.count == 0);

    }

    // Check if the tack is full

    public boolean isFull() {
        if (count == stack.length)
            return true;
        return false;

    }

    // write a code for push

    public void push(Object valObject) {
        if (!(isFull())) {

            this.stack[this.count] = valObject;
            count++;

        } else {
            throw new RuntimeException("Stack Full");

        }

    }

    public Object pop() {
        if (!(isEmpty())) {
            Object v = top();
            count -= 1;
            return v;
        } else {
            throw new RuntimeException("Stack Empty");

        }
    }

    public Object top() {
        if (!(isEmpty())) {
            return this.stack[count - 1];
        }
        throw new RuntimeException("Stack Empty");
    }

    public DSAStack reverse(DSAStack in) {
        DSAStack reversedq = new DSAStack();

        while (!in.isEmpty()) {
            reversedq.push(in.pop());
            System.out.println(reversedq.toString());


        }
        return reversedq;
    }

    public void display() {

        // int count = this.getCount();
        while (!this.isEmpty()) {
            System.out.println("|--------------------------------|");

            System.out.println(this.pop() + " Stack Count " + ((int) this.getCount() + 1) + "");

            // Just to look good
            if (this.getCount() == 0)
                System.out.println("|--------------------------------|");

        }
        System.out.println("Reached End of Stack");

    }
}
