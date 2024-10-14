package C043.GameVault.repositories;

import C043.GameVault.entities.GameNote;
import C043.GameVault.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameNotesRepository extends JpaRepository<GameNote, Integer> {
    GameNote findByUserAndGameId(User user, Integer gameId);
}
