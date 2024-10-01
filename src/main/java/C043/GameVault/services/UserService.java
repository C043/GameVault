package C043.GameVault.services;

import C043.GameVault.entities.User;
import C043.GameVault.exceptions.BadRequestException;
import C043.GameVault.payloads.NewUserDTO;
import C043.GameVault.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User registerUser(NewUserDTO body) {
        User foundByEmail = this.userRepository.findByEmail(body.email());
        if (foundByEmail != null) throw new BadRequestException("User with this email already exists");
        User foundByUsername = this.userRepository.findByUsername(body.username());
        if (foundByUsername != null) throw new BadRequestException("User with this username already exists");
        User newUser = new User(body.username(), body.name(), body.surname(), body.email(), body.password());
        newUser.setAvatar("https://ui-avatars.com/api/?background=random&name=" + body.username());
        // To Do: settare una cover random a tema videogiochi
        return this.userRepository.save(newUser);
    }
}
