package view;
import model.*;

import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;

public class Level{

    MainMenu mainMenu;
    AnimationTimer timer;
    Animal animal;
    MyStage background;
    ScoreBoard scoreBoard;

    public Level(MainMenu mainMenu, int lvl) {
        this.mainMenu = mainMenu;
        if (lvl == 1) {
            System.out.println("Initializing level 1");
            init_level1();
        }
        if (lvl == 2) {
            System.out.println("Initializing level 2");
            init_level2();
        }
    }

    public void init_level1() {
        background = new MyStage();
        BackgroundImage gameBG = new BackgroundImage("file:media/images/iKogsKW.png");
        background.add(gameBG);

        //log3.png
        background.add(new Log("file:media/images/log3.png", 150, 0, 166, 0.75));
		background.add(new Log("file:media/images/log3.png", 150, 220, 166, 0.75));
        background.add(new Log("file:media/images/log3.png", 150, 440, 166, 0.75));
        //logs.png
		background.add(new Log("file:media/images/logs.png", 300, 0, 276, -2));
		background.add(new Log("file:media/images/logs.png", 300, 400, 276, -2));
        //logs3.png
        background.add(new Log("file:media/images/log3.png", 150, 50, 329, 0.75));
		background.add(new Log("file:media/images/log3.png", 150, 270, 329, 0.75));
		background.add(new Log("file:media/images/log3.png", 150, 490, 329, 0.75));
        //turtle
        background.add(new Turtle(500, 376, -1));
		background.add(new Turtle(300, 376, -1));
        //wettrutle
        background.add(new WetTurtle(700, 376, -1));
		background.add(new WetTurtle(600, 217, -1));
		background.add(new WetTurtle(400, 217, -1));
        background.add(new WetTurtle(200, 217, -1));
        //end
		background.add(new End(13,96));
		background.add(new End(141,96));
		background.add(new End(141 + 141-13,96));
		background.add(new End(141 + 141-13+141-13+1,96));
		background.add(new End(141 + 141-13+141-13+141-13+3,96));
        //animal
        animal = new Animal("file:media/images/froggerUp.png");
        background.add(animal);
        //obstacle
        background.add(new Obstacle("file:media/images/truck1Right.png", 0, 649, 1, 120, 120));
		background.add(new Obstacle("file:media/images/truck1Right.png", 300, 649, 1, 120, 120));
		background.add(new Obstacle("file:media/images/truck1Right.png", 600, 649, 1, 120, 120));
		background.add(new Obstacle("file:media/images/car1Left.png", 100, 597, -1, 50, 50));
		background.add(new Obstacle("file:media/images/car1Left.png", 250, 597, -1, 50, 50));
		background.add(new Obstacle("file:media/images/car1Left.png", 400, 597, -1, 50, 50));
		background.add(new Obstacle("file:media/images/car1Left.png", 550, 597, -1, 50, 50));
		background.add(new Obstacle("file:media/images/truck2Right.png", 0, 540, 1, 200, 200));
		background.add(new Obstacle("file:media/images/truck2Right.png", 500, 540, 1, 200, 200));
		background.add(new Obstacle("file:media/images/car1Left.png", 500, 490, -5, 50, 50));
        //digit
        background.add(new Digit(0, 550, 25));

        start();
    }

    public void init_level2() {
        background = new MyStage();
        BackgroundImage gameBG = new BackgroundImage("file:media/images/iKogsKW.png");
        background.add(gameBG);

        //log3.png
        background.add(new Log("file:media/images/log3.png", 150, 0, 166, 0.75));
		background.add(new Log("file:media/images/log3.png", 150, 220, 166, 0.75));
        background.add(new Log("file:media/images/log3.png", 150, 440, 166, 0.75));
        //logs.png
		background.add(new Log("file:media/images/logs.png", 300, 0, 276, -2));
		background.add(new Log("file:media/images/logs.png", 300, 400, 276, -2));
        //logs3.png
        background.add(new Log("file:media/images/log3.png", 150, 50, 329, 0.75));
		background.add(new Log("file:media/images/log3.png", 150, 270, 329, 0.75));
		background.add(new Log("file:media/images/log3.png", 150, 490, 329, 0.75));
        //turtle
        background.add(new Turtle(500, 376, -1));
		background.add(new Turtle(300, 376, -1));
        //wettrutle
        background.add(new WetTurtle(700, 376, -1));
		background.add(new WetTurtle(600, 217, -1));
		background.add(new WetTurtle(400, 217, -1));
        background.add(new WetTurtle(200, 217, -1));
        //end
		background.add(new End(13,96));
		background.add(new End(141,96));
		background.add(new End(141 + 141-13,96));
		background.add(new End(141 + 141-13+141-13+1,96));
		background.add(new End(141 + 141-13+141-13+141-13+3,96));
        //animal
        animal = new Animal("file:media/images/froggerUp.png");
        background.add(animal);
        //obstacle
        background.add(new Obstacle("file:media/images/truck1Right.png", 0, 649, 1, 120, 120));
		background.add(new Obstacle("file:media/images/truck1Right.png", 300, 649, 1, 120, 120));
		background.add(new Obstacle("file:media/images/truck1Right.png", 600, 649, 1, 120, 120));
		background.add(new Obstacle("file:media/images/car1Left.png", 100, 597, -1, 50, 50));
		background.add(new Obstacle("file:media/images/car1Left.png", 250, 597, -1, 50, 50));
		background.add(new Obstacle("file:media/images/car1Left.png", 400, 597, -1, 50, 50));
		background.add(new Obstacle("file:media/images/car1Left.png", 550, 597, -1, 50, 50));
		background.add(new Obstacle("file:media/images/truck2Right.png", 0, 540, 1, 200, 200));
		background.add(new Obstacle("file:media/images/truck2Right.png", 500, 540, 1, 200, 200));
		background.add(new Obstacle("file:media/images/car1Left.png", 500, 490, -5, 50, 50));
        //digit
        background.add(new Digit(0, 550, 25));

        start();
    }

    public MyStage getCurrentStage() {
        return this.background;
    }

    public void createTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                animal.act(now);
                List<Actor> actors = background.getObjects(Actor.class);
                for (Actor anActor: actors) {
                	anActor.act(now);
                }
                if (animal.resetScore()) {
            		setNumber(animal.getPoints());
                }
                // if all ends are activated
            	if (animal.getStop()) {
            		System.out.println("Finished level: " + mainMenu.getCurrentLevel());
            		background.stopMusic();
                    stop();
                    // change to scoreboard
                    scoreBoard = new ScoreBoard(mainMenu);
                    Scene scoreBoardScene = new Scene(scoreBoard, 600, 800);
                    mainMenu.getApp().getPrimaryStage().setScene(scoreBoardScene);
                    mainMenu.getApp().getPrimaryStage().show();
            		// Alert alert = new Alert(AlertType.INFORMATION);
            		// alert.setTitle("You Have Won The Game!");
            		// alert.setHeaderText("Your High Score: "+animal.getPoints()+"!");
            		// alert.setContentText("Highest Possible Score: 800");
            		// alert.show();
            	}
            }
        };
    }

    public void start() {
        background.playMusic();
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
            background.add(new Digit(k, 550 - shift, 25));
            shift+=30;
    	}
    }
}