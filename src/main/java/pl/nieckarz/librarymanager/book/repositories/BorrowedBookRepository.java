package pl.nieckarz.librarymanager.book.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.nieckarz.librarymanager.book.entity.Book;
import pl.nieckarz.librarymanager.book.entity.BorrowedBook;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook,Long> {

   List<BorrowedBook> findAllByAppUser_Email(String email);

   List<BorrowedBook> findAllByAppUser_EmailAndTitle(String email,String title);

   List<BorrowedBook> findAllByToReturnIsBefore(LocalDate localDate);

   void deleteBorrowedBookByIdAndAndAppUser_Email(Long id,String email);



}
