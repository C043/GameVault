package C043.PowerUp.controllers;

import C043.PowerUp.entities.CustomList;
import C043.PowerUp.entities.CustomListGame;
import C043.PowerUp.entities.User;
import C043.PowerUp.payloads.CustomListDTO;
import C043.PowerUp.payloads.CustomListGameDTO;
import C043.PowerUp.payloads.RespDTO;
import C043.PowerUp.security.Validation;
import C043.PowerUp.services.CustomListGameService;
import C043.PowerUp.services.CustomListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customLists")
public class CustomListController {
    @Autowired
    private CustomListService customListService;

    @Autowired
    private Validation validation;

    @Autowired
    private CustomListGameService customListGameService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespDTO postCustomList(@AuthenticationPrincipal User user,
                                  @RequestBody @Validated CustomListDTO body,
                                  BindingResult validation) {
        this.validation.validate(validation);
        CustomList newCustomList = this.customListService.postCustomList(user
                , body);
        return new RespDTO(newCustomList.getId());
    }

    @GetMapping
    public List<CustomList> getAllCustomLists(
            @AuthenticationPrincipal User user) {
        return this.customListService.getAllCustomLists(user);
    }

    @GetMapping("/{customListId}")
    public CustomList getSingleCustomList(@PathVariable int customListId) {
        return this.customListService.getCustomList(customListId);
    }

    @GetMapping("/games/{customListId}")
    public List<CustomListGame> getAllCustomListGames(
            @PathVariable int customListId) {
        return this.customListGameService.getAllCustomListGames(customListId);
    }

    @PutMapping("/{customListId}")
    public RespDTO editTitle(@AuthenticationPrincipal User user,
                             @RequestBody @Validated CustomListDTO body,
                             @PathVariable int customListId,
                             BindingResult validation) {
        this.validation.validate(validation);
        CustomList updatedCustomList =
                this.customListService.updateCustomListTitle(user, body,
                        customListId);
        return new RespDTO(updatedCustomList.getId());
    }

    @DeleteMapping("/{customListId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomList(@AuthenticationPrincipal User user,
                                 @PathVariable int customListId) {
        this.customListService.deleteCustomList(user, customListId);
    }

    @PostMapping("/{customListId}")
    public RespDTO postCustomListGame(@AuthenticationPrincipal User user,
                                      @PathVariable int customListId,
                                      @RequestBody @Validated
                                      CustomListGameDTO body) {
        CustomListGame newGame =
                this.customListGameService.postCustomListGame(user,
                        customListId, body);
        return new RespDTO(newGame.getId());
    }

    @DeleteMapping("/{customListId}/{gameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomListGame(@AuthenticationPrincipal User user,
                                     @PathVariable int customListId,
                                     @PathVariable int gameId) {
        this.customListGameService.deleteCustomListGame(user, customListId,
                gameId);
    }
}