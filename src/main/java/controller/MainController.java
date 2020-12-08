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
    private Game game;
    private Info info;
    private final Image START_MOUSE_IN = new Image("file:media/images/buttons/startMouseIn.png");
    private final Image START = new Image("file:media/images/buttons/start.png");
    private final Image INFO_MOUSE_IN = new Image("file:media/images/buttons/infoMouseIn.png");
    private final Image INFO = new Image("file:media/images/buttons/info.png");

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
        this.game = new Game(mainMenu);
    }

    /**
    * Starts level when the start button is clicked.
    * 
    * @param    event
    */
    public void handleButtonStart(ActionEvent event) {
        game.resetLevel();
        game.startNextLevel();
        mainMenu.getApp().changePage(game.getStage());
    }
    
    /**
    * Change page to info page when the info button is clicked.
    * 
    * @param    event
    */
    public void handleButtonInfo(ActionEvent event) {
        mainMenu.getApp().showInfo(info);
    }

    /**
    * Configures image when the mouse is hovering on the start button.
    * 
    * @param    event
    */
    public void handleButtonStartMouseIn(MouseEvent event) {
        ImageView startMouseIn = new ImageView(START_MOUSE_IN);
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
        ImageView startMouseOut = new ImageView(START);
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
        ImageView infoMouseIn = new ImageView(INFO_MOUSE_IN);
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
        ImageView infoMouseOut = new ImageView(INFO);
        infoMouseOut.setFitHeight(30);
		infoMouseOut.setFitWidth(25);
        mainMenu.getInfoButton().setGraphic(infoMouseOut);
    }
}
