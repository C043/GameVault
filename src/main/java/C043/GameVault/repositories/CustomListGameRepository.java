package C043.GameVault.repositories;

import C043.GameVault.entities.CustomListGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomListGameRepository
        extends JpaRepository<CustomListGame, Integer> {
}
