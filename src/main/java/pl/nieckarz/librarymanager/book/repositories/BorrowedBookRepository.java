package pl.nieckarz.librarymanager.book.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.nieckarz.librarymanager.book.entity.Book;
import pl.nieckarz.librarymanager.book.entity.BorrowedBook;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook,Long> {

   List<BorrowedBook> findAllByAppUser_Email(String email);

   List<BorrowedBook> findAllByToReturnIsBefore(LocalDate localDate);

   void deleteBorrowedBookByTitleAndAndAppUser_Email(String title, String email);

}
