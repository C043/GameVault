package C043.GameVault.payloads;

import jakarta.validation.constraints.NotNull;

public record CustomListGameDTO(
        @NotNull(message = "GameId is required")
        int gameId
) {
}
