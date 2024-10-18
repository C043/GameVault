package C043.GameVault.services;

import C043.GameVault.entities.CustomList;
import C043.GameVault.entities.CustomListGame;
import C043.GameVault.entities.User;
import C043.GameVault.exceptions.UnauthorizedException;
import C043.GameVault.payloads.CustomListGameDTO;
import C043.GameVault.repositories.CustomListGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomListGameService {
    @Autowired
    private CustomListGameRepository customListGameRepository;

    @Autowired
    private CustomListService customListService;

    public CustomListGame postCustomListGame(User user, int id,
                                             CustomListGameDTO body) {
        CustomList found = this.customListService.getCustomList(id);
        if (found.getUser().getId() != user.getId())
            throw new UnauthorizedException(
                    "Unauthorized to add game to this list");
        CustomListGame newGame = new CustomListGame(found, body.gameId());
        return this.customListGameRepository.save(newGame);
    }
}
