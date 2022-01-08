package pl.nieckarz.librarymanager.responses;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.nieckarz.librarymanager.book.entity.BorrowedBook;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserListResponse {

    private String email;
    private String firstName;
    private String lastName;
    private List<BorrowedBook> borrowedBooks;

}
