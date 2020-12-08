package controller;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import view.Game;
import view.ScoreBoard;
import view.LeaderBoard;

public class ScoreBoardController {
    
    private ScoreBoard scoreBoard;
    private Game game;
    private LeaderBoard leader;
    private final Image CONTINUE_MOUSE_IN = new Image("file:media/images/buttons/continueMouseIn.png");
    private final Image CONTINUE = new Image("file:media/images/buttons/continue.png");
    private final Image MAINMENU_MOUSE_IN = new Image("file:media/images/buttons/mainmenuMouseIn.png");
    private final Image MAINMENU = new Image("file:media/images/buttons/mainmenu.png");
    private final Image LEADER_MOUSE_IN = new Image("file:media/images/buttons/leaderMouseIn.png");
    private final Image LEADER = new Image("file:media/images/buttons/leader.png");

	/**
	* Controls the actions for key events on main menu page.
	* <pre>
    * Methods:<br>handleButtonContinue(ActionEvent event)
    * handleButtonContinueMouseIn(MouseEvent event)
    * handleButtonContinueMouseOut(MouseEvent event)
    * <br>handleButtonMenu(ActionEvent event)
    * handleButtonMenuMouseIn(MouseEvent event)
    * handleButtonMenuMouseOut(MouseEvent event)
    * <br>handleButtonLeader(ActionEvent event)
    * handleButtonLeaderMouseIn(MouseEvent event)
    * handleButtonLeaderMouseOut(MouseEvent event)
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
        this.leader = new LeaderBoard(scoreBoard.getMainMenu().getApp(), scoreBoard);
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
    * Change page to leaderboard page when the leader button is clicked.
    * 
    * @param    event
    */
    public void handleButtonLeader(ActionEvent event) {
        this.scoreBoard.getMainMenu().getApp().showLeaderBoard(leader);
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

    /**
    * Configures image when the mouse is hovering on the leader button.
    * 
    * @param    event
    */
    public void handleButtonLeaderMouseIn(MouseEvent event) {
        ImageView leaderMouseIn = new ImageView(LEADER_MOUSE_IN);
        leaderMouseIn.setFitHeight(30);
		leaderMouseIn.setFitWidth(25);
        scoreBoard.getLeaderButton().setGraphic(leaderMouseIn);
    }

    /**
    * Configures image when the mouse is moved away from the leader button.
    * 
    * @param    event
    */
    public void handleButtonLeaderMouseOut(MouseEvent event) {
        ImageView leaderMouseOut = new ImageView(LEADER);
        leaderMouseOut.setFitHeight(30);
		leaderMouseOut.setFitWidth(25);
        scoreBoard.getLeaderButton().setGraphic(leaderMouseOut);
    }
}
