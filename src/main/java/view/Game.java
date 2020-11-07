package view;

import model.*;
import controller.GameController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class Game {

    private MainMenu mainMenu;
    private ScoreBoard scoreBoard;
    private AnimationTimer timer;
    private Animal animal;
    private GameController controller;

    private MyStage stage;
    private int level;

    private String bgInfo;
    private final int END_Y = 96;
    private final String COMMA_DELIMITER = ",";
    private List<List<String>> logInfo;
    private List<List<String>> turtleInfo;
    private List<List<String>> wetTurtleInfo;
    private List<List<String>> obstacleInfo;
    private List<List<String>> restInfo;
    private int restIndex;
    private Button btn_menu;
    int rest;

    public Game(MainMenu mainMenu) {
        this.level = 0;
        this.mainMenu = mainMenu;
        this.scoreBoard = new ScoreBoard(mainMenu, this);
        this.controller = new GameController(this);
    }

    public void initializeLevelInfo() {
        logInfo = new ArrayList<List<String>>();
        turtleInfo = new ArrayList<List<String>>();
        wetTurtleInfo = new ArrayList<List<String>>();
        obstacleInfo = new ArrayList<List<String>>();
        restInfo = new ArrayList<List<String>>();

        // read file level.csv
        try (BufferedReader br = new BufferedReader(new FileReader("level_info/level" + level + ".csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.charAt(0) == '0') {
                    String[] values = line.split(COMMA_DELIMITER);
                    bgInfo = values[1];
                } else if (line.charAt(0) == '1') {
                    String[] values = line.split(COMMA_DELIMITER);
                    logInfo.add(Arrays.asList(values));
                } else if (line.charAt(0) == '2') {
                    String[] values = line.split(COMMA_DELIMITER);
                    turtleInfo.add(Arrays.asList(values));
                } else if (line.charAt(0) == '3') {
                    String[] values = line.split(COMMA_DELIMITER);
                    wetTurtleInfo.add(Arrays.asList(values));
                } else if (line.charAt(0) == '4') {
                    String[] values = line.split(COMMA_DELIMITER);
                    obstacleInfo.add(Arrays.asList(values));
                } else if (line.charAt(0) == '5') {
                    String[] values = line.split(COMMA_DELIMITER);
                    restInfo.add(Arrays.asList(values));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startNextLevel() {
        this.restIndex = 0;
        level++;
        System.out.println("Initializing level " + level);
        initializeLevelInfo();
        
        stage = new MyStage();
        animal = new Animal(this);
        stage.add(new BackgroundImage(bgInfo, animal));
        stage.add(new BackgroundImage("file:media/images/background/header.png"));
        for (int index = 0; index < logInfo.size(); index++) {
            stage.add(new Log(
                logInfo.get(index).get(1),
                Integer.parseInt(logInfo.get(index).get(2)), 
                Integer.parseInt(logInfo.get(index).get(3)), 
                Integer.parseInt(logInfo.get(index).get(4)), 
                Double.parseDouble(logInfo.get(index).get(5)),
                animal
            ));
        }
        for (int index = 0; index < turtleInfo.size(); index++) {
            stage.add(new Turtle(
                Integer.parseInt(turtleInfo.get(index).get(1)), 
                Integer.parseInt(turtleInfo.get(index).get(2)), 
                Integer.parseInt(turtleInfo.get(index).get(3)),
                animal
            ));
        }
        for (int index = 0; index < wetTurtleInfo.size(); index++) {
            stage.add(new WetTurtle(
                Integer.parseInt(wetTurtleInfo.get(index).get(1)), 
                Integer.parseInt(wetTurtleInfo.get(index).get(2)), 
                Integer.parseInt(wetTurtleInfo.get(index).get(3)),
                animal
            ));
        }
        for (int index = 0; index < obstacleInfo.size(); index++) {
            stage.add(new Obstacle(
                obstacleInfo.get(index).get(1), 
                Integer.parseInt(obstacleInfo.get(index).get(2)), 
                Integer.parseInt(obstacleInfo.get(index).get(3)), 
                Double.parseDouble(obstacleInfo.get(index).get(4)), 
                Integer.parseInt(obstacleInfo.get(index).get(5)),
                animal
            ));
        }
        stage.add(new BackgroundImage("file:media/images/background/endBackground.png", animal));
		stage.add(new End(13, END_Y));
		stage.add(new End(141, END_Y));
		stage.add(new End(269, END_Y));
        stage.add(new End(398, END_Y));
        stage.add(new End(528, END_Y));
        
		Circle circle = new Circle();
        circle.setRadius(15);
        
		ImageView homeBG = new ImageView(new Image("file:media/images/buttons/info.png"));
		homeBG.setFitHeight(30);
        homeBG.setFitWidth(25);
        
		btn_menu = new Button();
		btn_menu.setGraphic(homeBG);
		btn_menu.setShape(circle);
        btn_menu.setPrefSize(30, 30);
        btn_menu.setLayoutX(25);
        btn_menu.setLayoutY(700);

		btn_menu.setOnAction(controller::handleButtonMenu);
        btn_menu.addEventHandler(MouseEvent.MOUSE_ENTERED, controller::handleButtonMenuMouseIn);
        btn_menu.addEventHandler(MouseEvent.MOUSE_EXITED, controller::handleButtonMenuMouseOut);
        stage.getChildren().add(btn_menu);

        stage.add(new Digit(0, 540, 43));
        
        stage.add(animal);

        start();
    }

    public int getRest() {
        return Integer.parseInt(restInfo.get(restIndex).get(1));
    }

    public void setRestIndex() {
        restIndex++;
    }

    public boolean checkRestInfo() {
        if (restIndex + 1 == restInfo.size() || restInfo == null) {
            return false;
        }
        return true;
    }

    public MyStage getStage() {
        return this.stage;
    }

    public int getLevel() {
        return this.level;
    }

    public void createTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                animal.act(now);
                List<Actor> actors = stage.getObjects(Actor.class);
                for (Actor anActor: actors) {
                	anActor.act(now);
                }
                if (animal.resetScore()) {
            		setNumber(animal.getPoints());
                }
                // if all ends are activated
            	if (animal.getStop()) {
            		stage.stopMusic();
                    stop();
                    scoreBoard.show(level, animal.getPoints());
                    mainMenu.getApp().showScoreBoard(scoreBoard);
            	}
            }
        };
    }

    public void start() {
        stage.playMusic();
    	createTimer();
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
    
    public void setNumber(int n) {
    	int shift = 0;
    	while (n > 0) {
            int d = n / 10;
            int k = n - d * 10;
            n = d;
            stage.add(new Digit(k, 540 - shift, 43));
            shift += 30;
    	}
    }

    /**
	* This method returns configurations of start button.
	*
    * @return  Start button
	*/
    public Button getMenuButton() {
		return this.btn_menu;
    }
    
    public MainMenu getMainMenu() {
        return this.mainMenu;
    }
}