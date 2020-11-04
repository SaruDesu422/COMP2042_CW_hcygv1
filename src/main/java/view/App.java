package view;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	AnimationTimer timer;
	MainMenu mainMenu;
	private Stage primaryStage;
	ScoreBoard scoreBoard;
	Scene menuscene;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Frogger");
		primaryStage.setHeight(800);
		primaryStage.setWidth(600);
		primaryStage.setResizable(false);

		mainMenu = new MainMenu(this);
		menuscene = new Scene(mainMenu, 600, 800);
		primaryStage.setScene(menuscene);

		// scoreBoard = new ScoreBoard(this);
		// Scene scorescene = new Scene(scoreBoard, 600, 800);
		// primaryStage.setScene(scorescene);
		primaryStage.show();
	}

	public Stage getPrimaryStage() {
		return this.primaryStage;
	}

	public Scene getMainScene() {
		return this.menuscene;
	}
}
