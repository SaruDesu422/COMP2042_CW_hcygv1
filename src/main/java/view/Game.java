package view;

import model.*;
import controller.GameController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

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

    private final String COMMA_DELIMITER = ",";
    private final int STEP = 50;

    private final char TYPE_OBSTACLE = '1';
    private final char TYPE_TURTLE = '2';
    private final char TYPE_WETTURTLE = '3';
    private final char TYPE_LOG = '4';
    private final char TYPE_REST = '5';
    private final char TYPE_END = '6';

    private final int LAND = 0;
    private final int WATER = 1;
    private final int REST = 2;
    private final int END = 3;

    private final int YPOS = 1;
    private final int XPOS = 2;
    private final int SPEED = 3;
    private final int IMG = 4;

    private List<List<String>> logInfo;
    private List<List<String>> turtleInfo;
    private List<List<String>> wetTurtleInfo;
    private List<List<String>> obstacleInfo;
    private List<List<Integer>> backgroundInfo;
    private int endInfo;
    private Button btn_menu;
    private BackgroundImage endBG;
    int rest;

    /**
    * Sets up the pane for the current level to be shown on.
    * <pre>
    * Methods:<br>initializeLevelInfo()<br>startNextLevel()<br>createTimer()
    * </pre>
    *
    * @param    mainMenu
    * @see      MainMenu
    */
    public Game(MainMenu mainMenu) {
        this.level = 0;
        this.mainMenu = mainMenu;
        this.scoreBoard = new ScoreBoard(mainMenu, this);
    }

    /**
    * Reads the level file, stores the data in arrays and 
    * sorts the backgroundInfo array.
    *
    */
    public void initializeLevelInfo() {
        logInfo = new ArrayList<List<String>>();
        turtleInfo = new ArrayList<List<String>>();
        wetTurtleInfo = new ArrayList<List<String>>();
        obstacleInfo = new ArrayList<List<String>>();
        this.backgroundInfo = new ArrayList<List<Integer>>();

        try (BufferedReader br = new BufferedReader(new FileReader("data/level" + level + ".csv"))) {
            String line;
            List<Integer> list;
            String[] values;
            while ((line = br.readLine()) != null) {
                if (line.charAt(0) == TYPE_OBSTACLE) {
                    values = line.split(COMMA_DELIMITER);
                    obstacleInfo.add(Arrays.asList(values));
                    list = new ArrayList<Integer>();
                    list.add(LAND);
                    list.add(Integer.valueOf(obstacleInfo.get(obstacleInfo.size() - 1).get(YPOS)));
                    backgroundInfo.add(list);
                } else if (line.charAt(0) == TYPE_TURTLE) {
                    values = line.split(COMMA_DELIMITER);
                    turtleInfo.add(Arrays.asList(values));
                    list = new ArrayList<Integer>();
                    list.add(WATER);
                    list.add(Integer.valueOf(turtleInfo.get(turtleInfo.size() - 1).get(YPOS)));
                    backgroundInfo.add(list);
                } else if (line.charAt(0) == TYPE_WETTURTLE) {
                    values = line.split(COMMA_DELIMITER);
                    wetTurtleInfo.add(Arrays.asList(values));
                    list = new ArrayList<Integer>();
                    list.add(WATER);
                    list.add(Integer.valueOf(wetTurtleInfo.get(wetTurtleInfo.size() - 1).get(YPOS)));
                    backgroundInfo.add(list);
                } else if (line.charAt(0) == TYPE_LOG) {
                    values = line.split(COMMA_DELIMITER);
                    logInfo.add(Arrays.asList(values));
                    list = new ArrayList<Integer>();
                    list.add(WATER);
                    list.add(Integer.valueOf(logInfo.get(logInfo.size() - 1).get(YPOS)));
                    backgroundInfo.add(list);
                } else if (line.charAt(0) == TYPE_REST) {
                    values = line.split(COMMA_DELIMITER);
                    list = new ArrayList<Integer>();
                    list.add(REST);
                    list.add(Integer.valueOf(values[YPOS]));
                    backgroundInfo.add(list);
                } else if (line.charAt(0) == TYPE_END) {
                    values = line.split(COMMA_DELIMITER);
                    endInfo = Integer.parseInt(values[YPOS]);
                    list = new ArrayList<Integer>();
                    list.add(END);
                    list.add(Integer.valueOf(values[YPOS]));
                    backgroundInfo.add(list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /** Sorts the backgroundInfo array according to y position */
        Collections.sort(this.backgroundInfo, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> list1, List<Integer> list2) {
                return Integer.valueOf(list1.get(1)).compareTo(Integer.valueOf(list2.get(1)));
            }
        });
    }

    /**
    * Sets up the stage for the level and adds objects.
    * 
    */
    public void startNextLevel() {
        level++;
        initializeLevelInfo();

        stage = new MyStage();
        animal = new Animal(this);

        /** Generates background */
        for (int index = 0; index < backgroundInfo.size(); index++) {
            if(backgroundInfo.get(index).get(0).equals(WATER)) {
                stage.add(new BackgroundImage("waterBackground", 695 - backgroundInfo.get(index).get(YPOS) * STEP, animal));
            } else if (backgroundInfo.get(index).get(0).equals(LAND)) {
                stage.add(new BackgroundImage("landBackground", 695 - backgroundInfo.get(index).get(YPOS) * STEP, animal));
            } else if (backgroundInfo.get(index).get(0).equals(REST)) {
                stage.add(new BackgroundImage("restBackground", 695 - backgroundInfo.get(index).get(YPOS) * STEP, animal));
            }
        }
        stage.add(new BackgroundImage("startBackground", 695, animal));
        endBG = new BackgroundImage("endBackground", 683 - (endInfo * STEP), animal);
        stage.add(endBG);
        stage.add(new End(13, 685 - (endInfo * STEP), animal));
        stage.add(new End(141, 685 - (endInfo * STEP), animal));
        stage.add(new End(269, 685 - (endInfo * STEP), animal));
        stage.add(new End(398, 685 - (endInfo * STEP), animal));
        stage.add(new End(528, 685 - (endInfo * STEP), animal));

        /** Adds obstacles */
        for (int index = 0; index < obstacleInfo.size(); index++) {
            int y = 695 - STEP * Integer.parseInt(obstacleInfo.get(index).get(YPOS));
            stage.add(new Obstacle(
                obstacleInfo.get(index).get(IMG), 
                Integer.valueOf(obstacleInfo.get(index).get(XPOS)), 
                y, 
                Double.parseDouble(obstacleInfo.get(index).get(SPEED)),
                animal
            ));
        }
        
        /** Adds turtles */
        for (int index = 0; index < turtleInfo.size(); index++) {
            int y = 695 - STEP * Integer.parseInt(turtleInfo.get(index).get(YPOS));
            stage.add(new Turtle(
                Integer.valueOf(turtleInfo.get(index).get(XPOS)), 
                y, 
                Double.parseDouble(turtleInfo.get(index).get(SPEED)),
                animal
            ));
        }
        
        /** Adds wet turtles */
        for (int index = 0; index < wetTurtleInfo.size(); index++) {
            int y = 695 - STEP * Integer.parseInt(wetTurtleInfo.get(index).get(YPOS));
            stage.add(new WetTurtle(
                Integer.valueOf(wetTurtleInfo.get(index).get(XPOS)), 
                y, 
                Double.parseDouble(wetTurtleInfo.get(index).get(SPEED)),
                animal
            ));
        }
        
        /** Adds logs */
        for (int index = 0; index < logInfo.size(); index++) {
            int y = 695 - STEP * Integer.parseInt(logInfo.get(index).get(YPOS));
            stage.add(new Log(
                logInfo.get(index).get(IMG),
                Integer.valueOf(logInfo.get(index).get(XPOS)), 
                y, 
                Double.parseDouble(logInfo.get(index).get(SPEED)),
                animal
            ));
        }
        
        /** Creates a menu button */
        btn_menu = new Button();
        
		ImageView homeBG = new ImageView(new Image("file:media/images/buttons/home.png"));
		homeBG.setFitHeight(30);
        homeBG.setFitWidth(25);
        btn_menu.setGraphic(homeBG);
        
		Circle circle = new Circle();
        circle.setRadius(15);
        btn_menu.setShape(circle);
        btn_menu.setPrefSize(30, 30);
        
        /** Configure menu button position */
        btn_menu.setLayoutX(25);
        btn_menu.setLayoutY(710);

        /** Menu button controls */
        this.controller = new GameController(this);
		btn_menu.setOnAction(controller::handleButtonMenu);
        btn_menu.addEventHandler(MouseEvent.MOUSE_ENTERED, controller::handleButtonMenuMouseIn);
        btn_menu.addEventHandler(MouseEvent.MOUSE_EXITED, controller::handleButtonMenuMouseOut);
        stage.getChildren().add(btn_menu);

        /** Adds header and animal */
        stage.add(new BackgroundImage("header"));
        stage.add(new Digit(0, 540, 43));
        stage.add(animal);

        start();
    }

    /**
    * Creates a new array for each steps whether it is water or not.
    *
    * @return   water
    */
    public List<Integer> getWater() {
        List<Integer> water = new ArrayList<Integer>();
        water.add(0);
        if (backgroundInfo.get(0).get(0).equals(WATER)) {
            water.add(1);
        } else {
            water.add(0);
        }
        for (int index = 1; index < backgroundInfo.size(); index++) {
            if (!backgroundInfo.get(index).get(YPOS).equals(backgroundInfo.get(index - 1).get(YPOS))) {
                if (backgroundInfo.get(index).get(0).equals(WATER) || backgroundInfo.get(index).get(0).equals(END)) {
                    water.add(1);
                } else {
                    water.add(0);
                }
            }
        }
        return water;
    }

    /**
    * Initiates the starting routine of every level.
    * 
    */
    public void start() {
        stage.playMusic();
    	createTimer();
        timer.start();
    }

    /**
    * Create a new AnimationTimer to start the game. 
    * Every frame, this method:<p>
    * - Calls the act method on every objects<p>
    * - Sets the scores<p>
    * - Checks for end of level
    *
    */
    public void createTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                List<Actor> actors = stage.getObjects(Actor.class);
                for (Actor anActor: actors) anActor.act(now);
                setNumber(animal.getPoints());
            	if (animal.getEndActivated() == 5) {
            		stage.stopMusic();
                    timer.stop();
                    scoreBoard.show(level, animal.getPoints());
                    mainMenu.getApp().showScoreBoard(scoreBoard);
            	}
            }
        };
    }
    
    /**
    * Sets every digits in points to a new digit object.
    * 
    * @param    points
    */
    public void setNumber(int points) {
    	int shift = 0;
    	while (points > 0) {
            int d = points / 10;
            int k = points - d * 10;
            points = d;
            stage.add(new Digit(k, 540 - shift, 43));
            shift += 30;
    	}
    }

    /** Accessors **/

	/**
	* Accessor: int endInfo
    *
    * @return	endInfo
	*/
    public int getEnd() {
        return endInfo;
    }

	/**
	* Accessor: MyStage stage
    *
    * @return	stage
	*/
    public MyStage getStage() {
        return this.stage;
    }

	/**
	* Accessor: int level
    *
    * @return	level
	*/
    public int getLevel() {
        return this.level;
    }

	/**
	* Accessor: Button btn_menu
    *
    * @return	btn_menu
	*/
    public Button getMenuButton() {
		return this.btn_menu;
    }
    
	/**
	* Accessor: MainMenu mainMenu
    *
    * @return	mainMenu
	*/
    public MainMenu getMainMenu() {
        return this.mainMenu;
    }
}