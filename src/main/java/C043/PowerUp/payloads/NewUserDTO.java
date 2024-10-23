package C043.PowerUp.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public record NewUserDTO(
        @NotEmpty(message = "Username is required")
        String username,
        @NotEmpty(message = "Name is required")
        String name,
        @NotEmpty(message = "Surname is required")
        String surname,
        @NotEmpty(message = "Email is required")
        @Email(message = "Email is not valid")
        String email,
        @NotEmpty(message = "Password is required")
        @Length(min = 8,
                message = "Password must be at least 8 characters long")
        String password
) {
}
