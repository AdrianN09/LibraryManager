package pl.nieckarz.librarymanager.responses;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowDetailsResponse {

    private String email;
    private String title;
    private LocalDate date;
    private int days;

}
