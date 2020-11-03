package model;

import javafx.scene.image.Image;

public class WetTurtle extends Actor{
	private final int SIZE = 130;
	Image turtle1;
	Image turtle2;
	Image turtle3;
	Image turtle4;
	private int speed;
	boolean bool = true;
	boolean sunk = false;

	/**
	* This method initializes .
    *
	* @param  xpos  x coordinate
	* @param  ypos  y coordinate
	* @param  s     speed
	* @see          Image of wet turtle
	*/
	public WetTurtle(int xpos, int ypos, int s) {
		turtle1 = new Image("file:src/main/java/view/images/TurtleAnimation1.png", SIZE, SIZE, true, true);
		turtle2 = new Image("file:src/main/java/view/images/TurtleAnimation2Wet.png", SIZE, SIZE, true, true);
		turtle3 = new Image("file:src/main/java/view/images/TurtleAnimation3Wet.png", SIZE, SIZE, true, true);
		turtle4 = new Image("file:src/main/java/view/images/TurtleAnimation4Wet.png", SIZE, SIZE, true, true);
		setX(xpos);
		setY(ypos);
		speed = s;
		setImage(turtle2);
	}

	/**
	* This method configures actions and image of wet turtle.
    *
    * @param  now  current frame
	* @see         Movement of wet turtle
	*/
	@Override
	public void act(long now) {
		if (now/900000000 % 4 == 0) {
			setImage(turtle2);
			sunk = false;
		}
		else if (now/900000000 % 4 == 1) {
			setImage(turtle1);
			sunk = false;
		}
		else if (now/900000000 %4 == 2) {
			setImage(turtle3);
			sunk = false;
		} 
		else if (now/900000000 %4 == 3) {
			setImage(turtle4);
			sunk = true;
		}
		move(speed , 0);
		if (getX() > 600 && speed>0)
			setX(-200);
		if (getX() < -75 && speed<0)
			setX(600);
	}

	/**
	* This method returns the value of sunk.
    *
	* @return      Returns value of sunk
	*/
	public boolean isSunk() {
		return sunk;
	}
}
