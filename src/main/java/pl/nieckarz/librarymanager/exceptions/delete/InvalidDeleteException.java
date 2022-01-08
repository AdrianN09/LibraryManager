package pl.nieckarz.librarymanager.exceptions.delete;

public class InvalidDeleteException  extends RuntimeException  {

    public InvalidDeleteException(String s) {
        super(s+ " is borrowed: Cant delete this book");
    }
}
