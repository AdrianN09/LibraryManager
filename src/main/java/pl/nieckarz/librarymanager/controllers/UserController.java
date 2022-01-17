package pl.nieckarz.librarymanager.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.nieckarz.librarymanager.appuser.AppUserService;
import pl.nieckarz.librarymanager.book.BorrowedBookService;
import pl.nieckarz.librarymanager.book.entity.Book;
import pl.nieckarz.librarymanager.book.BookService;
import pl.nieckarz.librarymanager.book.entity.BorrowedBook;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class UserController {

    private final BookService bookService;
    private final BorrowedBookService borrowedBookService;

    @GetMapping
    public List<BorrowedBook> getUserDetails(Principal principal) {
        return borrowedBookService.findUserBooks(principal.getName());
    }

    @GetMapping("/borrow")
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @PostMapping("/borrow/{title}")
    public String borrowBook(@PathVariable(name = "title") String title, Principal principal) {
        return borrowedBookService.borrowBookByTitle(title, principal.getName());
    }

    @DeleteMapping("/return/{title}")
    public void returnBook(@PathVariable(name = "title") String title, Principal principal) {
        borrowedBookService.returnBook(title, principal.getName());
    }
}
