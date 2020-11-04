package controller;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import view.Level;
import view.MainMenu;
import view.ScoreBoard;

public class ScoreBoardController {
    
    private ScoreBoard scoreBoard;
    Level level;

    /**
    * This method controls the continue and menu buttons in the
    * scoreboard pane.
	*
	*/
    public ScoreBoardController(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
        MainMenu mainMenu = this.scoreBoard.getMainMenu();
        if (mainMenu.getCurrentLevel() < 2) {
            scoreBoard.getContinueButton().setOnAction(this::handleButtonContinue);
            scoreBoard.getContinueButton().addEventHandler(MouseEvent.MOUSE_ENTERED, this::handleButtonContinueMouseIn);
            scoreBoard.getContinueButton().addEventHandler(MouseEvent.MOUSE_ENTERED, this::handleButtonContinueMouseOut);
        }
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
        MainMenu mainMenu = this.scoreBoard.getMainMenu();
        
        mainMenu.setCurrentLevel();
        this.level = new Level(mainMenu, mainMenu.getCurrentLevel());

        Scene levelScene = new Scene(level.getCurrentStage(), 600, 800);
        mainMenu.getApp().getPrimaryStage().setScene(levelScene);
        mainMenu.getApp().getPrimaryStage().show();
    }

    /**
    * This method redirects current stage to main menu pane.
    *
    * @see     main menu scene is loaded
	*/
    private void handleButtonMenu(ActionEvent event) {
        //go to menu
        MainMenu mainMenu = this.scoreBoard.getMainMenu();
        
        Scene menuscene = mainMenu.getApp().getMainScene();
        mainMenu.getApp().getPrimaryStage().setScene(menuscene);
        mainMenu.getApp().getPrimaryStage().show();
    }

    /**
    * This method sets the image of the continue button when the cursor is 
    * hovering over it.
    *
    * @see     image of continue button
	*/
    private void handleButtonContinueMouseIn(MouseEvent event) {
        ImageView continueMouseIn = new ImageView(new Image("file:media/images/info.png"));
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
        ImageView menuMouseIn = new ImageView(new Image("file:media/images/info.png"));
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
