package pl.nieckarz.librarymanager.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.nieckarz.librarymanager.models.entity.Book;

import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book,String> {

    Optional<Book> findByTitle(String title);

    Optional<Book> findByIsbn(String isbn);


}
