package pl.nieckarz.librarymanager.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.nieckarz.librarymanager.appuser.AppUserService;
import pl.nieckarz.librarymanager.book.entity.Book;
import pl.nieckarz.librarymanager.book.BookService;
import pl.nieckarz.librarymanager.book.entity.BorrowedBook;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class UserController {

    private BookService bookService;
    private AppUserService appUserService;

    @GetMapping
    public List<BorrowedBook> getUserDetails(Principal principal) {
        return appUserService.findUserBooks(principal.getName());
    }

    @GetMapping("/borrow")
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @PostMapping("/borrow/{title}")
    public String borrowBook(@PathVariable(name = "title") String title, Principal principal) {
        return appUserService.borrowBookByTitle(title, principal.getName());
    }

    @DeleteMapping("/return/{title}")
    public void returnBook(@PathVariable(name = "title") String title, Principal principal){
         appUserService.returnBook(title, principal.getName());
    }
}
