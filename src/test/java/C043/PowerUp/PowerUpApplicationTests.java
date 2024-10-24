package C043.PowerUp;

import C043.PowerUp.controllers.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PowerUpApplicationTests {
    @Autowired
    AuthController authController;

    @Autowired
    CustomListController customListController;

    @Autowired
    GameListController gameListController;

    @Autowired
    GameNotesController gameNotesController;

    @Autowired
    GamesController gamesController;

    @Autowired
    ReviewController reviewController;

    @Autowired
    UserController userController;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(reviewController);
        Assertions.assertNotNull(userController);
        Assertions.assertNotNull(gameListController);
        Assertions.assertNotNull(gamesController);
        Assertions.assertNotNull(authController);
        Assertions.assertNotNull(customListController);
        Assertions.assertNotNull(gameNotesController);
    }
}
