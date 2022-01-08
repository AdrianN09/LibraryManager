package pl.nieckarz.librarymanager.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.nieckarz.librarymanager.appuser.AppUserService;
import pl.nieckarz.librarymanager.book.entity.Book;
import pl.nieckarz.librarymanager.book.BookService;
import pl.nieckarz.librarymanager.responses.BorrowDetailsResponse;
import pl.nieckarz.librarymanager.responses.UserListResponse;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private BookService bookService;
    private AppUserService appUserService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @PostMapping
    public Book addNewBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @GetMapping("/users")
    public List<UserListResponse> getAllUsers() {
        return appUserService.findAllUsers();
    }

    @GetMapping("/timeout")
    public List<BorrowDetailsResponse> timeout() {

        return bookService.timeout();
    }

    @DeleteMapping("/{title}")
    public void deleteBook(@PathVariable(name = "title") String title) {
        bookService.deleteBook(title);
    }

}
