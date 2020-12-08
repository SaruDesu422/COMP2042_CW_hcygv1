package view;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;

public class App extends Application {
	
	AnimationTimer timer;
	private Stage primaryStage;

	private MainMenu mainMenu;
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

		mainMenu = new MainMenu(this);
		showMainMenu();
	}

	/**
	* Change Scene of primary stage to MainMenu.
	*
	* @see		MainMenu
	*/
	public void showMainMenu() {
		primaryStage.setScene(mainMenu.getScene());
	}

	/**
	* Change Scene of primary stage to Scoreboard.
	* 
	* @param	scoreBoard
	* @see		ScoreBoard
	*/
	public void showScoreBoard(ScoreBoard scoreBoard) {
		primaryStage.setScene(scoreBoard.getScene());
	}

	/**
	* Change Scene of primary stage to Info.
	* 
	* @param	info
	* @see		info
	*/
	public void showInfo(Info info) {
		primaryStage.setScene(info.getScene());
	}

	/**
	* Change Scene of primary stage to LeaderBoard.
	* 
	* @param	leaderBoard
	* @see		leaderBoard
	*/
	public void showLeaderBoard(LeaderBoard leaderBoard) {
		primaryStage.setScene(leaderBoard.getScene());
	}

	/**
	* Change Scene of primary stage to a specific pane.
    *
	* @param	pane
	*/
	public void changePage(Pane pane) {
		primaryStage.setScene(new Scene(pane, 600, 800));
	}
}
