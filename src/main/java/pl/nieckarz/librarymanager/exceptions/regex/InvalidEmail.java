package pl.nieckarz.librarymanager.exceptions.regex;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;


@AllArgsConstructor
@Getter
public class InvalidEmail {

    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

}
