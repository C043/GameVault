package C043.PowerUp.controllers;

import C043.PowerUp.entities.User;
import C043.PowerUp.payloads.AuthDTO;
import C043.PowerUp.payloads.NewUserDTO;
import C043.PowerUp.payloads.RespDTO;
import C043.PowerUp.payloads.TokenRespDTO;
import C043.PowerUp.security.JWTTools;
import C043.PowerUp.security.Validation;
import C043.PowerUp.services.AuthService;
import C043.PowerUp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private Validation validation;

    @Autowired
    private JWTTools jTools;

    @PostMapping("/login")
    public TokenRespDTO login(@RequestBody @Validated AuthDTO body,
                              BindingResult validation) {
        this.validation.validate(validation);
        String token = this.authService.checkCredentialsAndGenerateToken(body);
        return new TokenRespDTO(token);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RespDTO register(@RequestBody @Validated NewUserDTO body,
                            BindingResult validation) {
        this.validation.validate(validation);
        User newUser = this.userService.registerUser(body);
        return new RespDTO(newUser.getId());
    }

    @PostMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void checkToken(@RequestBody TokenRespDTO body) {
        this.jTools.verifyToken(body.token());
    }
}
