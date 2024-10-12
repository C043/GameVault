package C043.GameVault.services;

import C043.GameVault.entities.GameList;
import C043.GameVault.entities.User;
import C043.GameVault.repositories.GameListRepository;
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
