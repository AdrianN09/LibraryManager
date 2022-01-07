package pl.nieckarz.librarymanager.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.nieckarz.librarymanager.appuser.AppUser;
import pl.nieckarz.librarymanager.appuser.AppUserService;
import pl.nieckarz.librarymanager.book.entity.Book;
import pl.nieckarz.librarymanager.book.BookService;
import pl.nieckarz.librarymanager.book.entity.BorrowedBook;
import pl.nieckarz.librarymanager.payload.Response;

import java.security.Principal;
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
    public Iterable<AppUser> getAllUsers(){
        return appUserService.findAllUsers();
    }

    
    @GetMapping("/timeout")
    public List<Response> timeout(){

        return bookService.timeout();
    }
}
