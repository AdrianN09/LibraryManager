package pl.nieckarz.librarymanager.exceptions.regex;


public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException(String s) {
        super(s + " is not valid");
    }
}
