package C043.PowerUp.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("played")
@Setter
@Getter
@NoArgsConstructor
public class PlayedList extends GameList {
    public PlayedList(int gameId, int userRating, User user) {
        super(gameId, userRating, user);
    }

    public PlayedList(int gameId, User user) {
        super(gameId, user);
    }
}
