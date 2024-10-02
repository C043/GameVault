package C043.GameVault.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/games")
public class GamesController {
    private final String url = "https://api.rawg.io/api/";
    @Autowired
    private RestTemplate restTemplate;

    @Value("${rawg.key}")
    private String key;

    @GetMapping
    public ResponseEntity<String> getGames() {
        return restTemplate.getForEntity(this.url + "/games?key=" + this.key, String.class);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<String> getGameById(@PathVariable String gameId) {
        return restTemplate.getForEntity(this.url + "/games/" + gameId + "?key=" + this.key, String.class);
    }
}
