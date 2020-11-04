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

	/**
	* This method calls the initialize method and main controller
	* for actions on the buttons.
    *
    * @param  app  application
	*/
    public MainMenu(App app) {
		this.app = app;
		initialize();
		/* configure actions on buttons */
		MainController mainController = new MainController(this);
	}

	/**
	* This method initializes the positions of the buttons
	* and the background of the main menu pane.
    *
    * @see     Main menu pane
	*/
	public void initialize() {
		this.setPrefSize(600, 800);
		/* configure start button shape */
		Rectangle start = new Rectangle();
		start.setArcHeight(50);
		start.setArcWidth(50);
		start.setHeight(100);
		start.setWidth(200);
		start.setStrokeWidth(10);
		/* configure start button background */
		startBG = new ImageView(new Image("file:src/main/java/view/images/pngegg.png"));
		startBG.setFitHeight(100);
		startBG.setPreserveRatio(true);

		btn_start = new Button();
		btn_start.setGraphic(startBG);
		btn_start.setShape(start);
		btn_start.setPrefSize(200, 100);

		/* configure info button shape */
		Circle circle = new Circle();
		circle.setRadius(15);
		circle.setStrokeWidth(5);
		/* configure info button background */
		infoBG = new ImageView(new Image("file:src/main/java/view/images/info.png"));
		infoBG.setFitHeight(30);
		infoBG.setFitWidth(30);
		
		btn_info = new Button();
		btn_info.setGraphic(infoBG);
		btn_info.setShape(circle);
		btn_info.setPrefSize(30, 30);
		/* configure position of buttons and background */
		add(new BackgroundImage("file:src/main/java/view/images/iKogsKW.png"));
		setCenter(btn_start);
		setTop(btn_info);
		setAlignment(btn_info, Pos.TOP_RIGHT);
		setMargin(btn_info, new Insets(25,25,25,25));
		setMargin(btn_start, new Insets(300,0,0,0));
	}
	
	/**
	* This method adds actor to pane.
	*
    * @see     Actor
	*/
	public void add(Actor actor) {
        getChildren().add(actor);
    }

	/**
	* This method returns configurations of start button.
	*
    * @return  Start button
	*/
    public Button getStartButton() {
		return this.btn_start;
	}

	/**
	* This method returns configurations of info button.
	*
    * @return  Info button
	*/
	public Button getInfoButton() {
		return this.btn_info;
	}

	/**
	* This method returns current start button background.
	*
    * @return  Start button background
	*/
	public ImageView getStartBG() {
		return this.startBG;
	}
	
	/**
	* This method returns current info button background.
	*
    * @return  Info button background
	*/
	public ImageView getInfoBG() {
		return this.infoBG;
	}

	/**
	* This method returns current application config.
	*
    * @return  Application
	*/
	public App getApp() {
		return this.app;
	}
}
