package C043.GameVault.repositories;

import C043.GameVault.entities.PlayedList;
import C043.GameVault.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayedListRepository extends JpaRepository<PlayedList, Integer> {
    PlayedList findByGameIdAndUser(int gameId, User user);

    List<PlayedList> findAllByUser(User user);
}
