package C043.GameVault.controllers;

import C043.GameVault.entities.User;
import C043.GameVault.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public User getUser(@AuthenticationPrincipal User currentUser) {
        return currentUser;
    }
}
