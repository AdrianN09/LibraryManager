package pl.nieckarz.librarymanager.book;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.nieckarz.librarymanager.appuser.AppUser;
import pl.nieckarz.librarymanager.appuser.AppUserRepository;
import pl.nieckarz.librarymanager.book.entity.Book;
import pl.nieckarz.librarymanager.book.entity.BorrowedBook;
import pl.nieckarz.librarymanager.book.repositories.BookRepository;
import pl.nieckarz.librarymanager.book.repositories.BorrowedBookRepository;
import pl.nieckarz.librarymanager.exceptions.resources.ResourceNotFoundException;
import pl.nieckarz.librarymanager.responses.BorrowDetailsResponse;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BorrowedBookService {

    private final BorrowedBookRepository borrowedBookRepository;
    private final AppUserRepository appUserRepository;
    private final BookRepository bookRepository;


    public List<BorrowDetailsResponse> timeout() {
        List<BorrowDetailsResponse> responses = new ArrayList<>();

        borrowedBookRepository.findAllByToReturnIsBefore(LocalDate.now())
                .forEach(e -> responses.add(new BorrowDetailsResponse(e.getAppUser().getEmail(), e.getTitle(),e.getToReturn(),
                        (int) ChronoUnit.DAYS.between(e.getToReturn(),LocalDate.now()))));

        return responses;
    }

    public String borrowBookByTitle(String title, String email) {
        AppUser appUser = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));

        Book book = bookRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "title", title));

        if (book.getBooksAvailable() > 0) {
            BorrowedBook borrowedBook = new BorrowedBook();
            borrowedBook.setAppUser(appUser);
            borrowedBook.setTitle(title);
            borrowedBookRepository.save(borrowedBook);
            book.setBooksAvailable(book.getBooksAvailable() - 1);
            bookRepository.save(book);

            return email + " borrowed: " + title;
        } else return "out of stock";

    }

    @Transactional
    public void returnBook(String title, String email) {

        Book book = bookRepository.findByTitle(title).orElseThrow(() -> new ResourceNotFoundException("Book", "title", title));

        List<BorrowedBook> borrowedBooks = borrowedBookRepository.findAllByAppUser_EmailAndTitle(email, title);


        if (borrowedBooks.size() == 0) {
            throw new ResourceNotFoundException("borrowedBook", "title", title);

        } else {
            borrowedBookRepository.deleteBorrowedBookByIdAndAndAppUser_Email(borrowedBooks.get(0).getId(), email);
            book.setBooksAvailable(book.getBooksAvailable() + 1);
        }

        bookRepository.save(book);
    }

    public List<BorrowedBook> findUserBooks(String email) {
        return borrowedBookRepository.findAllByAppUser_Email(email);
    }
}
