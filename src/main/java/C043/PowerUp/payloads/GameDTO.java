package C043.PowerUp.payloads;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record GameDTO(
        @NotNull(message = "Game id is required")
        int gameId,
        @Range(min = 0, max = 5, message = "Rating must be in a range from 1 " +
                "to 5")
        int userRating
) {
}
