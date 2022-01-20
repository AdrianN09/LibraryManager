package pl.nieckarz.librarymanager.appuser;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.nieckarz.librarymanager.models.BorrowedBookService;
import pl.nieckarz.librarymanager.models.entity.Book;
import pl.nieckarz.librarymanager.models.repositories.BookRepository;
import pl.nieckarz.librarymanager.models.repositories.BorrowedBookRepository;
import pl.nieckarz.librarymanager.exceptions.resources.ResourceNotFoundException;
import pl.nieckarz.librarymanager.responses.UserListResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final BorrowedBookRepository borrowedBookRepository;
    private final BookRepository bookRepository;
    private final BorrowedBookService borrowedBookService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return appUserRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));

    }

    public List<UserListResponse> findAllUsers() {
        List<UserListResponse> userListResponses = new ArrayList<>();

        appUserRepository.findAllByAppUserRoleEquals(AppUserRole.ROLE_USER).forEach(user ->
                userListResponses.add(new UserListResponse(
                        user.getEmail(),
                        user.getFirstName(),
                        user.getLastName(),
                        borrowedBookRepository.findAllByAppUser_Email(user.getEmail()))));

        return userListResponses;
    }

    public String signUpUser(AppUser appUser) {

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        return "New user created.";
    }

    private List<GrantedAuthority> getAuthority(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }


    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {

        bookRepository.save(new Book("9780974192581", "Napoleon Hill", "Think and grow rich", 10));
        bookRepository.save(new Book("9780671043216", "Dale Carnegie", "How to Win Friends and Influence People", 2));

        AppUser user = new AppUser("Test firstname", "Test lastname", "user@gmail.com", bCryptPasswordEncoder.encode("password"), AppUserRole.ROLE_USER);
        AppUser admin = new AppUser("Admin ", "Admin", "admin@gmail.com", bCryptPasswordEncoder.encode("password"), AppUserRole.ROLE_ADMIN);
        appUserRepository.save(admin);
        appUserRepository.save(user);

        borrowedBookService.borrowBookByTitle("Think and grow rich", user.getEmail());

    }


}
