package pl.nieckarz.librarymanager.book;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@NoArgsConstructor
@Entity
public class Book {

    @Id
    private String isbn;

    private String author;
    private String title;
    private int inStock;
    private int toBorrow =inStock;

    public Book(String isbn, String author, String title, int inStock) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.inStock = inStock;
    }
}
