package C043.GameVault.services;

import C043.GameVault.entities.CustomList;
import C043.GameVault.entities.User;
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
}
