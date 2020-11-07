package model;

import java.lang.Math;

import javafx.scene.image.Image;

public class Turtle extends Actor{
	private final int LIMIT_LEFT = -130;
	private final int LIMIT_RIGHT = 600;
	private final int SIZE_X = 130;
	private final int SIZE_Y = 50;
	private final int STEP = 50;
	
	private double speed;
	private int frame = 0;
	Animal animal;

	boolean bool = true;
	Image turtle1;
	Image turtle2;
	Image turtle3;
	Image turtle4;

	/**
	* This method initializes the image and starting position of turtles.
    *
	* @param  xpos  x coordinate
	* @param  ypos  y coordinate
	* @param  s     speed
	* @see          image of turtle
	* @see          position of turtle
	*/
	public Turtle(int xpos, int ypos, double s, Animal animal) {
		this.animal = animal;
		if (s > 0) {
			turtle1 = new Image("file:media/images/turtle/turtle1.png", SIZE_X, SIZE_Y, false, true);
			turtle2 = new Image("file:media/images/turtle/turtle2.png", SIZE_X, SIZE_Y, false, true);
			turtle3 = new Image("file:media/images/turtle/turtle3.png", SIZE_X, SIZE_Y, false, true);
			turtle4 = new Image("file:media/images/turtle/turtle4.png", SIZE_X, SIZE_Y, false, true);
		} else {
			turtle1 = new Image("file:media/images/turtle/turtle1left.png", SIZE_X, SIZE_Y, false, true);
			turtle2 = new Image("file:media/images/turtle/turtle2left.png", SIZE_X, SIZE_Y, false, true);
			turtle3 = new Image("file:media/images/turtle/turtle3left.png", SIZE_X, SIZE_Y, false, true);
			turtle4 = new Image("file:media/images/turtle/turtle4left.png", SIZE_X, SIZE_Y, false, true);
		}
		setX(xpos);
		setY(ypos);
		speed = s;
		setImage(turtle1);
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
		if (frame == 0 || frame == 16) {
			setImage(turtle2);
		} else if (frame == 4 || frame == 12) {
			setImage(turtle3);
		} else if (frame == 8) {
			setImage(turtle4);
		} else if (frame == 20) {
			setImage(turtle1);
			frame = 0;
		}
		frame++;
		/* turtle out of frame */
		double rng = (int)Math.random() * 3;
		move(speed, 0);
		if (getX() > LIMIT_RIGHT && speed > 0)
			setX(LIMIT_LEFT - rng * 10);
		if (getX() < LIMIT_LEFT && speed < 0)
			setX(LIMIT_RIGHT + rng * 10);
		if (animal.getRestMove() > 0)
			move(0, STEP);
	}
}
