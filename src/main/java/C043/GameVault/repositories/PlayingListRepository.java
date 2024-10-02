package C043.GameVault.repositories;

import C043.GameVault.entities.PlayingList;
import C043.GameVault.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayingListRepository extends JpaRepository<PlayingList, Integer> {
    PlayingList findByGameIdAndUser(int gameId, User user);

    List<PlayingList> findAllByUser(User user);
}
