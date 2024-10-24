package C043.PowerUp.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record AuthDTO(
        @NotEmpty(message = "Email is required")
        @Email(message = "Email is not valid")
        String email,
        @NotEmpty(message = "Password is required")
        String password
) {
}
