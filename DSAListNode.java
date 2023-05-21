import java.io.Serializable;


public class DSAListNode implements  Serializable{



    private DSAGraphNode value;
    private  DSAListNode next;


public DSAListNode(DSAGraphNode valuein){

    this.value=valuein;
    this.next=null;

}
public void setvalue(DSAGraphNode valuein){
    this.value=valuein;
}

public DSAGraphNode getValue(){

    return this.value;
}

public void setNext(DSAListNode input){

    this.next=input;
}

public DSAListNode getNext(){
    return this.next;
}
    
}
