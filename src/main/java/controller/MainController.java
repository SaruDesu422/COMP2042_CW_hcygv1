package controller;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import view.Game;
import view.Info;
import view.MainMenu;

public class MainController {
    
    private MainMenu mainMenu;
    private Info info;

    /**
    * This method controls the start and info buttons in the
    * main menu pane.
	*
	*/
    public MainController(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        this.info = new Info(mainMenu.getApp());
    }

    /**
    * When Start Button is clicked, start game by changing 
    * page to first level.
    *
    * @see      ActionEvent
	*/
    public void handleButtonStart(ActionEvent event) {
        Game game = new Game(mainMenu);
        game.startNextLevel();
        mainMenu.getApp().changePage(game.getStage());
    }
    
    /**
    * This method redirects current stage to info pane.
    *
    * @see     info scene is loaded
	*/
    public void handleButtonInfo(ActionEvent event) {
        mainMenu.getApp().changePage(info);
    }

    /**
    * This method sets the image of the start button when the cursor is 
    * hovering over it.
    *
    * @see     image of start button
	*/
    public void handleButtonStartMouseIn(MouseEvent event) {
        ImageView startMouseIn = new ImageView(new Image("file:media/images/buttons/startMouseIn.png"));
		startMouseIn.setFitHeight(100);
		startMouseIn.setPreserveRatio(true);
        mainMenu.getStartButton().setGraphic(startMouseIn);
    }

    /**
    * This method sets the image of the start button when the cursor is not
    * hovering over it anymore.
    *
    * @see     image of start button
	*/
    public void handleButtonStartMouseOut(MouseEvent event) {
        ImageView startMouseOut = new ImageView(new Image("file:media/images/buttons/start.png"));
		startMouseOut.setFitHeight(100);
		startMouseOut.setPreserveRatio(true);
        mainMenu.getStartButton().setGraphic(startMouseOut);
    }

    /**
    * This method sets the image of the info button when the cursor is 
    * hovering over it.
    *
    * @see     image of info button
	*/
    public void handleButtonInfoMouseIn(MouseEvent event) {
        ImageView infoMouseIn = new ImageView(new Image("file:media/images/buttons/infoMouseIn.png"));
		infoMouseIn.setFitHeight(30);
		infoMouseIn.setFitWidth(25);
        mainMenu.getInfoButton().setGraphic(infoMouseIn);
    }
    
    /**
    * This method sets the image of the info button when the cursor is not
    * hovering over it anymore.
    *
    * @see     image of info button
	*/
    public void handleButtonInfoMouseOut(MouseEvent event) {
        ImageView infoMouseOut = new ImageView(new Image("file:media/images/buttons/info.png"));
        infoMouseOut.setFitHeight(30);
		infoMouseOut.setFitWidth(25);
        mainMenu.getInfoButton().setGraphic(infoMouseOut);
    }
}
