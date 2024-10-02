package C043.GameVault.repositories;

import C043.GameVault.entities.BackLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackLogRepository extends JpaRepository<BackLog, Integer> {
    BackLog findByGameId(int gameId);
}
