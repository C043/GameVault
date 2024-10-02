package C043.GameVault.services;

import C043.GameVault.entities.PlayedList;
import C043.GameVault.entities.User;
import C043.GameVault.exceptions.BadRequestException;
import C043.GameVault.exceptions.NotFoundException;
import C043.GameVault.exceptions.UnauthorizedException;
import C043.GameVault.payloads.GameDTO;
import C043.GameVault.repositories.PlayedListRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PlayedListService {
    @Autowired
    private PlayedListRepository playedListRepository;

    public PlayedList postPlaying(GameDTO body, User user) {
        PlayedList found = this.playedListRepository.findByGameId(body.gameId());
        if (found != null) throw new BadRequestException("Game already in list");
        PlayedList newPlaying = new PlayedList(body.gameId(), user);
        return this.playedListRepository.save(newPlaying);
    }

    public List<PlayedList> getPlayingListByUser(User user) {
        return this.playedListRepository.findAllByUser(user);
    }

    public PlayedList getPlayingListById(int id) {
        return this.playedListRepository.findById(id).orElseThrow(() -> new NotFoundException("Game not found"));
    }

    public void deletePlayingList(User user, int id) {
        PlayedList found = this.getPlayingListById(id);
        if (found.getUser().getId() != user.getId()) throw new UnauthorizedException("You don't have permissions to delete this game");
        this.playedListRepository.delete(found);
    }
}
