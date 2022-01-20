package pl.nieckarz.librarymanager.exceptions.update;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.nieckarz.librarymanager.exceptions.regex.InvalidEmail;
import pl.nieckarz.librarymanager.exceptions.regex.InvalidEmailException;

import java.time.ZoneId;
import java.time.ZonedDateTime;


@ControllerAdvice
public class InvalidUpdateHandler {

    @ExceptionHandler(value = {InvalidUpdateException.class})
    public ResponseEntity<Object> handleInvalidUpdateException(InvalidUpdateException e) {

        InvalidUpdate invalidUpdate = new InvalidUpdate(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(invalidUpdate, HttpStatus.BAD_REQUEST);
    }
}
