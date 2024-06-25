package rw.ac.rca.springstarter.exceptions;

public class InvalidLongException extends RuntimeException{
    public InvalidLongException(String message) {
        super(message);
    }

    public InvalidLongException(String message, Throwable cause) {
        super(message, cause);
    }

}