package pl.nieckarz.librarymanager.models.entity;


import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@NoArgsConstructor
@Entity
public class Book {

    @Id
    private String isbn;

    private String author;
    private String title;
    private int inStock;
    private int booksAvailable = inStock;


    public Book(String isbn, String author, String title, int inStock) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.inStock = inStock;
        this.booksAvailable = inStock;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public int getBooksAvailable() {
        return booksAvailable;
    }

    public void setBooksAvailable(int booksAvailable) {
        this.booksAvailable = booksAvailable;
    }
}
