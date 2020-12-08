package view;

import model.Actor;
import model.BackgroundImage;
import model.Digit;
import controller.ScoreBoardController;
import javafx.scene.control.Alert;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard extends BorderPane {

    private MainMenu mainMenu;
    private Game game;
    private ScoreBoardController controller;

    protected Scene scene;

    public final int MAXLEVEL = 10;
    private final int MAXSCORESTORAGE = 10;
    private final String COMMA_DELIMITER = ",";
    private Button btn_continue;
    private Button btn_menu;
    private int highscore;
    private List<String[]> highScoreList;

    /**
     * Sets up the pane for the scoreboard pane to be shown on.
     * 
     * <pre>
     * Methods:<br>show(int level, int points)<br>setNumbers(int temp, int val, int y)<br>updateScoreSheet(int level, int points)
     * </pre>
     *
     * @param mainMenu
     * @param game
     * @see MainMenu
     * @see Game
     */
    public ScoreBoard(MainMenu mainMenu, Game game) {
        this.mainMenu = mainMenu;
        this.game = game;
        this.scene = new Scene(this, 600, 800);
        this.highScoreList = readData();
    }

    /**
     * ScoreBoard has 2 types of characteristic.
     * <p>
     * - Continue Button & MainMenu Button
     * <p>
     * - Only MainMenu Button
     *
     * @param level  scoreboard characteristic depends on current level
     * @param points show points on scoreboard
     */
    public void show(int level, int points) {
        this.setPrefSize(600, 800);
        game.stop();
        add(new BackgroundImage("scoreboardBackground"));
//        System.out.println("Before Update:");
//        for (int i = 0; i < MAXLEVEL; i++) {
//            for (int j = 0; j < MAXSCORESTORAGE; j++) {
//                System.out.print(highScoreList.get(i)[j] + ",");
//            }
//            System.out.println();
//        }
        highScoreList.set(level - 1, updateData(level, points, highScoreList.get(level - 1)));
        System.out.println("After Update:");
//        for (int i = 0; i < MAXLEVEL; i++) {
//            for (int j = 0; j < MAXSCORESTORAGE; j++) {
//                System.out.print(highScoreList.get(i)[j] + ",");
//            }
//            System.out.println();
//        }
        // updateData(level, points, highScoreList); 
        highscore = Integer.valueOf(highScoreList.get(level - 1)[0]);
        writeData(highScoreList);
        // go to the method, the writeData method
        if (level < MAXLEVEL) {
            /** Create a continue button */
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
        showLeaderBoard(level, points);
    }

    /**
    * Create a new alert to show top 10 highscores stored.
    * 
    * @param    level
    * @param    points
    */
    private void showLeaderBoard(int level, int points) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String scores = "History:\n";
        alert.setTitle("Frogger Leader Board");
        alert.setHeaderText("Level " + level + " Score: " + points);
        for (int i = 0; i < MAXSCORESTORAGE; i++)
            scores += highScoreList.get(level - 1)[i] + "\n";
        alert.setContentText(scores);
        alert.show();
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
        if (val == 0)
            add(new Digit(0, start + 30, y));
        else {
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
    }

    /**
    * Reads the highscore file and store in a list of array of string.
	*
    * @return   highScoreList
	*/
    public List<String[]> readData() { 
        String file = "data/highscore.csv";
        List<String[]> highScoreList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = "";
            if ((line = reader.readLine()) != null) {
                while ((line = reader.readLine()) != null)
                    highScoreList.add(line.split(","));
                int diff = MAXLEVEL - highScoreList.size();
                while (diff > 0) {
                    String[] emptyFile = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
                    diff--;
                    highScoreList.add(emptyFile);
                }
            } else {
                for (int i = 0; i < MAXLEVEL; i++) {
                    String[] emptyFile = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
                    highScoreList.add(emptyFile);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highScoreList;
    }

    /**
    * Update the array of String according to level and points, and sorts it
    * in an ascending order.
    *
    * @param    level
    * @param    points
    * @param    levelHighScoreList
    * @return   levelHighScoreList
	*/
    public String[] updateData(int level, int points, String[] levelHighScoreList) {
        int index = MAXSCORESTORAGE - 1;
        String temp;
        if (points > Integer.valueOf(levelHighScoreList[index])){
            levelHighScoreList[index] = Integer.toString(points);
            while (index > 0) {
                if (Integer.valueOf(levelHighScoreList[index]) > Integer.valueOf(levelHighScoreList[index - 1])) {
                    temp = levelHighScoreList[index - 1];
                    levelHighScoreList[index - 1] = levelHighScoreList[index];
                    levelHighScoreList[index] = temp;
                    index--;
                } else {
                    break;
                }
            }
        }
        return levelHighScoreList;
    }
  
    /**
    * Overwrites the designated csv file with the highScoreList array.
	*
    * @param   highScoreList
	*/  
    public void writeData(List<String[]> highScoreList) {
        String file = "data/highscore.csv";
        try {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(file, false));
            PrintWriter pWriter = new PrintWriter(bWriter);
            for (int i = 0; i < MAXLEVEL; i++) {
                for (int j = 0; j < MAXSCORESTORAGE; j++) {
                    pWriter.print(highScoreList.get(i)[j]);
                    if (j != MAXSCORESTORAGE - 1)
                        pWriter.print(COMMA_DELIMITER);
                }
                bWriter.newLine();
            }
            pWriter.close();
            bWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
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
