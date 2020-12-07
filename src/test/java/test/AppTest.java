package test;

import controller.*;
import model.*;
import view.*;

import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for certain methods in the app.
 * 
 */
public class AppTest extends ApplicationTest {

    Animal animal;
    App app;
    Info info;
    MainMenu mainMenu;
    Game game;
    ScoreBoard scoreBoard;
    BorderPane testPane;

    // @Before
    // public void setUp() {
    //     app = new App();
    //     info = new Info(app);
    //     mainMenu = new MainMenu(app);
    //     game = new Game(mainMenu);
    //     scoreBoard = new ScoreBoard(mainMenu, game);
    // }

    @Test
    public void test() {
    }
}