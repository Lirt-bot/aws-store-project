package se.jensen.linus.awsstoreproject.DTO;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "Användarnamn krävs")
        String username,

        @NotBlank(message = "Lösenord krävs")
        String password

) {
}
