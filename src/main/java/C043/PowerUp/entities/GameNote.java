package C043.PowerUp.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_notes")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class GameNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    private String notes;

    private int gameId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public GameNote(String notes, int gameId, User user) {
        this.notes = notes;
        this.gameId = gameId;
        this.user = user;
    }
}
