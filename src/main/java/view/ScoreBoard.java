package view;

import model.Actor;
import controller.ScoreBoardController;
import model.BackgroundImage;

import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

public class ScoreBoard extends BorderPane{

    private Button btn_continue;
    private ImageView continueBG;
    private Button btn_menu;
    private ImageView menuBG;
    private MainMenu mainMenu;

    public ScoreBoard(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        if (mainMenu.getCurrentLevel() == 2) {
            lastScore();
        }
        else{
            initialize();
        }
        // configure actions on button
        @SuppressWarnings("unused")
        ScoreBoardController scoreBoardController = new ScoreBoardController(this);
        }

    public void initialize() {
        this.setPrefSize(600, 800);
        // configure continue button shape
        Rectangle continueShape = new Rectangle();
        continueShape.setArcHeight(50);
		continueShape.setArcWidth(50);
		continueShape.setHeight(100);
		continueShape.setWidth(200);
		continueShape.setStrokeWidth(10);
        // configure continue button background
        continueBG = new ImageView(new Image("file:media/images/pngegg.png"));
        continueBG.setFitHeight(100);
        continueBG.setPreserveRatio(true);

        btn_continue = new Button();
        btn_continue.setGraphic(continueBG);
        btn_continue.setShape(continueShape);
        btn_continue.setPrefSize(200, 100);
        
        // configure menu button shape
        Rectangle menuShape = new Rectangle();
        menuShape.setArcHeight(50);
		menuShape.setArcWidth(50);
		menuShape.setHeight(100);
		menuShape.setWidth(200);
		menuShape.setStrokeWidth(10);
        // configure menu button background
        menuBG = new ImageView(new Image("file:media/images/pngegg.png"));
        menuBG.setFitHeight(100);
        menuBG.setPreserveRatio(true);

        btn_menu = new Button();
        btn_menu.setGraphic(menuBG);
        btn_menu.setShape(menuShape);
        btn_menu.setPrefSize(200, 100);
        
        add(new BackgroundImage("file:media/images/.png"));
        setLeft(btn_continue);
        setRight(btn_menu);
        setMargin(btn_continue, new Insets(500, 0, 0, 50));
        setMargin(btn_menu, new Insets(500, 50, 0, 0));
    }
    
    public void lastScore() {
        this.setPrefSize(600, 800);
        // configure menu button shape
        Rectangle menuShape = new Rectangle();
        menuShape.setArcHeight(50);
		menuShape.setArcWidth(50);
		menuShape.setHeight(100);
		menuShape.setWidth(200);
		menuShape.setStrokeWidth(10);
        // configure menu button background
        menuBG = new ImageView(new Image("file:media/images/pngegg.png"));
        menuBG.setFitHeight(100);
        menuBG.setPreserveRatio(true);

        btn_menu = new Button();
        btn_menu.setGraphic(menuBG);
        btn_menu.setShape(menuShape);
        btn_menu.setPrefSize(200, 100);
        
        add(new BackgroundImage("file:media/images/.png"));
        setCenter(btn_menu);
        setMargin(btn_menu, new Insets(300, 0, 0, 0));
    }

	public void add(Actor actor) {
        getChildren().add(actor);
    }

    public Button getContinueButton() {
        return this.btn_continue;
    }

    public Button getMenuButton() {
        return this.btn_menu;
    }

    public ImageView getContinueBG() {
        return this.continueBG;
    }

    public ImageView getMenuBG() {
        return this.menuBG;
    }

    public MainMenu getMainMenu() {
        return this.mainMenu;
    }
}