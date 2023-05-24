

import java.io.Serializable;


public class DSAListNodeforQueue implements  Serializable{



    private Object value;
    private  DSAListNodeforQueue next;


public DSAListNodeforQueue(Object valuein){

    this.value=valuein;
    this.next=null;

}
public void setvalue(Object valuein){
    this.value=valuein;
}

public Object getValue(){

    return this.value;
}

public void setNext(DSAListNodeforQueue input){

    this.next=input;
}

public DSAListNodeforQueue getNext(){
    return this.next;
}
    
}
