package C043.GameVault.services;

import C043.GameVault.entities.GameNote;
import C043.GameVault.entities.User;
import C043.GameVault.payloads.NotesDTO;
import C043.GameVault.repositories.GameNotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameNotesService {
    @Autowired
    private GameNotesRepository gameNotesRepository;

    public GameNote postGameNote(User user, NotesDTO body) {
        return this.gameNotesRepository.save(new GameNote(body.notes(),
                body.gameId(), user));
    }

    public GameNote getGameNote(User user, int gameId) {
        return this.gameNotesRepository.findByUserAndGameId(user, gameId);
    }
}
