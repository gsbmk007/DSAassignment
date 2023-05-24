

public class DSAShufflingQueue extends DSAQueue {

   

    public DSAShufflingQueue(){
        super();
    }

    public boolean isEmpty(){

        if(super.head==null){

            return true;

        }
        return false;
    }


    

   

    // public Object peek(){
    //     return this.head.getValue();

    // }
    
    public void enqueue(Object value) throws Exception{

        super.list.insertLast(value);
       


    }
    public Object dequeue() throws Exception{




      return super.list.removeFirst();


    }

    @Override
    public void display() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }

    @Override
    public Object peek() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'peek'");
    }
    

    // public void display(){

    //     for(int i=0;i<super.size;i++){


    //         System.out.println("Shuffle Data: "+(dequeue()));
    //     }
    // }

  
    
    
}
