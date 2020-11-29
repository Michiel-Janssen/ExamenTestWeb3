package ui.controller;

public class NotAuthorizedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotAuthorizedException() {
        super();
    }

    public NotAuthorizedException(String message) {
        super(message);
    }

    public NotAuthorizedException(Throwable exception) {
        super(exception);
    }

    public NotAuthorizedException(String message, Throwable exception) {
        super(message, exception);
    }
}