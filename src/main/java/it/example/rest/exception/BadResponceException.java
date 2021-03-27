package it.example.rest.exception;

public class BadResponceException extends Exception{

    public BadResponceException() {
        super();
    }
    public BadResponceException(String message, Throwable cause) {
        super(message, cause);
    }
    public BadResponceException(String message) {
        super(message);
    }
    public BadResponceException(Throwable cause) {
        super(cause);
    }

}
