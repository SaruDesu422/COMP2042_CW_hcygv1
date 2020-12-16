package view;

import model.Actor;
import controller.InfoController;
import model.BackgroundImage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;

public class Info extends BorderPane {

	private Button btn_exit;
	private App app;

	protected Scene scene;

	private InfoController controller;

	/**
	 * Sets up the pane for the info pane to be shown on.
	 * 
	 * @param	app
	 * @see		App
	 */
    public Info(App app) {
        this.app = app;
		this.scene = new Scene(this, 600, 800);
        initialize();
	}

	/**
	 * Adds the buttons and background to the pane.
	 */
	public void initialize() {
		this.setPrefSize(600, 800);
		add(new BackgroundImage("infoBackground"));

		// Creates the exit button
		btn_exit = new Button();
		
		ImageView exitBG = new ImageView(new Image("file:media/images/buttons/back.png"));
		exitBG.setFitHeight(30);
		exitBG.setFitWidth(25);
		btn_exit.setGraphic(exitBG);

		Circle circle = new Circle();
		circle.setRadius(15);
		circle.setStrokeWidth(5);
		btn_exit.setShape(circle);
		btn_exit.setPrefSize(30, 30);

        // Configure exit button position 
		setTop(btn_exit);
		setAlignment(btn_exit, Pos.TOP_RIGHT);
		setMargin(btn_exit, new Insets(25,25,25,25));

		// Exit button controls 
		controller = new InfoController(this);
		btn_exit.setOnAction(controller::handleButtonExit);
        btn_exit.addEventHandler(MouseEvent.MOUSE_ENTERED, controller::handleButtonExitMouseIn);
        btn_exit.addEventHandler(MouseEvent.MOUSE_EXITED, controller::handleButtonExitMouseOut);
	}
	
	/**
	 * Adds actor to the current pane.
	 * 
	 * @param	actor
	 * @see		Actor
	 */
	public void add(Actor actor) {
        getChildren().add(actor);
    }

	/**
	 * Accessor: Button btn_ext
	 * 
	 * @return	btn_exit
	 */
	public Button getExitButton() {
		return this.btn_exit;
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
