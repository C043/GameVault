package C043.GameVault.payloads;

import org.hibernate.validator.constraints.Range;

public record RatingDTO(
        @Range(min = 0, max = 5, message = "Rating must be in range of 1 to 5")
        int rating
) {
}
