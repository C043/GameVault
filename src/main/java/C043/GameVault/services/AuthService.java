package C043.GameVault.services;

import C043.GameVault.entities.User;
import C043.GameVault.exceptions.UnauthorizedException;
import C043.GameVault.payloads.AuthDTO;
import C043.GameVault.security.JWTTools;
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
