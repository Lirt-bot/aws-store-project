package se.jensen.linus.awsstoreproject.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterDTO(
        @NotBlank(message = "Användernamn krävs")
        String username,

        @NotBlank(message = "Lössenord krävs")
        @Size(min = 6, message = "Lösenordet måste vara minst 6 tecken långt")
        String password,

        @NotBlank(message = "Email krävs")
        @Email(message = "Ogiltig email")
        String email

) {
}
