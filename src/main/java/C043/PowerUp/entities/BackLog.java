package C043.PowerUp.entities;

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
    public BackLog(int gameId, int userRating, User user) {
        super(gameId, userRating, user);
    }

    public BackLog(int gameId, User user) {
        super(gameId, user);
    }
}
