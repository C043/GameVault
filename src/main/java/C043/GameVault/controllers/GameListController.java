package C043.GameVault.controllers;

import C043.GameVault.entities.BackLog;
import C043.GameVault.entities.User;
import C043.GameVault.payloads.GameDTO;
import C043.GameVault.payloads.RespDTO;
import C043.GameVault.security.Validation;
import C043.GameVault.services.BackLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lists")
public class GameListController {

    @Autowired
    private BackLogService backLogService;

    @Autowired
    private Validation validation;

    @PostMapping("/backlog")
    @ResponseStatus(HttpStatus.CREATED)
    public RespDTO postListEntry(@AuthenticationPrincipal User user, @RequestBody @Validated GameDTO body, BindingResult validation) {
        this.validation.validate(validation);
        BackLog newBacklog = this.backLogService.postBackLog(body, user);
        return new RespDTO(newBacklog.getId());
    }
}
