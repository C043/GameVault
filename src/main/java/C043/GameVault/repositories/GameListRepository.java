package C043.GameVault.repositories;

import C043.GameVault.entities.GameList;
import C043.GameVault.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameListRepository extends JpaRepository<GameList, Integer> {
    List<GameList> findByUser(User user);
}
