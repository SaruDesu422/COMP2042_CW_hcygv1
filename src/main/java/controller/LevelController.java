package controller;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    /**
    * This method controls the continue and menu buttons in the
    * scoreboard pane.
	*
	*/
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

    /**
    * This method redirects current stage to next level pane.
    *
    * @see     next level scene is loaded
	*/
    private void handleButtonContinue(ActionEvent event) {
        // go to next level

    }

    /**
    * This method redirects current stage to main menu pane.
    *
    * @see     main menu scene is loaded
	*/
    private void handleButtonMenu(ActionEvent event) {
        //go to menu
        this.mainmenu = new MainMenu(scoreBoard.getApp());
        Scene menuscene = new Scene(this.mainmenu, 600, 800);
        app.getPrimaryStage().setScene(menuscene);
        app.getPrimaryStage().show();
    }

    /**
    * This method sets the image of the continue button when the cursor is 
    * hovering over it.
    *
    * @see     image of continue button
	*/
    private void handleButtonContinueMouseIn(MouseEvent event) {
        ImageView continueMouseIn = new ImageView(new Image("file:src/main/java/view/images/info.png"));
        continueMouseIn.setFitHeight(100);
		continueMouseIn.setPreserveRatio(true);
        scoreBoard.getContinueButton().setGraphic(continueMouseIn);
    }

    /**
    * This method sets the image of the continue button when the cursor is not
    * hovering over it anymore.
    *
    * @see     image of continue button
	*/
    private void handleButtonContinueMouseOut(MouseEvent event) {
        ImageView continueMouseOut = scoreBoard.getContinueBG();
        scoreBoard.getContinueButton().setGraphic(continueMouseOut);
    }

    /**
    * This method sets the image of the main menu button when the cursor is 
    * hovering over it.
    *
    * @see     image of main menu button
	*/
    private void handleButtonMenuMouseIn(MouseEvent event) {
        ImageView menuMouseIn = new ImageView(new Image("file:src/main/java/view/images/info.png"));
        menuMouseIn.setFitHeight(30);
		menuMouseIn.setFitWidth(30);
        scoreBoard.getMenuButton().setGraphic(menuMouseIn);
    }

    /**
    * This method sets the image of the main menu button when the cursor is not
    * hovering over it anymore.
    *
    * @see     image of main menu button
	*/
    private void handleButtonMenuMouseOut(MouseEvent event) {
        ImageView menuMouseOut = scoreBoard.getMenuBG();
        scoreBoard.getMenuButton().setGraphic(menuMouseOut);
    }
}
