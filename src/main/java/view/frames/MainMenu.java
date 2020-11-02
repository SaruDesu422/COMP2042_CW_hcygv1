package view.frames;

import model.Actor;
import controller.MainController;
import model.BackgroundImage;
import view.App;

import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class MainMenu extends BorderPane{

	private Button btn_start;
	private ImageView startBG;
	private Button btn_info;
	private ImageView infoBG;
	private App app;

    public MainMenu(App app) {
		this.app = app;
		initialize();
		// configure actions on buttons
		MainController mainController = new MainController(this);
	}

	public void initialize() {
		this.setPrefSize(600, 800);
		// configure start button shape
		Rectangle start = new Rectangle();
		start.setArcHeight(50);
		start.setArcWidth(50);
		start.setHeight(100);
		start.setWidth(200);
		start.setStrokeWidth(10);
		// configure start button background
		startBG = new ImageView(new Image("file:src/main/java/view/images/pngegg.png"));
		startBG.setFitHeight(100);
		startBG.setPreserveRatio(true);

		btn_start = new Button();
		btn_start.setGraphic(startBG);
		btn_start.setStyle("-fx-border-color: black");
		btn_start.setShape(start);
		btn_start.setPrefSize(200, 100);

		// configure info button shape
		Circle circle = new Circle();
		circle.setRadius(15);
		circle.setStrokeWidth(5);
		// configure info button background
		infoBG = new ImageView(new Image("file:src/main/java/view/images/info.png"));
		infoBG.setFitHeight(30);
		infoBG.setFitWidth(30);
		
		btn_info = new Button();
		btn_info.setGraphic(infoBG);
		btn_info.setStyle("-fx-border-color: black");
		btn_info.setShape(circle);
		btn_info.setPrefSize(30, 30);
		// configure position of buttons and background
		add(new BackgroundImage("file:src/main/java/view/images/iKogsKW.png"));
		this.setCenter(btn_start);
		this.setTop(btn_info);
		BorderPane.setAlignment(btn_info, Pos.TOP_RIGHT);
		BorderPane.setMargin(btn_info, new Insets(25,25,25,25));
		BorderPane.setMargin(btn_start, new Insets(300,0,0,0));
	}
	
	public void add(Actor actor) {
        getChildren().add(actor);
    }
    
    public void remove(Actor actor) {
        getChildren().remove(actor);
    }

    public Button getStartButton() {
		return this.btn_start;
	}

	public Button getInfoButton() {
		return this.btn_info;
	}

	public ImageView getStartBG() {
		return this.startBG;
	}
	
	public ImageView getInfoBG() {
		return this.infoBG;
	}

	public App getApp() {
		return this.app;
	}
}
