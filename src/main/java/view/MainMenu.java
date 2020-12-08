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
import javafx.scene.Scene;

public class MainMenu extends BorderPane{

	private Button btn_start;
	private Button btn_info;
	private App app;

	protected Scene scene;

	private MainController controller;

    /**
    * Sets up the pane for the main menu pane to be shown on.
    * <pre>
    * Methods:<br>initialize()
    * </pre>
    *
	* @param    app
	* @see		App
    */
    public MainMenu(App app) {
		this.app = app;
		this.scene = new Scene(this, 600, 800);
		initialize();
	}

	/**
	* Adds the buttons and backgrounds to the pane.
    *
	*/
	public void initialize() {
		this.setPrefSize(600, 800);
		add(new BackgroundImage("menuBackground"));

		/* Create a start button */
		btn_start = new Button();

		ImageView startBG = new ImageView(new Image("file:media/images/buttons/start.png"));
		startBG.setFitHeight(100);
		startBG.setPreserveRatio(true);
		btn_start.setGraphic(startBG);

		Rectangle start = new Rectangle();
		start.setArcHeight(50);
		start.setArcWidth(50);
		start.setHeight(100);
		start.setWidth(200);
		start.setStrokeWidth(10);
		btn_start.setShape(start);
		btn_start.setPrefSize(200, 100);

		/* Create an info button */
		btn_info = new Button();

		ImageView infoBG = new ImageView(new Image("file:media/images/buttons/info.png"));
		infoBG.setFitHeight(30);
		infoBG.setFitWidth(25);
		btn_info.setGraphic(infoBG);

		Circle circle = new Circle();
		circle.setRadius(15);
		btn_info.setShape(circle);
		btn_info.setPrefSize(30, 30);

		/** Configure start and info button position */
		setCenter(btn_start);
		setTop(btn_info);
		setAlignment(btn_info, Pos.TOP_RIGHT);
		setMargin(btn_info, new Insets(25, 25, 25, 25));
		setMargin(btn_start, new Insets(300, 0, 0, 0));

		/** Start and info button controls */
		controller = new MainController(this);
		btn_start.setOnAction(controller::handleButtonStart);
        btn_start.addEventHandler(MouseEvent.MOUSE_ENTERED, controller::handleButtonStartMouseIn);
        btn_start.addEventHandler(MouseEvent.MOUSE_EXITED, controller::handleButtonStartMouseOut);
        btn_info.setOnAction(controller::handleButtonInfo);
        btn_info.addEventHandler(MouseEvent.MOUSE_ENTERED, controller::handleButtonInfoMouseIn);
        btn_info.addEventHandler(MouseEvent.MOUSE_EXITED, controller::handleButtonInfoMouseOut);
	}
	
	/**
	* Adds actor to current pane.
	*
	* @param	actor
    * @see		Actor
	*/
	public void add(Actor actor) {
        getChildren().add(actor);
	}

	/**
	* Accessor: Button btn_start
	*
    * @return	btn_start
	*/
    public Button getStartButton() {
		return this.btn_start;
	}

	/**
	* Accessor: Button btn_info
	*
    * @return	btn_info
	*/
	public Button getInfoButton() {
		return this.btn_info;
	}

	/**
	* Accessor: App app
	*
    * @return	app
	*/
	public App getApp() {
		return this.app;
	}
}
