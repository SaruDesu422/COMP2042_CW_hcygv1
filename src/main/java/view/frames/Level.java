package view.frames;

import model.*;

import javafx.animation.AnimationTimer;

public class Level{
    
    AnimationTimer timer;
    Animal animal;
    MyStage background;
    private int lvl = 1;
    ScoreBoard scoreBoard;

    public Level() {
        lvl = 1;
        if (lvl == 1) {
            init_level1();
        }
        if (lvl == 2) {
            init_level2();
        }
    }

    public void init_level1() {
        background = new MyStage();
        BackgroundImage gameBG = new BackgroundImage("file:src/main/java/view/images/iKogsKW.png");
        background.add(gameBG);

        //log3.png
        background.add(new Log("file:src/main/java/view/images/log3.png", 150, 0, 166, 0.75));
		background.add(new Log("file:src/main/java/view/images/log3.png", 150, 220, 166, 0.75));
        background.add(new Log("file:src/main/java/view/images/log3.png", 150, 440, 166, 0.75));
        //logs.png
		background.add(new Log("file:src/main/java/view/images/logs.png", 300, 0, 276, -2));
		background.add(new Log("file:src/main/java/view/images/logs.png", 300, 400, 276, -2));
        //logs3.png
        background.add(new Log("file:src/main/java/view/images/log3.png", 150, 50, 329, 0.75));
		background.add(new Log("file:src/main/java/view/images/log3.png", 150, 270, 329, 0.75));
		background.add(new Log("file:src/main/java/view/images/log3.png", 150, 490, 329, 0.75));
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
        animal = new Animal("file:src/main/java/view/images/froggerUp.png");
        background.add(animal);
        //obstacle
        background.add(new Obstacle("file:src/main/java/view/images/truck1Right.png", 0, 649, 1, 120, 120));
		background.add(new Obstacle("file:src/main/java/view/images/truck1Right.png", 300, 649, 1, 120, 120));
		background.add(new Obstacle("file:src/main/java/view/images/truck1Right.png", 600, 649, 1, 120, 120));
		background.add(new Obstacle("file:src/main/java/view/images/car1Left.png", 100, 597, -1, 50, 50));
		background.add(new Obstacle("file:src/main/java/view/images/car1Left.png", 250, 597, -1, 50, 50));
		background.add(new Obstacle("file:src/main/java/view/images/car1Left.png", 400, 597, -1, 50, 50));
		background.add(new Obstacle("file:src/main/java/view/images/car1Left.png", 550, 597, -1, 50, 50));
		background.add(new Obstacle("file:src/main/java/view/images/truck2Right.png", 0, 540, 1, 200, 200));
		background.add(new Obstacle("file:src/main/java/view/images/truck2Right.png", 500, 540, 1, 200, 200));
		background.add(new Obstacle("file:src/main/java/view/images/car1Left.png", 500, 490, -5, 50, 50));
        //digit
        background.add(new Digit(0, 550, 90));

        background.start();
    }

    public void init_level2() {

    }

    public MyStage getCurrentStage() {
        return this.background;
    }
}