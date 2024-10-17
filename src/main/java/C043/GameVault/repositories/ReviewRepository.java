package C043.GameVault.repositories;

import C043.GameVault.entities.Review;
import C043.GameVault.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Review findByUserAndGameId(User user, int gameId);
}
