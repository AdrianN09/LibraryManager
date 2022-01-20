package pl.nieckarz.librarymanager.models.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.nieckarz.librarymanager.models.entity.BorrowedBook;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook,Long> {

   List<BorrowedBook> findAllByAppUser_Email(String email);

   List<BorrowedBook> findAllByAppUser_EmailAndTitle(String email,String title);

   List<BorrowedBook> findAllByToReturnIsBefore(LocalDate localDate);

   void deleteBorrowedBookByIdAndAndAppUser_Email(Long id,String email);

   int countAllByTitle(String title);

   List<BorrowedBook> findAllByTitle(String title);



}
