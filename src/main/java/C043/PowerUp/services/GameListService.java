package C043.PowerUp.services;

import C043.PowerUp.entities.GameList;
import C043.PowerUp.entities.User;
import C043.PowerUp.repositories.GameListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameListService {
    @Autowired
    private GameListRepository gameListRepository;

    public List<GameList> getAllGames(User user) {
        return this.gameListRepository.findByUser(user);
    }
}
