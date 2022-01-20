package pl.nieckarz.librarymanager.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.nieckarz.librarymanager.appuser.AppUserService;
import pl.nieckarz.librarymanager.models.BorrowedBookService;
import pl.nieckarz.librarymanager.models.entity.Book;
import pl.nieckarz.librarymanager.models.BookService;
import pl.nieckarz.librarymanager.responses.BorrowDetailsResponse;
import pl.nieckarz.librarymanager.responses.UserListResponse;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final BookService bookService;
    private final AppUserService appUserService;
    private final BorrowedBookService borrowedBookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @PostMapping
    public Book addNewBook(@RequestBody Book book) {
        return bookService.save(book);
    }


    @PutMapping
    public Book update(@RequestBody Book updatedBook) {
        return bookService.update(updatedBook);
    }

    @GetMapping("/users")
    public List<UserListResponse> getAllUsers() {
        return appUserService.findAllUsers();
    }

    @GetMapping("/timeout")
    public List<BorrowDetailsResponse> timeout() {
        return borrowedBookService.timeout();
    }

    @DeleteMapping("/{title}")
    public void deleteBook(@PathVariable(name = "title") String title) {
        bookService.deleteBook(title);
    }

}
