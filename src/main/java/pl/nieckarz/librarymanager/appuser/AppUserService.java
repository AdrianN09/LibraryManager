package pl.nieckarz.librarymanager.appuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.nieckarz.librarymanager.book.Book;
import pl.nieckarz.librarymanager.book.BookRepository;
import pl.nieckarz.librarymanager.book.borrowed.BorrowedBook;
import pl.nieckarz.librarymanager.book.borrowed.BorrowedBookRepository;
import pl.nieckarz.librarymanager.security.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final BorrowedBookRepository borrowedBookRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("User not found"));
    }

    public List<BorrowedBook> findUsersBooks(String email){
        return  borrowedBookRepository.findAllByAppUser_Email(email);
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

    public void borrowBookByIsbn(String isbn, String email) {
        AppUser appUser = appUserRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("Book not found"));

        BorrowedBook borrowedBook = new BorrowedBook();
        borrowedBook.setAppUser(appUser);
        borrowedBook.setIsbn(isbn);

        borrowedBookRepository.save(borrowedBook);

    }
}
