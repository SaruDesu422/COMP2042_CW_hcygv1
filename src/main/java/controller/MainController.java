package controller;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import view.App;
import view.Level;
import view.MainMenu;

public class MainController {
    
    private MainMenu mainMenu;
    private App app;
    Level level;

    /**
    * This method controls the start and info buttons in the
    * main menu pane.
	*
	*/
    public MainController(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        this.app = mainMenu.getApp();
        mainMenu.getStartButton().setOnAction(this::handleButtonStart);
        mainMenu.getStartButton().addEventHandler(MouseEvent.MOUSE_ENTERED, this::handleButtonStartMouseIn);
        mainMenu.getStartButton().addEventHandler(MouseEvent.MOUSE_EXITED, this::handleButtonStartMouseOut);
        mainMenu.getInfoButton().setOnAction(this::handleButtonInfo);
        mainMenu.getInfoButton().addEventHandler(MouseEvent.MOUSE_ENTERED, this::handleButtonInfoMouseIn);
        mainMenu.getInfoButton().addEventHandler(MouseEvent.MOUSE_EXITED, this::handleButtonInfoMouseOut);
    }

    /**
    * This method redirects current stage to first level pane.
    *
    * @see     first level scene is loaded
	*/
    private void handleButtonStart(ActionEvent event) {
        this.level = new Level(mainMenu, 1);
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
        ImageView startMouseIn = new ImageView(new Image("file:media/images/download.png"));
		startMouseIn.setFitHeight(100);
		startMouseIn.setPreserveRatio(true);
        mainMenu.getStartButton().setGraphic(startMouseIn);
    }

    /**
    * This method sets the image of the start button when the cursor is not
    * hovering over it anymore.
    *
    * @see     image of start button
	*/
    private void handleButtonStartMouseOut(MouseEvent event) {
        ImageView startMouseOut = mainMenu.getStartBG();
        mainMenu.getStartButton().setGraphic(startMouseOut);
    }

    /**
    * This method sets the image of the info button when the cursor is 
    * hovering over it.
    *
    * @see     image of info button
	*/
    private void handleButtonInfoMouseIn(MouseEvent event) {
        ImageView infoMouseIn = new ImageView(new Image("file:media/images/info.png"));
        infoMouseIn.setFitHeight(30);
		infoMouseIn.setFitWidth(30);
        mainMenu.getInfoButton().setGraphic(infoMouseIn);
    }
    
    /**
    * This method sets the image of the info button when the cursor is not
    * hovering over it anymore.
    *
    * @see     image of info button
	*/
    private void handleButtonInfoMouseOut(MouseEvent event) {
        ImageView infoMouseOut = mainMenu.getInfoBG();
        mainMenu.getInfoButton().setGraphic(infoMouseOut);
    }
}
