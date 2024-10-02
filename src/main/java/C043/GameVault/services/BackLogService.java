package C043.GameVault.services;

import C043.GameVault.entities.BackLog;
import C043.GameVault.entities.PlayedList;
import C043.GameVault.entities.PlayingList;
import C043.GameVault.entities.User;
import C043.GameVault.exceptions.BadRequestException;
import C043.GameVault.exceptions.NotFoundException;
import C043.GameVault.exceptions.UnauthorizedException;
import C043.GameVault.payloads.GameDTO;
import C043.GameVault.repositories.BackLogRepository;
import C043.GameVault.repositories.PlayedListRepository;
import C043.GameVault.repositories.PlayingListRepository;
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
    private PlayedListService playedListService;

    @Autowired
    private PlayingListRepository playingListRepository;

    @Autowired
    private PlayingService playingService;

    public BackLog postBackLog(GameDTO body, User user) {
        BackLog found = this.backLogRepository.findByGameIdAndUser(body.gameId(), user);
        if (found != null) throw new BadRequestException("Game already in list");
        PlayedList foundPlayed = this.playedListRepository.findByGameIdAndUser(body.gameId(), user);
        if (foundPlayed != null) this.playedListService.deletePlayedList(user, foundPlayed.getId());
        PlayingList foundPlaying = this.playingListRepository.findByGameIdAndUser(body.gameId(), user);
        if (foundPlaying != null) this.playingService.deletePlayingList(user, foundPlaying.getGameId());
        BackLog newBackLog = new BackLog(body.gameId(), user);
        return this.backLogRepository.save(newBackLog);
    }

    public List<BackLog> getBackLogByUser(User user) {
        return this.backLogRepository.findAllByUser(user);
    }

    public BackLog getBackLogById(int id) {
        return this.backLogRepository.findById(id).orElseThrow(() -> new NotFoundException("Game not found"));
    }

    public void deleteBackLog(User user, int id) {
        BackLog found = this.backLogRepository.findByGameIdAndUser(id, user);
        if (found == null) throw new NotFoundException("Game not found");
        if (found.getUser().getId() != user.getId()) throw new UnauthorizedException("You don't have permissions to delete this game");
        this.backLogRepository.delete(found);
    }
}
