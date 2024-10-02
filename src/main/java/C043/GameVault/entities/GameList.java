package C043.GameVault.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "list_type")
@Getter
@Setter
@NoArgsConstructor
public abstract class GameList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    protected int id;

    @Column(name = "game_id")
    protected int gameId;

    @Column(name = "user_rating")
    protected int userRating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    protected User user;

    public GameList(int gameId, int userRating, User user) {
        this.gameId = gameId;
        this.userRating = userRating;
        this.user = user;
    }

    public GameList(int gameId, User user) {
        this.gameId = gameId;
        this.user = user;
    }
}
