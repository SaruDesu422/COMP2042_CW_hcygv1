package controller;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import view.App;
import view.frames.Level;
import view.frames.MainMenu;
import view.frames.ScoreBoard;

public class LevelController {
    private Level level;
    private ScoreBoard scoreBoard;
    private App app;
    private MainMenu mainmenu;

    public LevelController(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
        this.app = scoreBoard.getApp();
        scoreBoard.getContinueButton().setOnAction(this::handleButtonContinue);
        scoreBoard.getContinueButton().addEventHandler(MouseEvent.MOUSE_ENTERED, this::handleButtonContinueMouseIn);
        scoreBoard.getContinueButton().addEventHandler(MouseEvent.MOUSE_ENTERED, this::handleButtonContinueMouseOut);
        scoreBoard.getMenuButton().setOnAction(this::handleButtonMenu);
        scoreBoard.getMenuButton().addEventHandler(MouseEvent.MOUSE_ENTERED, this::handleButtonMenuMouseIn);
        scoreBoard.getMenuButton().addEventHandler(MouseEvent.MOUSE_ENTERED, this::handleButtonMenuMouseOut);
    }

    private void handleButtonContinue(ActionEvent event) {
        // go to next level

    }

    private void handleButtonMenu(ActionEvent event) {
        //go to menu
        this.mainmenu = new MainMenu(scoreBoard.getApp());
        Scene menuscene = new Scene(this.mainmenu, 600, 800);
        app.getPrimaryStage().setScene(menuscene);
        app.getPrimaryStage().show();
    }

    private void handleButtonContinueMouseIn(MouseEvent event) {
        // animate mouse in continue
    }

    private void handleButtonContinueMouseOut(MouseEvent event) {
        // animate mouse out continue
    }

    private void handleButtonMenuMouseIn(MouseEvent event) {
        // animate mouse in menu
    }

    private void handleButtonMenuMouseOut(MouseEvent event) {
        // animate mouse out menu
    }
}
