package C043.PowerUp.repositories;

import C043.PowerUp.entities.GameList;
import C043.PowerUp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameListRepository extends JpaRepository<GameList, Integer> {
    List<GameList> findByUser(User user);
}
