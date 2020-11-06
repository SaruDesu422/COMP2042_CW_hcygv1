package model;

import java.lang.Math;

import javafx.scene.image.Image;

public class WetTurtle extends Actor{
	private final int LIMIT_LEFT = -130;
	private final int LIMIT_RIGHT = 600;
	private final int SIZE = 130;
	private final double MOVEMENT_Y = 26.666666;

	private int speed;
	Animal animal;

	boolean bool = true;
	boolean sunk = false;
	Image turtle1;
	Image turtle2;
	Image turtle3;
	Image turtle4;

	/**
	* This method initializes the image and starting position of wet turtles.
    *
	* @param  xpos  x coordinate
	* @param  ypos  y coordinate
	* @param  s     speed
	* @see          image of wet turtle
	* @see          position of wet turtle
	*/
	public WetTurtle(int xpos, int ypos, int s, Animal animal) {
		this.animal = animal;
		turtle1 = new Image("file:media/images/TurtleAnimation1.png", SIZE, SIZE, true, true);
		turtle2 = new Image("file:media/images/TurtleAnimation2Wet.png", SIZE, SIZE, true, true);
		turtle3 = new Image("file:media/images/TurtleAnimation3Wet.png", SIZE, SIZE, true, true);
		turtle4 = new Image("file:media/images/TurtleAnimation4Wet.png", SIZE, SIZE, true, true);
		setX(xpos);
		setY(ypos);
		speed = s;
		setImage(turtle2);
	}

	/**
	* This method sets the position of wet turtle when is out of frame
	* and starts the animation for the wet turtle.
	* This method also randomizes the distance between each wet turtles.
    *
    * @param  now  current frame
	* @see         position of wet turtle
	* @see         animation of wet turtle
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
		else if (now/900000000 % 4 == 2) {
			setImage(turtle3);
			sunk = false;
		} 
		else if (now/900000000 % 4 == 3) {
			setImage(turtle4);
			sunk = true;
		}
		/* wet turtle out of frame */
		double rng = (int)Math.random() * 3;
		if (animal.getRestMove() > 0)
			move(0, MOVEMENT_Y * 2);
		move(speed, 0);
		if (getX() > LIMIT_RIGHT && speed > 0)
			setX(LIMIT_LEFT - rng * 10);
		if (getX() < LIMIT_LEFT && speed < 0)
			setX(LIMIT_RIGHT + rng * 10);
	}

	/**
	* This method returns the value of sunk.
    *
	* @return      returns value of sunk
	*/
	public boolean isSunk() {
		return sunk;
	}
}
