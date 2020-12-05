package test;

import view.*;

import javafx.stage.Stage;
import javafx.scene.Node;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for certain methods in the app.
 * 
 */
public class AppTest extends ApplicationTest {

    App app;
    Info info;
    MainMenu mainMenu;
    Game game;
    Node node;
    ScoreBoard scoreBoard;
    String[] highscoreInfo;

    @Start
    public void start(Stage stage) throws Exception {
        this.app = new App();
        this.info = new Info(app);
        this.mainMenu = new MainMenu(app);
        this.game = new Game(mainMenu);
        this.scoreBoard = new ScoreBoard(mainMenu, game);
    }

    @Test
    void testLevel() {
        game.startNextLevel();
        assertEquals(1, game.getLevel());
    }

    @Test
    void testUpdateScoreSheet() {
        int points = 350;
        for (int i = 0; i < scoreBoard.MAXLEVEL; i++)
            highscoreInfo = scoreBoard.updateScoreSheet(i + 1, points);
        for (int i = 0; i < scoreBoard.MAXLEVEL; i++)
            assertEquals(points, Integer.valueOf(highscoreInfo[i]));
    }

    @Test
    void test() {
        
    }
}