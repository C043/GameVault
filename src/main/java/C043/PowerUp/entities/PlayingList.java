package C043.PowerUp.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("playing")
@NoArgsConstructor
@Getter
@Setter
public class PlayingList extends GameList {
    public PlayingList(int gameId, int userRating, User user) {
        super(gameId, userRating, user);
    }

    public PlayingList(int gameId, User user) {
        super(gameId, user);
    }
}
