package C043.PowerUp.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "custom_lists")
public class CustomList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public CustomList(String title, User user) {
        this.title = title;
        this.user = user;
    }
}
