package C043.GameVault.services;

import C043.GameVault.entities.User;
import C043.GameVault.exceptions.UnauthorizedException;
import C043.GameVault.payloads.AuthDTO;
import C043.GameVault.repositories.UserRepository;
import C043.GameVault.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTTools jwtTools;

    public String checkCredentialsAndGenerateToken(AuthDTO body) {
        User found = this.userRepository.findByEmail(body.email());
        if (found.getPassword().equals(body.password())) {
            return jwtTools.createToken(found);
        } else {
            throw new UnauthorizedException("Wrong credentials");
        }
    }
}
