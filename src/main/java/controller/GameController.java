package controller;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import view.Game;

public class GameController {
    
    private Game game;
    private final Image HOME_MOUSE_IN = new Image("file:media/images/buttons/homeMouseIn.png");
    private final Image HOME = new Image("file:media/images/buttons/home.png");

    /**
     * Controles the actions for mouse events on game page.
     * 
     * @param   game
     * @see     Game
     */
    public GameController(Game game) {
        this.game = game;
    }

    /**
     * Change page to main menu page when the menu button is clicked.
     * 
     * @param   event
     */
    public void handleButtonMenu(ActionEvent event) {
        game.stop();
        game.getMainMenu().getApp().showMainMenu();
    }

    /**
     * Configures image when the cursor is hovering on the menu button.
     * 
     * @param   event
     */
    public void handleButtonMenuMouseIn(MouseEvent event) {
        ImageView menuMouseIn = new ImageView(HOME_MOUSE_IN);
        menuMouseIn.setFitHeight(30);
		menuMouseIn.setFitWidth(25);
        game.getMenuButton().setGraphic(menuMouseIn);
    }

    /**
     * Configures image when the cursor is moved away from the menu button.
     * 
     * @param   event
     */
    public void handleButtonMenuMouseOut(MouseEvent event) {
        ImageView menuMouseOut = new ImageView(HOME);
        menuMouseOut.setFitHeight(30);
		menuMouseOut.setFitWidth(25);
        game.getMenuButton().setGraphic(menuMouseOut);
    }
}
