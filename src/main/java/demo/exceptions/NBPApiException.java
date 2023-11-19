package demo.exceptions;

public class NBPApiException extends RuntimeException {
    public NBPApiException(String message, Throwable cause) {
        super(message, cause);
    }
}