package C043.PowerUp.services;

import C043.PowerUp.entities.BackLog;
import C043.PowerUp.entities.PlayedList;
import C043.PowerUp.entities.PlayingList;
import C043.PowerUp.entities.User;
import C043.PowerUp.exceptions.BadRequestException;
import C043.PowerUp.exceptions.NotFoundException;
import C043.PowerUp.exceptions.UnauthorizedException;
import C043.PowerUp.payloads.GameDTO;
import C043.PowerUp.payloads.RatingDTO;
import C043.PowerUp.repositories.BackLogRepository;
import C043.PowerUp.repositories.PlayedListRepository;
import C043.PowerUp.repositories.PlayingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayingService {
    @Autowired
    private PlayingListRepository playingListRepository;

    @Autowired
    private BackLogRepository backLogRepository;

    @Autowired
    private PlayedListRepository playedListRepository;

    public PlayingList postPlaying(GameDTO body, User user) {
        PlayingList found =
                this.playingListRepository.findByGameIdAndUser(body.gameId(),
                        user);
        if (found != null)
            throw new BadRequestException("Game already in list");
        PlayedList foundPlayed =
                this.playedListRepository.findByGameIdAndUser(body.gameId(),
                        user);
        if (foundPlayed != null) this.playedListRepository.delete(foundPlayed);
        BackLog foundBackLog =
                this.backLogRepository.findByGameIdAndUser(body.gameId(), user);
        if (foundBackLog != null) this.backLogRepository.delete(foundBackLog);
        PlayingList newPlaying = new PlayingList(body.gameId(),
                body.userRating(), user);
        return this.playingListRepository.save(newPlaying);
    }

    public List<PlayingList> getPlayingListByUser(User user) {
        return this.playingListRepository.findAllByUser(user);
    }

    public PlayingList getPlayingListById(int id) {
        return this.playingListRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Game not found"));
    }

    public void deletePlayingList(User user, int id) {
        PlayingList found =
                this.playingListRepository.findByGameIdAndUser(id, user);
        if (found == null) throw new NotFoundException("Game not found");
        if (found.getUser().getId() != user.getId())
            throw new UnauthorizedException(
                    "You don't have permissions to delete this game");
        this.playingListRepository.delete(found);
    }

    public PlayingList rateGame(User user, int id, RatingDTO body) {
        PlayingList found = this.playingListRepository.findByGameIdAndUser(id,
                user);
        if (found == null) throw new NotFoundException("Game not found");
        found.setUserRating(body.rating());
        return this.playingListRepository.save(found);
    }
}
