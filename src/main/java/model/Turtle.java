package model;

import java.lang.Math;

import javafx.scene.image.Image;

public class Turtle extends Actor{
	private final int LIMIT_LEFT = -130;
	private final int LIMIT_RIGHT = 600;
	private final int SIZE_X = 130;
	private final int SIZE_Y = 50;
	private final int STEP = 45;
	
	private double speed;
	private int frame = 0;
	private Animal animal;

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
	public Turtle(int xpos, int ypos, double s, Animal animal) {
		this.animal = animal;
		this.speed = s;
		setX(xpos);
		setY(ypos);
		setImage(getImage("turtle1"));
	}

	@Override
	public Image getImage(String address) {
		if (speed > 0)
			return new Image("file:media/images/turtle/" + address + ".png", SIZE_X, SIZE_Y, false, true);
		return new Image("file:media/images/turtle/" + address + "left.png", SIZE_X, SIZE_Y, false, true);
	}
	
	public double getSpeed() {
		return speed;
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
			setImage(getImage("turtle2"));
		} else if (frame == 4 || frame == 12) {
			setImage(getImage("turtle3"));
		} else if (frame == 8) {
			setImage(getImage("turtle4"));
		} else if (frame == 20) {
			setImage(getImage("turtle1"));
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
		if (animal.isMoveDown())
			move(0, STEP);
		if (animal.isMoveBG())
			move(0, -STEP * animal.getDownMovement());
	}
}
