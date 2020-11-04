package model;

import java.lang.Math;

import javafx.scene.image.Image;

public class Turtle extends Actor{

	private final int LIMIT_LEFT = -130;
	private final int LIMIT_RIGHT = 600;
	private final int SIZE = 130;
	Image turtle1;
	Image turtle2;
	Image turtle3;
	private int speed;
	boolean bool = true;

	/**
	* This method initializes the image and starting position of turtles.
    *
	* @param  xpos  x coordinate
	* @param  ypos  y coordinate
	* @param  s     speed
	* @see          image of turtle
	* @see          position of turtle
	*/
	public Turtle(int xpos, int ypos, int s) {
		turtle1 = new Image("file:media/images/TurtleAnimation1.png", SIZE, SIZE, true, true);
		turtle2 = new Image("file:media/images/TurtleAnimation2.png", SIZE, SIZE, true, true);
		turtle3 = new Image("file:media/images/TurtleAnimation3.png", SIZE, SIZE, true, true);
		setX(xpos);
		setY(ypos);
		speed = s;
		setImage(turtle2);
	}
	
	/**
	* This method sets the position of turtle when is out of frame
	* and starts the animation for the turtle.
	* This method also randomizes the distance between each turtles.
    *
    * @param  now  current frame
	* @see         position of turtle
	* @see         animation of turtle
	*/
	@Override
	public void act(long now) {
		if (now/900000000  % 3 ==0) {
			setImage(turtle2);
		}
		else if (now/900000000 % 3 == 1) {
			setImage(turtle1);
		}
		else if (now/900000000 %3 == 2) {
			setImage(turtle3);
		}
		/* turtle out of frame */
		double rng = (int)Math.random() * 3;
		move(speed, 0);
		if (getX() > LIMIT_RIGHT && speed > 0)
			setX(LIMIT_LEFT - rng * 10);
		if (getX() < LIMIT_LEFT && speed < 0)
			setX(LIMIT_RIGHT + rng * 10);
	}
}
