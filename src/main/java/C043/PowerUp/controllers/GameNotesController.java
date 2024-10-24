package C043.PowerUp.controllers;

import C043.PowerUp.entities.GameNote;
import C043.PowerUp.entities.User;
import C043.PowerUp.payloads.NotesDTO;
import C043.PowerUp.payloads.RespDTO;
import C043.PowerUp.services.GameNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
public class GameNotesController {
    @Autowired
    private GameNotesService gameNotesService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespDTO postNotes(@AuthenticationPrincipal User user,
                             @RequestBody NotesDTO body) {
        GameNote newGameNote = this.gameNotesService.postGameNote(user, body);
        return new RespDTO(newGameNote.getId());
    }

    @GetMapping("/{gameId}")
    public GameNote getGameNote(@AuthenticationPrincipal User user,
                                @PathVariable int gameId) {
        return this.gameNotesService.getGameNote(user, gameId);
    }

    @DeleteMapping("/{gameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNotes(@AuthenticationPrincipal User user,
                            @PathVariable int gameId) {
        this.gameNotesService.deleteGameNotes(user, gameId);
    }
}
