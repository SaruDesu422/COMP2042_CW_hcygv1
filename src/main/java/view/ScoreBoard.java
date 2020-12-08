package view;

import model.Actor;
import model.BackgroundImage;
import model.Digit;
import controller.ScoreBoardController;

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
    private Button btn_leader;
    private int highscore;
    private int level;
    private int points;
    private List<String[]> highScoreList;

    /**
     * Sets up the pane for the ScoreBoard page to be shown on.
     * 
     * @param   mainMenu
     * @param   game
     * @see     MainMenu
     * @see     Game
     */
    public ScoreBoard(MainMenu mainMenu, Game game) {
        this.mainMenu = mainMenu;
        this.game = game;
        this.scene = new Scene(this, 600, 800);
        this.highScoreList = readData();
    }

    /**
     * ScoreBoard shows the continue button for all levels except
     * for the last level. Mainmenu button is always on the page.
     * 
     * @param   level
     * @param   points
     */
    public void show(int level, int points) {
        this.setPrefSize(600, 800);
        this.level = level;
        this.points = points;
        game.stop();
        add(new BackgroundImage("scoreboardBackground"));

        // update high scores in file
        highScoreList.set(level - 1, updateData(level, points, highScoreList.get(level - 1)));
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

        /** Create a leaderboard button */
        btn_leader = new Button();

        ImageView leaderBG = new ImageView(new Image("file:media/images/buttons/leader.png"));
        leaderBG.setFitHeight(30);
        leaderBG.setFitWidth(25);
        btn_leader.setGraphic(leaderBG);

        Circle circle = new Circle();
        circle.setRadius(15);
        btn_leader.setShape(circle);
        btn_leader.setPrefSize(30, 30);

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
        setTop(btn_leader);
        setAlignment(btn_leader, Pos.TOP_RIGHT);
        setMargin(btn_leader, new Insets(15, 15, 0, 0));

        btn_menu.setOnAction(controller::handleButtonMenu);
        btn_menu.addEventHandler(MouseEvent.MOUSE_ENTERED, controller::handleButtonMenuMouseIn);
        btn_menu.addEventHandler(MouseEvent.MOUSE_EXITED, controller::handleButtonMenuMouseOut);
        btn_leader.setOnAction(controller::handleButtonLeader);
        btn_leader.addEventHandler(MouseEvent.MOUSE_ENTERED, controller::handleButtonLeaderMouseIn);
        btn_leader.addEventHandler(MouseEvent.MOUSE_EXITED, controller::handleButtonLeaderMouseOut);
    }

    /**
     * Sets every digits to a new Digit object.
     * 
     * @param   temp
     * @param   val
     * @param   y
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
     * @return  highScoreList
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
                    String[] emptyLine = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
                    highScoreList.add(emptyLine);
                    diff--;
                }
            } else {
                for (int i = 0; i < MAXLEVEL; i++) {
                    String[] emptyLine = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
                    highScoreList.add(emptyLine);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highScoreList;
    }

    /**
     * Update the array of String according to level and points, and sort it
     * in an ascending order.
     * 
     * @param   level
     * @param   points
     * @param   levelHighScoreList
     * @return  levelHighScoreList
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
     * Overwrites the designated csv file with the highScoreList list.
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
     * Adds actor to the current pane.
     * 
     * @param   actor
     * @see     Actor
     */
	public void add(Actor actor) {
        getChildren().add(actor);
    }
    
    /**
     * Accessor: int level
     * 
     * @return  level
     */
    public int getLevel() {
        return this.level;
    }
    
    /**
     * Accessor: int points
     * 
     * @return  points
     */
    public int getPoints() {
        return this.points;
    }
    
    /**
     * Accessor: List of String[] highScoreList
     * 
     * @return  highScoreList
     */
    public List<String[]> getHighScoreList() {
        return this.highScoreList;
    }
    
    /**
     * Accessor: Button btn_continue
     * 
     * @return  btn_continue
     */
    public Button getContinueButton() {
        return this.btn_continue;
    }

    /**
     * Accessor: Button btn_menu
     * 
     * @return  btn_menu
     */
    public Button getMenuButton() {
        return this.btn_menu;
    }

    /**
     * Accessor: Button btn_leader
     * 
     * @return  btn_leader
     */
    public Button getLeaderButton() {
        return this.btn_leader;
    }

    /**
     * Accessor: MainMenu mainMenu
     * 
     * @return  mainMenu
     */
    public MainMenu getMainMenu() {
        return this.mainMenu;
    }
}
