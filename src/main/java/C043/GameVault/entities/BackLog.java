package C043.GameVault.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("backlog")
@Getter
@Setter
@NoArgsConstructor
public class BackLog extends GameList {
    public BackLog(int gameId, User user) {
        this.gameId = gameId;
        this.user = user;
    }
}
