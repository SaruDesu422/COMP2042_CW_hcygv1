package controller;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import view.Game;
import view.ScoreBoard;

public class ScoreBoardController {
    
    private ScoreBoard scoreBoard;
    private Game game;

    /**
    * This method controls the continue and menu buttons in the
    * scoreboard pane.
	*
	*/
    public ScoreBoardController(ScoreBoard scoreBoard, Game game) {
        this.scoreBoard = scoreBoard;
        this.game = game;
    }

    /**
    * This method redirects current stage to next level pane.
    *
    * @see     next level scene is loaded
	*/
    public void handleButtonContinue(ActionEvent event) {
        game.startNextLevel();
        scoreBoard.getMainMenu().getApp().changePage(game.getStage());
    }

    /**
    * This method redirects current stage to main menu pane.
    *
    * @see     main menu scene is loaded
	*/
    public void handleButtonMenu(ActionEvent event) {
        this.scoreBoard.getMainMenu().getApp().showMainMenu();
    }

    /**
    * This method sets the image of the continue button when the cursor is 
    * hovering over it.
    *
    * @see     image of continue button
	*/
    public void handleButtonContinueMouseIn(MouseEvent event) {
        ImageView continueMouseIn = new ImageView(new Image("file:media/images/buttons/continueMouseIn.png"));
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
    public void handleButtonContinueMouseOut(MouseEvent event) {
        ImageView continueMouseOut = new ImageView(new Image("file:media/images/buttons/continue.png"));
        continueMouseOut.setFitHeight(100);
		continueMouseOut.setPreserveRatio(true);
        scoreBoard.getContinueButton().setGraphic(continueMouseOut);
    }

    /**
    * This method sets the image of the main menu button when the cursor is 
    * hovering over it.
    *
    * @see     image of main menu button
	*/
    public void handleButtonMenuMouseIn(MouseEvent event) {
        ImageView menuMouseIn = new ImageView(new Image("file:media/images/buttons/mainmenuMouseIn.png"));
        menuMouseIn.setFitHeight(100);
		menuMouseIn.setPreserveRatio(true);
        scoreBoard.getMenuButton().setGraphic(menuMouseIn);
    }

    /**
    * This method sets the image of the main menu button when the cursor is not
    * hovering over it anymore.
    *
    * @see     image of main menu button
	*/
    public void handleButtonMenuMouseOut(MouseEvent event) {
        ImageView menuMouseOut = new ImageView(new Image("file:media/images/buttons/mainmenu.png"));
        menuMouseOut.setFitHeight(100);
		menuMouseOut.setPreserveRatio(true);
        scoreBoard.getMenuButton().setGraphic(menuMouseOut);
    }
}
