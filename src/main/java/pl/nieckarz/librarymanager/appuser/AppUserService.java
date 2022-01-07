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
import org.springframework.transaction.annotation.Transactional;
import pl.nieckarz.librarymanager.book.entity.Book;
import pl.nieckarz.librarymanager.book.entity.BorrowedBook;
import pl.nieckarz.librarymanager.book.repositories.BookRepository;
import pl.nieckarz.librarymanager.book.repositories.BorrowedBookRepository;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final BorrowedBookRepository borrowedBookRepository;
    private final BookRepository bookRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return appUserRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("User not found"));
    }

    public List<BorrowedBook> findUserBooks(String email){
        return  borrowedBookRepository.findAllByAppUser_Email(email);
    }

    public Iterable<AppUser> findAllUsers() {
        return appUserRepository.findAllByAppUserRoleEquals(AppUserRole.ROLE_USER);
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

    public String borrowBookByTitle(String title, String email) {
        AppUser appUser = appUserRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("User not found"));

        Book book = bookRepository.findByTitle(title).orElseThrow(()-> new IllegalStateException("Book not found"));

        if(book.getBooksAvailable()>0){
            BorrowedBook borrowedBook = new BorrowedBook();
            borrowedBook.setAppUser(appUser);
            borrowedBook.setTitle(title);
            borrowedBookRepository.save(borrowedBook);
            book.setBooksAvailable(book.getBooksAvailable()-1);
            bookRepository.save(book);

            return email + " borrowed: " + title;
        }
        else return "out of stock";

    }

    @Transactional
    public void returnBook(String title, String email) {
        Book book = bookRepository.findByTitle(title).orElseThrow(()-> new IllegalStateException("Book not found"));

        borrowedBookRepository.deleteBorrowedBookByTitleAndAndAppUser_Email(title,email);
        book.setBooksAvailable(book.getBooksAvailable()+1);
        bookRepository.save(book);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void fillUsers(){

        bookRepository.save(new Book("9780974192581", "Napoleon Hill", "Think and grow rich", 10));
        bookRepository.save(new Book("9780671043216", "Dale Carnegie", "How to Win Friends and Influence People", 2));

        AppUser user = new AppUser("Test firstname","Test lastname","user@gmail.com",bCryptPasswordEncoder.encode("password"), AppUserRole.ROLE_USER );
        AppUser admin = new  AppUser("Admin ","Admin","admin@gmail.com",bCryptPasswordEncoder.encode("password"),AppUserRole.ROLE_ADMIN );
        appUserRepository.save(admin);
        appUserRepository.save(user);

        borrowBookByTitle ("Think and grow rich",user.getEmail());

    }

}
