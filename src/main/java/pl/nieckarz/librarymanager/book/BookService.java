package pl.nieckarz.librarymanager.book;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.nieckarz.librarymanager.book.entity.Book;
import pl.nieckarz.librarymanager.book.entity.BorrowedBook;
import pl.nieckarz.librarymanager.book.repositories.BookRepository;
import pl.nieckarz.librarymanager.book.repositories.BorrowedBookRepository;

import java.time.LocalDate;
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


    //TODO: fix json bug
    public List<BorrowedBook> timeout() {
        return borrowedBookRepository.findAllByToReturnIsBefore(LocalDate.now());
    }
}
