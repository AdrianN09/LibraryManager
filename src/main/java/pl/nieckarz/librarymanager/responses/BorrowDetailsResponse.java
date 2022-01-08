package pl.nieckarz.librarymanager.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowDetailsResponse {

    private String email;
    private String title;

}
