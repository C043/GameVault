package C043.GameVault.services;

import C043.GameVault.entities.CustomList;
import C043.GameVault.entities.User;
import C043.GameVault.exceptions.NotFoundException;
import C043.GameVault.exceptions.UnauthorizedException;
import C043.GameVault.payloads.CustomListDTO;
import C043.GameVault.repositories.CustomListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomListService {
    @Autowired
    private CustomListRepository customListRepository;

    public CustomList postCustomList(User user, CustomListDTO body) {
        return this.customListRepository.save(new CustomList(body.title(),
                user));
    }

    public List<CustomList> getAllCustomLists(User user) {
        return this.customListRepository.findByUser(user);
    }

    public CustomList getCustomList(int id) {
        return this.customListRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Custom list not found"));
    }

    public CustomList updateCustomListTitle(User user, CustomListDTO body,
                                            int id) {
        CustomList found = this.getCustomList(id);
        if (found.getUser().getId() != user.getId())
            throw new UnauthorizedException("Not authorized to edit this list");
        found.setTitle(body.title());
        return this.customListRepository.save(found);
    }
}
