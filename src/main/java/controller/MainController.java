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
	* Controls the actions for key events on main menu page.
	* <pre>
    * Methods:<br>handleButtonStart(ActionEvent event)
    * handleButtonStartMouseIn(MouseEvent event)
    * handleButtonStartMouseOut(MouseEvent event)
    * <br>handleButtonInfo(ActionEvent event)
    * handleButtonInfoMouseIn(MouseEvent event)
    * handleButtonInfoMouseOut(MouseEvent event)
	* </pre>
    *
    * @param    game
    * @see      Game
	*/
    public MainController(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        this.info = new Info(mainMenu.getApp());
    }

    /**
    * Starts level when the start button is clicked.
    * 
    * @param    event
    */
    public void handleButtonStart(ActionEvent event) {
        Game game = new Game(mainMenu);
        game.startNextLevel();
        mainMenu.getApp().changePage(game.getStage());
    }
    
    /**
    * Change page to info page when the info button is clicked.
    * 
    * @param    event
    */
    public void handleButtonInfo(ActionEvent event) {
        mainMenu.getApp().changePage(info);
    }

    /**
    * Configures image when the mouse is hovering on the start button.
    * 
    * @param    event
    */
    public void handleButtonStartMouseIn(MouseEvent event) {
        ImageView startMouseIn = new ImageView(new Image("file:media/images/buttons/startMouseIn.png"));
		startMouseIn.setFitHeight(100);
		startMouseIn.setPreserveRatio(true);
        mainMenu.getStartButton().setGraphic(startMouseIn);
    }

    /**
    * Configures image when the mouse is moved away from the start button.
    * 
    * @param    event
    */
    public void handleButtonStartMouseOut(MouseEvent event) {
        ImageView startMouseOut = new ImageView(new Image("file:media/images/buttons/start.png"));
		startMouseOut.setFitHeight(100);
		startMouseOut.setPreserveRatio(true);
        mainMenu.getStartButton().setGraphic(startMouseOut);
    }

    /**
    * Configures image when the mouse is hovering on the info button.
    * 
    * @param    event
    */
    public void handleButtonInfoMouseIn(MouseEvent event) {
        ImageView infoMouseIn = new ImageView(new Image("file:media/images/buttons/infoMouseIn.png"));
		infoMouseIn.setFitHeight(30);
		infoMouseIn.setFitWidth(25);
        mainMenu.getInfoButton().setGraphic(infoMouseIn);
    }
    
    /**
    * Configures image when the mouse is moved away from the info button.
    * 
    * @param    event
    */
    public void handleButtonInfoMouseOut(MouseEvent event) {
        ImageView infoMouseOut = new ImageView(new Image("file:media/images/buttons/info.png"));
        infoMouseOut.setFitHeight(30);
		infoMouseOut.setFitWidth(25);
        mainMenu.getInfoButton().setGraphic(infoMouseOut);
    }
}
