package C043.PowerUp.repositories;

import C043.PowerUp.entities.PlayedList;
import C043.PowerUp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayedListRepository
        extends JpaRepository<PlayedList, Integer> {
    PlayedList findByGameIdAndUser(int gameId, User user);

    List<PlayedList> findAllByUser(User user);
}
