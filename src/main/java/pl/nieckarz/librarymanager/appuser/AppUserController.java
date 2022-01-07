package pl.nieckarz.librarymanager.appuser;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.nieckarz.librarymanager.book.borrowed.BorrowedBook;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/hello")
@AllArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;


    @GetMapping
    public List<BorrowedBook> getUserDetails(Principal principal) {
        return appUserService.findUsersBooks(principal.getName());
    }

    @PostMapping("/borrow/{isbn}")
    public void borrowBook(@PathVariable(name = "isbn") String isbn, Principal principal){
        appUserService.borrowBookByIsbn(isbn,principal.getName());
    }


}
