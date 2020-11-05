package controller;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import view.Info;

public class InfoController {
    
    private Info info;

    /**
    * This method controls the start and info buttons in the
    * main menu pane.
	*
	*/
    public InfoController(Info info) {
        this.info = info;
    }

    /**
    * This method redirects current stage to info pane.
    *
    * @see     info scene is loaded
	*/
    public void handleButtonExit(ActionEvent event) {
        info.getApp().showMainMenu();
    }

    /**
    * This method sets the image of the info button when the cursor is 
    * hovering over it.
    *
    * @see     image of info button
	*/
    public void handleButtonExitMouseIn(MouseEvent event) {
        ImageView exitMouseIn = new ImageView(new Image("file:media/images/info.png"));
        exitMouseIn.setFitHeight(30);
		exitMouseIn.setFitWidth(30);
        info.getExitButton().setGraphic(exitMouseIn);
    }
    
    /**
    * This method sets the image of the info button when the cursor is not
    * hovering over it anymore.
    *
    * @see     image of info button
	*/
    public void handleButtonExitMouseOut(MouseEvent event) {
        ImageView exitMouseOut = new ImageView(new Image("file:media/images/info.png"));
        exitMouseOut.setFitHeight(30);
		exitMouseOut.setFitWidth(30);
        info.getExitButton().setGraphic(exitMouseOut);
    }
}
