package C043.GameVault.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

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
        @NotEmpty(message = "password")
        @Min(value = 8)
        String password
) {
}
