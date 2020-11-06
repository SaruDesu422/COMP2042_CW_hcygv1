package view;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;

public class App extends Application {
	
	AnimationTimer timer;
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
		primaryStage.show();

		showMainMenu();
	}

	public void showMainMenu() {
		primaryStage.setScene(new Scene(new MainMenu(this), 600, 800));
	}

	public void showScoreBoard(ScoreBoard scoreBoard) {
		primaryStage.setScene(scoreBoard.getScene());
	}

	public void changePage(Pane pane) {
		primaryStage.setScene(new Scene(pane, 600, 800));
	}
}
