package pl.nieckarz.librarymanager.book;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.findAllBooks();
    }

    @PostMapping
    public Book addNewBook(@RequestBody Book book){
        return bookService.save(book);
    }

}
