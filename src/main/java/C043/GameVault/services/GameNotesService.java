package C043.GameVault.services;

import C043.GameVault.entities.GameNote;
import C043.GameVault.entities.User;
import C043.GameVault.exceptions.NotFoundException;
import C043.GameVault.payloads.NotesDTO;
import C043.GameVault.repositories.GameNotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameNotesService {
    @Autowired
    private GameNotesRepository gameNotesRepository;

    public GameNote postGameNote(User user, NotesDTO body) {
        GameNote found = this.gameNotesRepository.findByUserAndGameId(user,
                body.gameId());
        if (found != null) return this.editGameNote(user, body.gameId(), body);
        return this.gameNotesRepository.save(new GameNote(body.notes(),
                body.gameId(), user));
    }

    public GameNote getGameNote(User user, int gameId) {
        GameNote found =
                this.gameNotesRepository.findByUserAndGameId(user, gameId);
        if (found == null) throw new NotFoundException("GameNotes not found");
        return found;
    }

    public GameNote editGameNote(User user, int gameId, NotesDTO body) {
        GameNote found = this.getGameNote(user, gameId);
        found.setNotes(body.notes());
        return this.gameNotesRepository.save(found);
    }

    public void deleteGameNotes(User user, int gameId) {
        GameNote found = this.getGameNote(user, gameId);
        this.gameNotesRepository.delete(found);
    }
}
