package view;

import model.Actor;
import model.BackgroundImage;
import model.Digit;
import controller.ScoreBoardController;

import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.Scene;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ScoreBoard extends BorderPane{

	private MainMenu mainMenu;
    private Game game;
    private ScoreBoardController controller;

    protected Scene scene;
    
    private final int MAXLEVEL = 10;
    private final String COMMA_DELIMITER = ",";
    private Button btn_continue;
    private Button btn_menu;
    
    /**
    * Sets up the pane for the scoreboard pane to be shown on.
    * <pre>
    * Methods:<br>show(int level, int points)<br>setNumbers(int temp, int val, int y)<br>updateScoreSheet(int level, int points)
    * </pre>
    *
    * @param    mainMenu
    * @param    game
    * @see      MainMenu
    * @see      Game
    */
    public ScoreBoard(MainMenu mainMenu, Game game) {
        this.mainMenu = mainMenu;
        this.game = game;
        this.scene = new Scene(this, 600, 800);
    }

    /**
    * ScoreBoard has 2 types of characteristic.
    * 1 - Continue Button & MainMenu Button
    * 2 - only MainMenu Button
    *
    * @param    level   scoreboard characteristic depends on current level
    * @param    points  show points on scoreboard
	*/
    public void show(int level, int points) {
        this.setPrefSize(600, 800);
		add(new BackgroundImage("scoreboardBackground"));
        int highscore = Integer.valueOf(updateScoreSheet(level, points)[level - 1]);

        /** Create a continue button */
        if (level < MAXLEVEL) {
            btn_continue = new Button();

            ImageView continueBG = new ImageView(new Image("file:media/images/buttons/continue.png"));
            continueBG.setFitHeight(100);
            continueBG.setPreserveRatio(true);
            btn_continue.setGraphic(continueBG);

            Rectangle continueShape = new Rectangle();
            continueShape.setArcHeight(50);
            continueShape.setArcWidth(50);
            continueShape.setHeight(100);
            continueShape.setWidth(200);
            continueShape.setStrokeWidth(10);
            btn_continue.setShape(continueShape);
            btn_continue.setPrefSize(200, 100);
        }
        
        /** Create a menu button */
        btn_menu = new Button();

        ImageView menuBG = new ImageView(new Image("file:media/images/buttons/mainmenu.png"));
        menuBG.setFitHeight(100);
        menuBG.setPreserveRatio(true);
        btn_menu.setGraphic(menuBG);

        Rectangle menuShape = new Rectangle();
        menuShape.setArcHeight(50);
		menuShape.setArcWidth(50);
		menuShape.setHeight(100);
		menuShape.setWidth(200);
        menuShape.setStrokeWidth(10);
        btn_menu.setShape(menuShape);
        btn_menu.setPrefSize(200, 100);

        /** Show highscore, score and level */
        setNumbers(highscore, highscore, 168);
        setNumbers(points, points, 227);
        setNumbers(level, level, 286);

        /** Buttons controls and positions */
        this.controller = new ScoreBoardController(this, game);
        if (level < MAXLEVEL) {
            setLeft(btn_continue);
            setRight(btn_menu);
            setMargin(btn_continue, new Insets(550, 0, 0, 25));
            setMargin(btn_menu, new Insets(550, 25, 0, 0));

            btn_continue.setOnAction(controller::handleButtonContinue);
            btn_continue.addEventHandler(MouseEvent.MOUSE_ENTERED, controller::handleButtonContinueMouseIn);
            btn_continue.addEventHandler(MouseEvent.MOUSE_EXITED, controller::handleButtonContinueMouseOut);
        } else {
            setLeft(null);
            setRight(null);
            setCenter(btn_menu);
            setMargin(btn_menu, new Insets(450, 0, 0, 0));
        }
        btn_menu.setOnAction(controller::handleButtonMenu);
        btn_menu.addEventHandler(MouseEvent.MOUSE_ENTERED, controller::handleButtonMenuMouseIn);
        btn_menu.addEventHandler(MouseEvent.MOUSE_EXITED, controller::handleButtonMenuMouseOut);
    }
    
    /**
    * Sets every digits to a new digit object.
    * 
    * @param    temp
    * @param    val
    * @param    y
    */
    private void setNumbers(int temp, int val, int y) {
        int shift = 0;
        int start = 265;
        while (val > 0) {
            int k = 1;
            while (temp > 0) {
                int d = temp / 10;
                k = temp - d * 10;
                temp = d;
                start += 30;
            }
            int d = val / 10;
            k = val - d * 10;
            val = d;
            add(new Digit(k, start - shift, y));
            shift += 30;
        }
    }

	/**
    * Reads the highscore file and copy the data into an array. Then
    * updates the array with the new highscore and overwrite the file
    * with the data in the array.
	*
    * @param    level
    * @param    points
	*/
    private String[] updateScoreSheet(int level, int points) {
        String[] highscoreInfo = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/highscore.csv"));
            String line;

            if ((line = br.readLine()) != null) {
                highscoreInfo = line.split(COMMA_DELIMITER);
                if (Integer.valueOf(highscoreInfo[level - 1]) < points)
                    highscoreInfo[level - 1] = Integer.toString(points);

                BufferedWriter bw = new BufferedWriter(new FileWriter("data/highscore.csv", false));
                PrintWriter pw = new PrintWriter(bw);
                for (int i = 0; i < highscoreInfo.length; i++) {
                    pw.print(highscoreInfo[i] + COMMA_DELIMITER);
                }
                bw.close();
                pw.close();
            } else {
                BufferedWriter bw = new BufferedWriter(new FileWriter("data/highscore.csv", false));
                PrintWriter pw = new PrintWriter(bw);
                for (int i = 0; i < MAXLEVEL; i++) {
                    pw.print("0" + COMMA_DELIMITER);
                }
                bw.close();
                pw.close();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return highscoreInfo;
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
	* Accessor: Button btn_continue
	*
    * @param    btn_continue
	*/
    public Button getContinueButton() {
        return this.btn_continue;
    }

	/**
	* Accessor: Button btn_menu
	*
    * @param    btn_menu
	*/
    public Button getMenuButton() {
        return this.btn_menu;
    }

	/**
	* Accessor: MainMenu mainMenu
	*
    * @param    mainMenu
	*/
    public MainMenu getMainMenu() {
        return this.mainMenu;
    }
}
