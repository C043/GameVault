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
public class BackLogService {
    @Autowired
    private BackLogRepository backLogRepository;

    @Autowired
    private PlayedListRepository playedListRepository;

    @Autowired
    private PlayingListRepository playingListRepository;

    public BackLog postBackLog(GameDTO body, User user) {
        BackLog found =
                this.backLogRepository.findByGameIdAndUser(body.gameId(), user);
        if (found != null)
            throw new BadRequestException("Game already in list");
        PlayedList foundPlayed =
                this.playedListRepository.findByGameIdAndUser(body.gameId(),
                        user);
        if (foundPlayed != null) this.playedListRepository.delete(foundPlayed);
        PlayingList foundPlaying =
                this.playingListRepository.findByGameIdAndUser(body.gameId(),
                        user);
        if (foundPlaying != null)
            this.playingListRepository.delete(foundPlaying);
        BackLog newBackLog =
                new BackLog(body.gameId(), body.userRating(), user);
        return this.backLogRepository.save(newBackLog);
    }

    public List<BackLog> getBackLogByUser(User user) {
        return this.backLogRepository.findAllByUser(user);
    }

    public BackLog getBackLogById(int id) {
        return this.backLogRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Game not found"));
    }

    public void deleteBackLog(User user, int id) {
        BackLog found = this.backLogRepository.findByGameIdAndUser(id, user);
        if (found == null) throw new NotFoundException("Game not found");
        if (found.getUser().getId() != user.getId())
            throw new UnauthorizedException(
                    "You don't have permissions to delete this game");
        this.backLogRepository.delete(found);
    }

    public BackLog rateGame(User user, int id, RatingDTO body) {
        BackLog found = this.backLogRepository.findByGameIdAndUser(id,
                user);
        if (found == null) throw new NotFoundException("Game not found");
        found.setUserRating(body.rating());
        return this.backLogRepository.save(found);
    }
}
