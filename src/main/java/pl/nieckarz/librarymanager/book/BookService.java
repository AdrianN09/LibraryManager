package pl.nieckarz.librarymanager.book;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.nieckarz.librarymanager.book.entity.Book;
import pl.nieckarz.librarymanager.book.repositories.BookRepository;
import pl.nieckarz.librarymanager.book.repositories.BorrowedBookRepository;
import pl.nieckarz.librarymanager.payload.Response;

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


    public List<Response> timeout() {
        Response response = new Response();
        List<Response> responses = new ArrayList<>();

        borrowedBookRepository.findAllByToReturnIsBefore(LocalDate.now()).forEach(e -> {
            response.setEmail(e.getAppUser().getEmail());
            response.setTitle(e.getTitle());
            responses.add(response);

        });

        return responses;
    }
}
