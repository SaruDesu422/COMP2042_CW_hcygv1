package controller;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import view.Game;

public class GameController {
    
    private Game game;

    /**
    * This method controls the continue and menu buttons in the
    * scoreboard pane.
	*
	*/
    public GameController(Game game) {
        this.game = game;
    }

    /**
    * This method redirects current stage to main menu pane.
    *
    * @see     main menu scene is loaded
	*/
    public void handleButtonMenu(ActionEvent event) {
        game.getMainMenu().getApp().showMainMenu();
    }

    /**
    * This method sets the image of the main menu button when the cursor is 
    * hovering over it.
    *
    * @see     image of main menu button
	*/
    public void handleButtonMenuMouseIn(MouseEvent event) {
        ImageView menuMouseIn = new ImageView(new Image("file:media/images/info.png"));
        menuMouseIn.setFitHeight(30);
		menuMouseIn.setFitWidth(30);
        game.getMenuButton().setGraphic(menuMouseIn);
    }

    /**
    * This method sets the image of the main menu button when the cursor is not
    * hovering over it anymore.
    *
    * @see     image of main menu button
	*/
    public void handleButtonMenuMouseOut(MouseEvent event) {
        ImageView menuMouseOut = new ImageView(new Image("file:media/images/info.png"));
        menuMouseOut.setFitHeight(30);
		menuMouseOut.setFitWidth(30);
        game.getMenuButton().setGraphic(menuMouseOut);
    }
}
