package C043.PowerUp.repositories;

import C043.PowerUp.entities.CustomList;
import C043.PowerUp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomListRepository
        extends JpaRepository<CustomList, Integer> {
    List<CustomList> findByUser(User user);
}
