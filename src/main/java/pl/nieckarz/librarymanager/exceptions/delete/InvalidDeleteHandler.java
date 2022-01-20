package pl.nieckarz.librarymanager.exceptions.delete;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class InvalidDeleteHandler {

    @ExceptionHandler(value = {InvalidDeleteException.class})
    public ResponseEntity<Object> handleInvalidDeleteException(InvalidDeleteException request) {

        InvalidDelete invalidDelete = new InvalidDelete(
                request.getMessage(),
                HttpStatus.FORBIDDEN,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(invalidDelete, HttpStatus.FORBIDDEN);
    }
}
