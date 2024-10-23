package C043.PowerUp.services;

import C043.PowerUp.entities.User;
import C043.PowerUp.exceptions.UnauthorizedException;
import C043.PowerUp.payloads.AuthDTO;
import C043.PowerUp.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialsAndGenerateToken(AuthDTO body) {
        User found = this.userService.getUserByEmail(body.email());
        if (bcrypt.matches(body.password(), found.getPassword())) {
            return jwtTools.createToken(found);
        } else {
            throw new UnauthorizedException("Wrong credentials");
        }
    }
}
