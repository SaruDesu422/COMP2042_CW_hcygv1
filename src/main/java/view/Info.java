package view;

import model.Actor;
import controller.InfoController;
import model.BackgroundImage;

import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;

public class Info extends BorderPane{

	private Button btn_exit;
	private App app;

	private InfoController controller;

	/**
	* This method calls the initialize method and main controller
	* for actions on the buttons.
    *
    * @param  app  application
	*/
    public Info(App app) {
        this.app = app;
        initialize();
        controller = new InfoController(this);
	}

	public void initialize() {
		this.setPrefSize(600, 800);
		Circle circle = new Circle();
		circle.setRadius(15);
		circle.setStrokeWidth(5);
		/* configure info button background */
		ImageView exitBG = new ImageView(new Image("file:media/images/info.png"));
		exitBG.setFitHeight(30);
		exitBG.setFitWidth(30);

		btn_exit = new Button();
		btn_exit.setGraphic(exitBG);
		btn_exit.setShape(circle);
		btn_exit.setPrefSize(30, 30);

		add(new BackgroundImage("file:media/images/.png"));
		setTop(btn_exit);
		setAlignment(btn_exit, Pos.TOP_LEFT);
		setMargin(btn_exit, new Insets(25,25,25,25));

		controller = new InfoController(this);

		btn_exit.setOnAction(controller::handleButtonExit);
        btn_exit.addEventHandler(MouseEvent.MOUSE_ENTERED, controller::handleButtonExitMouseIn);
        btn_exit.addEventHandler(MouseEvent.MOUSE_EXITED, controller::handleButtonExitMouseOut);
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
	* This method returns configurations of info button.
	*
    * @return  Info button
	*/
	public Button getExitButton() {
		return this.btn_exit;
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
