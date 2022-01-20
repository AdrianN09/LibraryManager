package pl.nieckarz.librarymanager.models;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.nieckarz.librarymanager.exceptions.update.InvalidUpdateException;
import pl.nieckarz.librarymanager.models.entity.Book;
import pl.nieckarz.librarymanager.models.entity.BorrowedBook;
import pl.nieckarz.librarymanager.models.repositories.BookRepository;
import pl.nieckarz.librarymanager.exceptions.delete.InvalidDeleteException;
import pl.nieckarz.librarymanager.exceptions.resources.ResourceNotFoundException;
import pl.nieckarz.librarymanager.models.repositories.BorrowedBookRepository;

import javax.transaction.Transactional;
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


    public void deleteBook(String title) {
        Book book = bookRepository.findByTitle(title).orElseThrow(() -> new ResourceNotFoundException("Book", "title", title));

        if (book.getInStock() != book.getBooksAvailable()) {
            throw new InvalidDeleteException(title);
        }
        bookRepository.delete(book);
    }

    @Transactional
    public Book update(Book updatedBook) {

        Book book = bookRepository.findByIsbn(updatedBook.getIsbn())
                .orElseThrow(() -> new ResourceNotFoundException("Book", "isbn", updatedBook.getIsbn()));

        int booksAvailable = updatedBook.getInStock() - borrowedBookRepository.countAllByTitle(book.getTitle());

        if(booksAvailable<0){
            throw new InvalidUpdateException("Cannot update book: someone borrowed it");
        }

        borrowedBookRepository.findAllByTitle(book.getTitle())
                .forEach(borrowedBook -> borrowedBook.setTitle(updatedBook.getTitle()));



        book.setAuthor(updatedBook.getAuthor());
        book.setInStock(updatedBook.getInStock());
        book.setTitle(updatedBook.getTitle());
        book.setBooksAvailable(booksAvailable);

        return bookRepository.save(book);
    }
}