package C043.PowerUp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "customList", cascade = CascadeType.REMOVE)
    private List<CustomListGame> customListGameList = new ArrayList<>();

    public CustomList(String title, User user) {
        this.title = title;
        this.user = user;
    }
}
