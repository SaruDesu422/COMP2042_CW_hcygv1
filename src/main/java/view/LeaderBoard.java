package view;

import model.Actor;

import java.util.List;

import controller.LeaderController;
import model.BackgroundImage;
import model.Digit;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;

public class LeaderBoard extends BorderPane {

    private final int MAXSCORESTORAGE = 10;
    private Button btn_exit;
    private App app;
    private ScoreBoard scoreBoard;
    private LeaderController controller;
    private List<String[]> highScoreList;

	protected Scene scene;

    /**
    * Sets up the pane for the leaderboard pane, showing the
    * top 10 highest scores stored.
    * <pre>
    * Methods:<br>initialize()
    * </pre>
    *
	* @param    app
	* @see		App
    */
    public LeaderBoard(App app, ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
        this.app = app;
		this.scene = new Scene(this, 600, 800);
        initialize();
    }

	/**
	* Adds the buttons, background and digits to the pane.
    *
	*/
    public void initialize() {
        this.highScoreList = scoreBoard.getHighScoreList();
        this.setPrefSize(600, 800);
        add(new BackgroundImage("leaderBackground"));
        int level = scoreBoard.getLevel();
        int playerScore = scoreBoard.getPoints();
        int highScore = 0;

        setNumbers(playerScore, playerScore, 116, 256);

        for (int i = 0, y = 207; i < MAXSCORESTORAGE; i++, y += 49) {
            highScore = Integer.valueOf(highScoreList.get(level - 1)[i]);
            setNumbers(highScore, highScore, y, 190);
        }
        
        /** Creates the exit button */
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

        /** Configure exit button position */
		setTop(btn_exit);
		setAlignment(btn_exit, Pos.TOP_RIGHT);
        setMargin(btn_exit, new Insets(15, 15, 0, 0));
        
        /** Exit button controls */
        controller = new LeaderController(this);
        btn_exit.setOnAction(controller::handleButtonExit);
        btn_exit.addEventHandler(MouseEvent.MOUSE_ENTERED, controller::handleButtonExitMouseIn);
        btn_exit.addEventHandler(MouseEvent.MOUSE_EXITED, controller::handleButtonExitMouseOut);
    }

    /**
    * Sets every digits to a new digit object.
    * 
    * @param    temp
    * @param    val
    * @param    y
    * @param    x
    */
    private void setNumbers(int temp, int val, int y, int x) {
        int shift = 0;
        if (val == 0)
            add(new Digit(0, x + 30, y));
        else {
            while (val > 0) {
                int k = 1;
                while (temp > 0) {
                    int d = temp / 10;
                    k = temp - d * 10;
                    temp = d;
                    x += 30;
                }
                int d = val / 10;
                k = val - d * 10;
                val = d;
                add(new Digit(k, x - shift, y));
                shift += 30;
            }
        }
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
	* Accessor: Button btn_exit
	*
    * @return  btn_exit
	*/
	public Button getExitButton() {
		return this.btn_exit;
	}

	/**
	* Accessor: App app
	*
    * @return  app
	*/
	public App getApp() {
		return this.app;
    }

	/**
	* Accessor: ScoreBoards scoreBoard
	*
    * @return  scoreBoard
	*/
	public ScoreBoard getScoreBoard() {
		return this.scoreBoard;
    }
}
