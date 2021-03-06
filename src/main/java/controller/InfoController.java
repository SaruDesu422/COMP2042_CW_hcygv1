package controller;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import view.Info;

public class InfoController {
    
    private Info info;
    private final Image BACK_MOUSE_IN = new Image("file:media/images/buttons/backMouseIn.png");
    private final Image BACK = new Image("file:media/images/buttons/back.png");

    /**
     * Controls the actions for mouse event on info page.
     * 
     * @param   info
     * @see     Info
     */
    public InfoController(Info info) {
        this.info = info;
    }

    /**
     * Change page to main menu page when the exit button is clicked.
     * 
     * @param   event
     */
    public void handleButtonExit(ActionEvent event) {
        info.getApp().showMainMenu();
    }

    /**
     * Configures image when the cursor is hovering on the exit button.
     * 
     * @param event
     */
    public void handleButtonExitMouseIn(MouseEvent event) {
        ImageView exitMouseIn = new ImageView(BACK_MOUSE_IN);
        exitMouseIn.setFitHeight(30);
		exitMouseIn.setFitWidth(25);
        info.getExitButton().setGraphic(exitMouseIn);
    }
    
    /**
     * Configures image when the cursor is moved away from the exit button.
     * 
     * @param   event
     */
    public void handleButtonExitMouseOut(MouseEvent event) {
        ImageView exitMouseOut = new ImageView(BACK);
        exitMouseOut.setFitHeight(30);
		exitMouseOut.setFitWidth(25);
        info.getExitButton().setGraphic(exitMouseOut);
    }
}
