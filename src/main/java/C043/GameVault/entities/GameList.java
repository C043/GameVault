package C043.GameVault.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "list_type")
@Getter
@Setter
public abstract class GameList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "game_id")
    private int gameId;

    @Column(name = "user_rating")
    private int userRating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
