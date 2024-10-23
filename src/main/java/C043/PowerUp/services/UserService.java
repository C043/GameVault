package C043.PowerUp.services;

import C043.PowerUp.entities.User;
import C043.PowerUp.exceptions.BadRequestException;
import C043.PowerUp.exceptions.NotFoundException;
import C043.PowerUp.payloads.NewUserDTO;
import C043.PowerUp.repositories.UserRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private Cloudinary cloudinary;

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

    public User findById(int id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User registerUser(NewUserDTO body) {
        User foundByEmail = this.userRepository.findByEmail(body.email());
        if (foundByEmail != null) throw new BadRequestException(
                "User with this email already exists");
        User foundByUsername =
                this.userRepository.findByUsername(body.username());
        if (foundByUsername != null) throw new BadRequestException(
                "User with this username already exists");
        User newUser = new User(body.username(), body.name(), body.surname(),
                body.email(), bcrypt.encode(body.password()));
        newUser.setAvatar(
                "https://ui-avatars.com/api/?background=random&name=" +
                        body.username());
        // To Do: settare una cover random a tema videogiochi
        return this.userRepository.save(newUser);
    }

    public void uploadImage(MultipartFile file, User currentUser)
            throws IOException {
        String url = (String) cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.emptyMap()).get("url");
        currentUser.setAvatar(url);
        this.userRepository.save(currentUser);
    }
}
