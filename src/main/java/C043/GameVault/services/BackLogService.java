package C043.GameVault.services;

import C043.GameVault.entities.BackLog;
import C043.GameVault.entities.User;
import C043.GameVault.exceptions.BadRequestException;
import C043.GameVault.payloads.GameDTO;
import C043.GameVault.repositories.BackLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackLogService {
    @Autowired
    private BackLogRepository backLogRepository;

    public BackLog postBackLog(GameDTO body, User user) {
        BackLog found = this.backLogRepository.findByGameId(body.gameId());
        if (found != null) throw new BadRequestException("Game already in list");
        BackLog newBackLog = new BackLog(body.gameId(), user);
        return this.backLogRepository.save(newBackLog);
    }
}
