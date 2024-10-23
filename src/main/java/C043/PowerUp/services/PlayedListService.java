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
public class PlayedListService {
    @Autowired
    private PlayedListRepository playedListRepository;

    @Autowired
    private BackLogRepository backLogRepository;

    @Autowired
    private PlayingListRepository playingListRepository;

    public PlayedList postPlaying(GameDTO body, User user) {
        PlayedList found =
                this.playedListRepository.findByGameIdAndUser(body.gameId(),
                        user);
        if (found != null)
            throw new BadRequestException("Game already in list");
        BackLog foundBackLog =
                this.backLogRepository.findByGameIdAndUser(body.gameId(), user);
        if (foundBackLog != null) this.backLogRepository.delete(foundBackLog);
        PlayingList foundPlaying =
                this.playingListRepository.findByGameIdAndUser(body.gameId(),
                        user);
        if (foundPlaying != null)
            this.playingListRepository.delete(foundPlaying);
        PlayedList newPlayed = new PlayedList(body.gameId(), body.userRating()
                , user);
        return this.playedListRepository.save(newPlayed);
    }

    public List<PlayedList> getPlayedListByUser(User user) {
        return this.playedListRepository.findAllByUser(user);
    }

    public PlayedList getPlayedListById(int id) {
        return this.playedListRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Game not found"));
    }

    public void deletePlayedList(User user, int id) {
        PlayedList found =
                this.playedListRepository.findByGameIdAndUser(id, user);
        if (found == null) throw new NotFoundException("Game not found");
        if (found.getUser().getId() != user.getId())
            throw new UnauthorizedException(
                    "You don't have permissions to delete this game");
        this.playedListRepository.delete(found);
    }

    public PlayedList rateGame(User user, int id, RatingDTO body) {
        PlayedList found = this.playedListRepository.findByGameIdAndUser(id,
                user);
        if (found == null) throw new NotFoundException("Game not found");
        found.setUserRating(body.rating());
        return this.playedListRepository.save(found);
    }
}
