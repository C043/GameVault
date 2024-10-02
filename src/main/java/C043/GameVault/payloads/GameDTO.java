package C043.GameVault.payloads;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record GameDTO(
        @NotNull(message = "Game id is required")
        int gameId,
        @Range(min = 0, max = 10, message = "Rating must be in a range from 1 to 10")
        int userRating
) {
}
