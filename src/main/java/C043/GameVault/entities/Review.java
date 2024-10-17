package C043.GameVault.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(name = "game_id")
    private int gameId;

    private int rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    public Review(int gameId, int rating, User user, String content) {
        this.gameId = gameId;
        this.rating = rating;
        this.user = user;
        this.content = content;
    }
}
