import java.io.Serializable;


public class DSAListEdge implements  Serializable{



    private DSAGraphEdge value;
    private  DSAListEdge next;


public DSAListEdge(DSAGraphEdge valuein){

    this.value=valuein;
    this.next=null;

}
public void setvalue(DSAGraphEdge valuein){
    this.value=valuein;
}

public DSAGraphEdge getValue(){

    return this.value;
}

public void setNext(DSAListEdge input){

    this.next=input;
}

public DSAListEdge getNext(){
    return this.next;
}
    
}
