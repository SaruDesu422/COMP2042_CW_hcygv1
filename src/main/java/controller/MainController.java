package controller;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import view.App;
import view.frames.Level;
import view.frames.MainMenu;

public class MainController {
    
    private MainMenu mainmenu;
    private App app;
    Level level;

    /**
    * This method controls the start and info buttons in the
    * main menu pane.
	*
	*/
    public MainController(MainMenu mainmenu) {
        this.mainmenu = mainmenu;
        this.app = mainmenu.getApp();
        mainmenu.getStartButton().setOnAction(this::handleButtonStart);
        mainmenu.getStartButton().addEventHandler(MouseEvent.MOUSE_ENTERED, this::handleButtonStartMouseIn);
        mainmenu.getStartButton().addEventHandler(MouseEvent.MOUSE_EXITED, this::handleButtonStartMouseOut);
        mainmenu.getInfoButton().setOnAction(this::handleButtonInfo);
        mainmenu.getInfoButton().addEventHandler(MouseEvent.MOUSE_ENTERED, this::handleButtonInfoMouseIn);
        mainmenu.getInfoButton().addEventHandler(MouseEvent.MOUSE_EXITED, this::handleButtonInfoMouseOut);
    }

    /**
    * This method redirects current stage to first level pane.
    *
    * @see     first level scene is loaded
	*/
    private void handleButtonStart(ActionEvent event) {
        this.level = new Level();
        Scene levelScene = new Scene(level.getCurrentStage(), 600, 800);
        app.getPrimaryStage().setScene(levelScene);
        app.getPrimaryStage().show();
    }
    
    /**
    * This method redirects current stage to info pane.
    *
    * @see     info scene is loaded
	*/
    private void handleButtonInfo(ActionEvent event) {
        // go to info page
    }

    /**
    * This method sets the image of the start button when the cursor is 
    * hovering over it.
    *
    * @see     image of start button
	*/
    private void handleButtonStartMouseIn(MouseEvent event) {
        ImageView startMouseIn = new ImageView(new Image("file:src/main/java/view/images/download.png"));
		startMouseIn.setFitHeight(100);
		startMouseIn.setPreserveRatio(true);
        mainmenu.getStartButton().setGraphic(startMouseIn);
    }

    /**
    * This method sets the image of the start button when the cursor is not
    * hovering over it anymore.
    *
    * @see     image of start button
	*/
    private void handleButtonStartMouseOut(MouseEvent event) {
        ImageView startMouseOut = mainmenu.getStartBG();
        mainmenu.getStartButton().setGraphic(startMouseOut);
    }

    /**
    * This method sets the image of the info button when the cursor is 
    * hovering over it.
    *
    * @see     image of info button
	*/
    private void handleButtonInfoMouseIn(MouseEvent event) {
        ImageView infoMouseIn = new ImageView(new Image("file:src/main/java/view/images/info.png"));
        infoMouseIn.setFitHeight(30);
		infoMouseIn.setFitWidth(30);
        mainmenu.getInfoButton().setGraphic(infoMouseIn);
    }
    
    /**
    * This method sets the image of the info button when the cursor is not
    * hovering over it anymore.
    *
    * @see     image of info button
	*/
    private void handleButtonInfoMouseOut(MouseEvent event) {
        ImageView infoMouseOut = mainmenu.getInfoBG();
        mainmenu.getInfoButton().setGraphic(infoMouseOut);
    }
}
