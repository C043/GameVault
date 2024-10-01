package C043.GameVault.services;

import C043.GameVault.entities.User;
import C043.GameVault.exceptions.BadRequestException;
import C043.GameVault.exceptions.NotFoundException;
import C043.GameVault.payloads.NewUserDTO;
import C043.GameVault.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        User found = this.userRepository.findByEmail(email);
        if (found == null) throw new NotFoundException("User not found");
        return found;
    }

    public User getUserByUsername(String username) {
        User found = this.userRepository.findByUsername(username);
        if (found == null) throw new NotFoundException("User not found");
        return found;
    }

    public User registerUser(NewUserDTO body) {
        User foundByEmail = this.userRepository.findByEmail(body.email());
        if (foundByEmail != null) throw new BadRequestException("User with this email already exists");
        User foundByUsername = this.userRepository.findByUsername(body.username());
        if (foundByUsername != null) throw new BadRequestException("User with this username already exists");
        User newUser = new User(body.username(), body.name(), body.surname(), body.email(), bcrypt.encode(body.password()));
        newUser.setAvatar("https://ui-avatars.com/api/?background=random&name=" + body.username());
        // To Do: settare una cover random a tema videogiochi
        return this.userRepository.save(newUser);
    }
}
