package view;

import view.frames.MainMenu;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	AnimationTimer timer;
	MainMenu mainMenu;
	private Stage primaryStage;
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
		Scene menuscene = new Scene(mainMenu, 600, 800);
		primaryStage.setScene(menuscene);
		primaryStage.show();
	}

	public Stage getPrimaryStage() {
		return this.primaryStage;
	}
}
