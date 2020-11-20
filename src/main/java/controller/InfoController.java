package controller;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import view.Info;

public class InfoController {
    
    private Info info;

	/**
	* Controls the actions for key events on info page.
	* <pre>
    * Methods:<br>handleButtonExit(ActionEvent event)
    * handleButtonExitMouseIn(MouseEvent event)
    * handleButtonExitMouseOut(MouseEvent event)
	* </pre>
    *
    * @param    info
    * @see      Info
	*/
    public InfoController(Info info) {
        this.info = info;
    }

    /**
    * Change page to main menu page when the exit button is clicked.
    * 
    * @param    event
    */
    public void handleButtonExit(ActionEvent event) {
        info.getApp().showMainMenu();
    }

    /**
    * Configures image when the mouse is hovering on the exit button.
    * 
    * @param    event
    */
    public void handleButtonExitMouseIn(MouseEvent event) {
        ImageView exitMouseIn = new ImageView(new Image("file:media/images/buttons/backMouseIn.png"));
        exitMouseIn.setFitHeight(30);
		exitMouseIn.setFitWidth(25);
        info.getExitButton().setGraphic(exitMouseIn);
    }
    
    /**
    * Configures image when the mouse is moved away from the exit button.
    * 
    * @param    event
    */
    public void handleButtonExitMouseOut(MouseEvent event) {
        ImageView exitMouseOut = new ImageView(new Image("file:media/images/buttons/back.png"));
        exitMouseOut.setFitHeight(30);
		exitMouseOut.setFitWidth(25);
        info.getExitButton().setGraphic(exitMouseOut);
    }
}
