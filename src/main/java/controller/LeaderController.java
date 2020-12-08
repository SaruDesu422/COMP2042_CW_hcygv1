package controller;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import view.LeaderBoard;

public class LeaderController {
    
    private LeaderBoard leader;
    private final Image BACK_MOUSE_IN = new Image("file:media/images/buttons/backMouseIn.png");
    private final Image BACK = new Image("file:media/images/buttons/back.png");

    /**
     * Controls the actions for mouse events on leaderbaord page.
     * 
     * @param   leader
     * @see     LeaderBoard
     */
    public LeaderController(LeaderBoard leader) {
        this.leader = leader;
    }

    /**
     * Change page to scoreBoard page when the exit button is clicked.
     * 
     * @param   event
     */
    public void handleButtonExit(ActionEvent event) {
        leader.getApp().showScoreBoard(leader.getScoreBoard());
    }

    /**
     * Configures image when the cursor is hovering on the exit button.
     * 
     * @param   event
     */
    public void handleButtonExitMouseIn(MouseEvent event) {
        ImageView exitMouseIn = new ImageView(BACK_MOUSE_IN);
        exitMouseIn.setFitHeight(30);
		exitMouseIn.setFitWidth(25);
        leader.getExitButton().setGraphic(exitMouseIn);
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
        leader.getExitButton().setGraphic(exitMouseOut);
    }
}
