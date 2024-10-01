package C043.GameVault.payloads;

import java.time.LocalDateTime;

public record ErrorDTO(
        String message,
        LocalDateTime timestamp
) {
}
