package pl.nieckarz.librarymanager.registration;

import lombok.*;
import pl.nieckarz.librarymanager.appuser.AppUserRole;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;


}
