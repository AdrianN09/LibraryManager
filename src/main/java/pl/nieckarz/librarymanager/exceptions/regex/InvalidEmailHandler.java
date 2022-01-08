package pl.nieckarz.librarymanager.exceptions.regex;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.nieckarz.librarymanager.exceptions.resources.ResourceNotFound;
import pl.nieckarz.librarymanager.exceptions.resources.ResourceNotFoundException;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class InvalidEmailHandler {

    @ExceptionHandler(value = {InvalidEmailException.class})
    public ResponseEntity<Object> handleInvalidEmailException(InvalidEmailException e) {

        InvalidEmail invalidEmail = new InvalidEmail(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(invalidEmail, HttpStatus.BAD_REQUEST);
    }
}
