package C043.PowerUp.repositories;

import C043.PowerUp.entities.Review;
import C043.PowerUp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Review findByUserAndGameId(User user, int gameId);

    List<Review> findByGameId(int gameId);
}
