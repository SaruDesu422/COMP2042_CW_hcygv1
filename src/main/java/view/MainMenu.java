package view;

import model.Actor;
import controller.MainController;
import model.BackgroundImage;

import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class MainMenu extends BorderPane{

	private Button btn_start;
	private Button btn_info;
	private App app;

	private MainController controller;

	/**
	* This method calls the initialize method and main controller
	* for actions on the buttons.
    *
    * @param  app  application
	*/
    public MainMenu(App app) {
		this.app = app;
		initialize();
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
		ImageView startBG = new ImageView(new Image("file:media/images/pngegg.png"));
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
		ImageView infoBG = new ImageView(new Image("file:media/images/info.png"));
		infoBG.setFitHeight(30);
		infoBG.setFitWidth(30);
		
		btn_info = new Button();
		btn_info.setGraphic(infoBG);
		btn_info.setShape(circle);
		btn_info.setPrefSize(30, 30);
		/* configure position of buttons and background */
		add(new BackgroundImage("file:media/images/iKogsKW.png"));
		
		setCenter(btn_start);
		setTop(btn_info);
		setAlignment(btn_info, Pos.TOP_RIGHT);
		setMargin(btn_info, new Insets(25,25,25,25));
		setMargin(btn_start, new Insets(300,0,0,0));

		controller = new MainController(this);
		
		btn_start.setOnAction(controller::handleButtonStart);
        btn_start.addEventHandler(MouseEvent.MOUSE_ENTERED, controller::handleButtonStartMouseIn);
        btn_start.addEventHandler(MouseEvent.MOUSE_EXITED, controller::handleButtonStartMouseOut);
        btn_info.setOnAction(controller::handleButtonInfo);
        btn_info.addEventHandler(MouseEvent.MOUSE_ENTERED, controller::handleButtonInfoMouseIn);
        btn_info.addEventHandler(MouseEvent.MOUSE_EXITED, controller::handleButtonInfoMouseOut);
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
	* This method returns current application config.
	*
    * @return  Application
	*/
	public App getApp() {
		return this.app;
	}
}
