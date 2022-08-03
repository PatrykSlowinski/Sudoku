package model;

public class NumberOutOfRangeException extends RuntimeException {
    public NumberOutOfRangeException() {
    }
    public NumberOutOfRangeException(String msg) {
        super(msg);
    }

}
