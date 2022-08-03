package model;

public class EmptyCellException extends RuntimeException{
    public EmptyCellException(){
    }
    public EmptyCellException(String msg){
        super(msg);
    }


}
