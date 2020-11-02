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

    private void handleButtonStart(ActionEvent event) {
        // go to level1
        this.level = new Level();
        Scene levelScene = new Scene(level.getCurrentStage(), 600, 800);
        app.getPrimaryStage().setScene(levelScene);
        app.getPrimaryStage().show();
    }
    
    private void handleButtonInfo(ActionEvent event) {
        // go to info page
    }

    private void handleButtonStartMouseIn(MouseEvent event) {
        // animate mouse in start
        ImageView startMouseIn = new ImageView(new Image("file:src/main/java/view/images/download.png"));
		startMouseIn.setFitHeight(100);
		startMouseIn.setPreserveRatio(true);
        mainmenu.getStartButton().setGraphic(startMouseIn);
    }

    private void handleButtonStartMouseOut(MouseEvent event) {
        // animate mouse out start
        ImageView startMouseOut = mainmenu.getStartBG();
        mainmenu.getStartButton().setGraphic(startMouseOut);
    }

    private void handleButtonInfoMouseIn(MouseEvent event) {
        // animate mouse in info
        ImageView infoMouseIn = new ImageView(new Image("file:src/main/java/view/images/info.png"));
        infoMouseIn.setFitHeight(30);
		infoMouseIn.setFitWidth(30);
        mainmenu.getInfoButton().setGraphic(infoMouseIn);
    }
    
    private void handleButtonInfoMouseOut(MouseEvent event) {
        // animate mouse out info
        ImageView infoMouseOut = mainmenu.getInfoBG();
        mainmenu.getInfoButton().setGraphic(infoMouseOut);
    }
}
