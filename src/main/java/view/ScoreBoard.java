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

    private Scene scene;
    
    private final int MAXLEVEL = 10;
    private final String COMMA_DELIMITER = ",";
    private Button btn_continue;
    private Button btn_menu;
    
    

    public ScoreBoard(MainMenu mainMenu, Game game) {
        this.mainMenu = mainMenu;
        this.game = game;
        this.scene = new Scene(this, 600, 800);
    }

    public void show(int level, int points) {
        this.setPrefSize(600, 800);
        int highscore = Integer.valueOf(updateScoreSheet(level, points)[level - 1]);
        if (level < MAXLEVEL) {
            Rectangle continueShape = new Rectangle();
            continueShape.setArcHeight(50);
            continueShape.setArcWidth(50);
            continueShape.setHeight(100);
            continueShape.setWidth(200);
            continueShape.setStrokeWidth(10);
            
            ImageView continueBG = new ImageView(new Image("file:media/images/buttons/continue.png"));
            continueBG.setFitHeight(100);
            continueBG.setPreserveRatio(true);

            btn_continue = new Button();
            btn_continue.setGraphic(continueBG);
            btn_continue.setShape(continueShape);
            btn_continue.setPrefSize(200, 100);
        }
        
        Rectangle menuShape = new Rectangle();
        menuShape.setArcHeight(50);
		menuShape.setArcWidth(50);
		menuShape.setHeight(100);
		menuShape.setWidth(200);
        menuShape.setStrokeWidth(10);
        
        ImageView menuBG = new ImageView(new Image("file:media/images/buttons/mainmenu.png"));
        menuBG.setFitHeight(100);
        menuBG.setPreserveRatio(true);

        btn_menu = new Button();
        btn_menu.setGraphic(menuBG);
        btn_menu.setShape(menuShape);
        btn_menu.setPrefSize(200, 100);
        
		add(new BackgroundImage("scoreboardBackground"));

        setNumbers(highscore, highscore, 168);
        setNumbers(points, points, 227);
        setNumbers(level, level, 286);
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

	public void add(Actor actor) {
        getChildren().add(actor);
    }

    public Scene getCurrentScene() {
        return this.scene;
    }

    public Button getContinueButton() {
        return this.btn_continue;
    }

    public Button getMenuButton() {
        return this.btn_menu;
    }

    public MainMenu getMainMenu() {
        return this.mainMenu;
    }
}
