package C043.GameVault.controllers;

import C043.GameVault.entities.User;
import C043.GameVault.payloads.AuthDTO;
import C043.GameVault.payloads.NewUserDTO;
import C043.GameVault.payloads.RespDTO;
import C043.GameVault.payloads.TokenRespDTO;
import C043.GameVault.security.Validation;
import C043.GameVault.services.AuthService;
import C043.GameVault.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private Validation validation;

    @PostMapping("/login")
    public TokenRespDTO login(@RequestBody AuthDTO body) {
        String token = this.authService.checkCredentialsAndGenerateToken(body);
        return new TokenRespDTO(token);
    }

    @PostMapping("/register")
    public RespDTO register(@RequestBody @Validated NewUserDTO body, BindingResult validation) {
        this.validation.validate(validation);
        User newUser = this.userService.registerUser(body);
        return new RespDTO(newUser.getId());
    }
}
