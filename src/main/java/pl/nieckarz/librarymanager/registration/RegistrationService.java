package pl.nieckarz.librarymanager.registration;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.nieckarz.librarymanager.appuser.AppUser;
import pl.nieckarz.librarymanager.appuser.AppUserRole;
import pl.nieckarz.librarymanager.appuser.AppUserService;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {

        emailValidator.test(request.getEmail());

        return appUserService.signUpUser(new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.ROLE_USER
                )
        );
    }
}
