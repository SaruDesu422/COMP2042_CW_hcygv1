package view;

import model.Actor;
import model.BackgroundImage;
import model.Digit;
import controller.ScoreBoardController;

import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.Scene;

public class ScoreBoard extends BorderPane{

    private MainMenu mainMenu;
    private Game game;
    private ScoreBoardController controller;

    private Scene scene;
    
    private Button btn_continue;
    private Button btn_menu;
    int level;
    int points;
   

    public ScoreBoard(MainMenu mainMenu, Game game) {
        this.mainMenu = mainMenu;
        this.game = game;
        this.scene = new Scene(this, 600, 800);
    }

    public void show(int level, int points) {
        this.setPrefSize(600, 800);
        this.level = level;
        this.points = points;
        if (level < 38) {
            // configure continue button shape
            Rectangle continueShape = new Rectangle();
            continueShape.setArcHeight(50);
            continueShape.setArcWidth(50);
            continueShape.setHeight(100);
            continueShape.setWidth(200);
            continueShape.setStrokeWidth(10);
            // configure continue button background
            ImageView continueBG = new ImageView(new Image("file:media/images/pngegg.png"));
            continueBG.setFitHeight(100);
            continueBG.setPreserveRatio(true);

            btn_continue = new Button();
            btn_continue.setGraphic(continueBG);
            btn_continue.setShape(continueShape);
            btn_continue.setPrefSize(200, 100);
        }
        
        // configure menu button shape
        Rectangle menuShape = new Rectangle();
        menuShape.setArcHeight(50);
		menuShape.setArcWidth(50);
		menuShape.setHeight(100);
		menuShape.setWidth(200);
		menuShape.setStrokeWidth(10);
        // configure menu button background
        ImageView menuBG = new ImageView(new Image("file:media/images/pngegg.png"));
        menuBG.setFitHeight(100);
        menuBG.setPreserveRatio(true);

        btn_menu = new Button();
        btn_menu.setGraphic(menuBG);
        btn_menu.setShape(menuShape);
        btn_menu.setPrefSize(200, 100);
        
		add(new BackgroundImage("file:media/images/background/scoreboardBackground.png"));

        setNumbers();
        this.controller = new ScoreBoardController(this, game);
        if (level < 38) {
            setLeft(btn_continue);
            setRight(btn_menu);
            setMargin(btn_continue, new Insets(500, 0, 0, 50));
            setMargin(btn_menu, new Insets(500, 50, 0, 0));

            btn_continue.setOnAction(controller::handleButtonContinue);
            btn_continue.addEventHandler(MouseEvent.MOUSE_ENTERED, controller::handleButtonContinueMouseIn);
            btn_continue.addEventHandler(MouseEvent.MOUSE_EXITED, controller::handleButtonContinueMouseOut);
        } else {
            setLeft(null);
            setRight(null);
            setCenter(btn_menu);
            setMargin(btn_menu, new Insets(300, 0, 0, 0));
        }
        btn_menu.setOnAction(controller::handleButtonMenu);
        btn_menu.addEventHandler(MouseEvent.MOUSE_ENTERED, controller::handleButtonMenuMouseIn);
        btn_menu.addEventHandler(MouseEvent.MOUSE_EXITED, controller::handleButtonMenuMouseOut);
    }

    private void setNumbers() {
        int shift = 0;
        int startPoints = 70;
        int startLevel = 70;
        int tempPoints = points;
        int tempLevel = level;
    	while (points > 0) {
            int k = 1;
            while (tempPoints > 0) {
                int d = tempPoints / 10;
                k = tempPoints - d * 10;
                tempPoints = d;
                startPoints += 30;
            }
            int d = points / 10;
            k = points - d * 10;
            points = d;
            add(new Digit(k, startPoints - shift, 200));
            shift += 30;
        }
        shift = 0;
    	while (level > 0) {
            int k = 1;
            while (tempLevel > 0) {
                int d = tempLevel / 10;
                k = tempLevel - d * 10;
                tempLevel = d;
                startLevel += 30;
            }
            int d = level / 10;
            k = level - d * 10;
            level = d;
            add(new Digit(k, startLevel - shift, 250));
            shift += 30;
    	}
    }

	public void add(Actor actor) {
        getChildren().add(actor);
    }

    public Scene getCurrentScene() {
        return this.scene;
    }

    public Button getContinueButton() {
        return this.btn_continue;
    }

    public Button getMenuButton() {
        return this.btn_menu;
    }

    public MainMenu getMainMenu() {
        return this.mainMenu;
    }
}
