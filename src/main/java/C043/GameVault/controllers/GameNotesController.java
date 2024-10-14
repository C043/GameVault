package C043.GameVault.controllers;

import C043.GameVault.entities.GameNote;
import C043.GameVault.entities.User;
import C043.GameVault.payloads.NotesDTO;
import C043.GameVault.payloads.RespDTO;
import C043.GameVault.services.GameNotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
public class GameNotesController {
    @Autowired
    private GameNotesService gameNotesService;

    @PostMapping
    public RespDTO postNotes(@AuthenticationPrincipal User user,
                             @RequestBody NotesDTO body) {
        GameNote newGameNote = this.gameNotesService.postGameNote(user, body);
        return new RespDTO(newGameNote.getId());
    }
}
