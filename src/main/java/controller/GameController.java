package controller;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import view.Game;

public class GameController {
    
    private Game game;

	/**
	* Controls the actions for key events on main menu page.
	* <pre>
    * Methods:<br>handleButtonMenu(ActionEvent event)
    * handleButtonMenuMouseIn(MouseEvent event)
    * handleButtonMenuMouseOut(MouseEvent event)
	* </pre>
    *
    * @param    game
    * @see      Game
	*/
    public GameController(Game game) {
        this.game = game;
    }

    /**
    * Change page to main menu page when the menu button is clicked.
    * 
    * @param    event
    */
    public void handleButtonMenu(ActionEvent event) {
        game.getMainMenu().getApp().showMainMenu();
    }

    /**
    * Configures image when the mouse is hovering on the menu button.
    * 
    * @param    event
    */
    public void handleButtonMenuMouseIn(MouseEvent event) {
        ImageView menuMouseIn = new ImageView(new Image("file:media/images/buttons/homeMouseIn.png"));
        menuMouseIn.setFitHeight(30);
		menuMouseIn.setFitWidth(25);
        game.getMenuButton().setGraphic(menuMouseIn);
    }

    /**
    * Configures image when the mouse is moved away from the menu button.
    * 
    * @param    event
    */
    public void handleButtonMenuMouseOut(MouseEvent event) {
        ImageView menuMouseOut = new ImageView(new Image("file:media/images/buttons/home.png"));
        menuMouseOut.setFitHeight(30);
		menuMouseOut.setFitWidth(25);
        game.getMenuButton().setGraphic(menuMouseOut);
    }
}
