package pl.nieckarz.librarymanager.book;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.nieckarz.librarymanager.book.entity.Book;
import pl.nieckarz.librarymanager.book.repositories.BookRepository;
import pl.nieckarz.librarymanager.book.repositories.BorrowedBookRepository;
import pl.nieckarz.librarymanager.exceptions.resources.ResourceNotFoundException;
import pl.nieckarz.librarymanager.responses.BorrowDetailsResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BorrowedBookRepository borrowedBookRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        book.setBooksAvailable(book.getInStock());
        return bookRepository.save(book);
    }

    public List<BorrowDetailsResponse> timeout() {
        List<BorrowDetailsResponse> responses = new ArrayList<>();

        borrowedBookRepository.findAllByToReturnIsBefore(LocalDate.now())
                .forEach(e -> responses.add(new BorrowDetailsResponse(e.getAppUser().getEmail(), e.getTitle())));

        return responses;
    }

    public void deleteBook(String title) {
        Book book = bookRepository.findByTitle(title).orElseThrow(() -> new ResourceNotFoundException("Book", "title", title));

        if (book.getInStock() != book.getBooksAvailable()) {
            throw new IllegalStateException("Cant delete book: book is borrowed");
        }
        bookRepository.delete(book);
    }
}
