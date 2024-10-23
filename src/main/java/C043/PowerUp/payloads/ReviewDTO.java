package C043.PowerUp.payloads;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record ReviewDTO(
        @NotNull(message = "Game id is required")
        int gameId,
        @Range(min = 1, max = 5, message = "Rating must be in range from 1 to" +
                " 5")
        int rating,
        String content
) {
}
