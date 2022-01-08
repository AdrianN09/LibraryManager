package pl.nieckarz.librarymanager.exceptions.resources;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.nieckarz.librarymanager.exceptions.resources.ResourceNotFound;
import pl.nieckarz.librarymanager.exceptions.resources.ResourceNotFoundException;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ResourceNotFoundHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {

        ResourceNotFound resourceNotFound = new ResourceNotFound(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(resourceNotFound, HttpStatus.NOT_FOUND);
    }
}
