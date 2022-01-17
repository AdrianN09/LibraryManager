package pl.nieckarz.librarymanager.exceptions.delete;

public class InvalidDeleteException  extends RuntimeException  {

    public InvalidDeleteException(String message) {
        super(message + " is borrowed: Cant delete this book");
    }
}
