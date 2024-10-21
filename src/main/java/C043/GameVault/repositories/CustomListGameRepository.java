package C043.GameVault.repositories;

import C043.GameVault.entities.CustomList;
import C043.GameVault.entities.CustomListGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomListGameRepository
        extends JpaRepository<CustomListGame, Integer> {
    CustomListGame findByCustomListAndGameId(CustomList customList, int gameId);

    List<CustomListGame> findByCustomList(CustomList customList);
}
