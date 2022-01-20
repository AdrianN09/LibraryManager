package pl.nieckarz.librarymanager.exceptions.update;

public class InvalidUpdateException extends RuntimeException{

    public InvalidUpdateException(String message) {
        super(message);
    }
}
