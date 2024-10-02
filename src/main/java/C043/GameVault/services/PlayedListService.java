package C043.GameVault.services;

import C043.GameVault.entities.PlayedList;
import C043.GameVault.entities.User;
import C043.GameVault.exceptions.BadRequestException;
import C043.GameVault.exceptions.NotFoundException;
import C043.GameVault.exceptions.UnauthorizedException;
import C043.GameVault.payloads.GameDTO;
import C043.GameVault.repositories.PlayedListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayedListService {
    @Autowired
    private PlayedListRepository playedListRepository;

    public PlayedList postPlaying(GameDTO body, User user) {
        PlayedList found = this.playedListRepository.findByGameIdAndUser(body.gameId(), user);
        if (found != null) throw new BadRequestException("Game already in list");
        PlayedList newPlaying = new PlayedList(body.gameId(), user);
        return this.playedListRepository.save(newPlaying);
    }

    public List<PlayedList> getPlayedListByUser(User user) {
        return this.playedListRepository.findAllByUser(user);
    }

    public PlayedList getPlayedListById(int id) {
        return this.playedListRepository.findById(id).orElseThrow(() -> new NotFoundException("Game not found"));
    }

    public void deletePlayedList(User user, int id) {
        PlayedList found = this.playedListRepository.findByGameIdAndUser(id, user);
        if (found == null) throw new NotFoundException("Game not found");
        if (found.getUser().getId() != user.getId()) throw new UnauthorizedException("You don't have permissions to delete this game");
        this.playedListRepository.delete(found);
    }
}
