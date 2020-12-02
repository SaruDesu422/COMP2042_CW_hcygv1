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
    private final Image CONTINUE_MOUSE_IN = new Image("file:media/images/buttons/continueMouseIn.png");
    private final Image CONTINUE = new Image("file:media/images/buttons/continue.png");
    private final Image MAINMENU_MOUSE_IN = new Image("file:media/images/buttons/mainmenuMouseIn.png");
    private final Image MAINMENU = new Image("file:media/images/buttons/mainmenu.png");

	/**
	* Controls the actions for key events on main menu page.
	* <pre>
    * Methods:<br>handleButtonContinue(ActionEvent event)
    * handleButtonContinueMouseIn(MouseEvent event)
    * handleButtonContinueMouseOut(MouseEvent event)
    * <br>handleButtonMenu(ActionEvent event)
    * handleButtonMenuMouseIn(MouseEvent event)
    * handleButtonMenuMouseOut(MouseEvent event)
	* </pre>
    *
    * @param    game
    * @param    scoreBoard
    * @see      Game
    * @see      ScoreBoard
	*/
    public ScoreBoardController(ScoreBoard scoreBoard, Game game) {
        this.scoreBoard = scoreBoard;
        this.game = game;
    }

    /**
    * Starts next level when the continue button is clicked.
    * 
    * @param    event
    */
    public void handleButtonContinue(ActionEvent event) {
        game.startNextLevel();
        scoreBoard.getMainMenu().getApp().changePage(game.getStage());
    }

    /**
    * Change page to main menu page when the menu button is clicked.
    * 
    * @param    event
    */
    public void handleButtonMenu(ActionEvent event) {
        this.scoreBoard.getMainMenu().getApp().showMainMenu();
    }

    /**
    * Configures image when the mouse is hovering on the continue button.
    * 
    * @param    event
    */
    public void handleButtonContinueMouseIn(MouseEvent event) {
        ImageView continueMouseIn = new ImageView(CONTINUE_MOUSE_IN);
        continueMouseIn.setFitHeight(100);
		continueMouseIn.setPreserveRatio(true);
        scoreBoard.getContinueButton().setGraphic(continueMouseIn);
    }

    /**
    * Configures image when the mouse is moved away from the continue button.
    * 
    * @param    event
    */
    public void handleButtonContinueMouseOut(MouseEvent event) {
        ImageView continueMouseOut = new ImageView(CONTINUE);
        continueMouseOut.setFitHeight(100);
		continueMouseOut.setPreserveRatio(true);
        scoreBoard.getContinueButton().setGraphic(continueMouseOut);
    }

    /**
    * Configures image when the mouse is hovering on the menu button.
    * 
    * @param    event
    */
    public void handleButtonMenuMouseIn(MouseEvent event) {
        ImageView menuMouseIn = new ImageView(MAINMENU_MOUSE_IN);
        menuMouseIn.setFitHeight(100);
		menuMouseIn.setPreserveRatio(true);
        scoreBoard.getMenuButton().setGraphic(menuMouseIn);
    }

    /**
    * Configures image when the mouse is moved away from the menu button.
    * 
    * @param    event
    */
    public void handleButtonMenuMouseOut(MouseEvent event) {
        ImageView menuMouseOut = new ImageView(MAINMENU);
        menuMouseOut.setFitHeight(100);
		menuMouseOut.setPreserveRatio(true);
        scoreBoard.getMenuButton().setGraphic(menuMouseOut);
    }
}
