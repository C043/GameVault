package C043.GameVault.controllers;

import C043.GameVault.payloads.AuthDTO;
import C043.GameVault.payloads.TokenRespDTO;
import C043.GameVault.services.AuthService;
import C043.GameVault.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/login")
    public TokenRespDTO login(@RequestBody AuthDTO body) {
        String token = this.authService.checkCredentialsAndGenerateToken(body);
        return new TokenRespDTO(token);
    }

}
