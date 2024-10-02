package C043.GameVault.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/games")
public class GamesController {
    private final String url = "https://api.rawg.io/api/";
    @Autowired
    private RestTemplate restTemplate;

    @Value("${rawg.key}")
    private String key;

    @GetMapping
    public ResponseEntity<String> getGames(@RequestParam(required = false) Map<String, String> allParams) {
        if (allParams != null && allParams.get("platforms") == null && allParams.get("genres") == null)
            return restTemplate.getForEntity(this.url + "/games?key=" + this.key +
                            "&search=" + allParams.getOrDefault("search", "") +
                            "&page=" + allParams.getOrDefault("page", "1") +
                            "&ordering=" + allParams.getOrDefault("ordering", ""),
                    String.class);
        if (allParams != null && allParams.get("platforms") != null && allParams.get("genres") == null)
            return restTemplate.getForEntity(this.url + "/games?key=" + this.key +
                            "&search=" + allParams.getOrDefault("search", "") +
                            "&ordering=" + allParams.getOrDefault("ordering", "") +
                            "&platforms=" + allParams.get("platforms"),
                    String.class);
        if (allParams != null && allParams.get("platforms") == null && allParams.get("genres") != null)
            return restTemplate.getForEntity(this.url + "/games?key=" + this.key +
                            "&search=" + allParams.getOrDefault("search", "") +
                            "&page=" + allParams.getOrDefault("page", "1") +
                            "&ordering=" + allParams.getOrDefault("ordering", "") +
                            "&genres=" + allParams.get("genres"),
                    String.class);
        if (allParams != null && allParams.get("platforms") != null && allParams.get("genres") != null)
            return restTemplate.getForEntity(this.url + "/games?key=" + this.key +
                            "&search=" + allParams.getOrDefault("search", "") +
                            "&page=" + allParams.getOrDefault("page", "1") +
                            "&ordering=" + allParams.getOrDefault("ordering", "") +
                            "&genres=" + allParams.get("genres") +
                            "&platforms=" + allParams.get("platforms"),
                    String.class);
        return restTemplate.getForEntity(this.url + "/games?key=" + this.key, String.class);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<String> getGameById(@PathVariable String gameId) {
        return restTemplate.getForEntity(this.url + "/games/" + gameId + "?key=" + this.key, String.class);

    }
}
