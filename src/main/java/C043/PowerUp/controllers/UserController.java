package C043.PowerUp.controllers;

import C043.PowerUp.entities.User;
import C043.PowerUp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public User getUser(@AuthenticationPrincipal User currentUser) {
        return currentUser;
    }

    @PostMapping("/me/avatar")
    public void uploadAvatar(@AuthenticationPrincipal User currentUser,
                             @RequestParam("avatar") MultipartFile img)
            throws IOException {
        this.userService.uploadImage(img, currentUser);
    }
}
