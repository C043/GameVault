package C043.GameVault.controllers;

import C043.GameVault.entities.CustomList;
import C043.GameVault.entities.User;
import C043.GameVault.payloads.CustomListDTO;
import C043.GameVault.payloads.RespDTO;
import C043.GameVault.security.Validation;
import C043.GameVault.services.CustomListService;
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

    @GetMapping("/me")
    public List<CustomList> getAllCustomLists(
            @AuthenticationPrincipal User user) {
        return this.customListService.getAllCustomLists(user);
    }

    @PutMapping("/me/{customListId}")
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

    @DeleteMapping("/me/{customListId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomList(@AuthenticationPrincipal User user,
                                 @PathVariable int customListId) {
        this.customListService.deleteCustomList(user, customListId);
    }
}
