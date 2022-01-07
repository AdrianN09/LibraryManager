package pl.nieckarz.librarymanager.book.borrowed;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook,Long> {

   List<BorrowedBook> findAllByAppUser_Email(String email);
}
