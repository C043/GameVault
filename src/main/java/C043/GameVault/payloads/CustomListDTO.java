package C043.GameVault.payloads;

import jakarta.validation.constraints.NotEmpty;

public record CustomListDTO(
        @NotEmpty(message = "Title is required")
        String title
) {
}
