package C043.PowerUp.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "custom_list_games")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CustomListGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    @ManyToOne
    @JoinColumn(name = "custom_list")
    private CustomList customList;

    @Column(name = "game_id")
    private int gameId;

    public CustomListGame(CustomList customList, int gameId) {
        this.customList = customList;
        this.gameId = gameId;
    }
}
