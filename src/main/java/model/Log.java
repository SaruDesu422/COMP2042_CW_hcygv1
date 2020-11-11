package model;

import java.lang.Math;

import javafx.scene.image.Image;

public class Log extends Actor {
	private final int LIMIT_LEFT = -180;
	private final int LIMIT_RIGHT = 600;
	private final int SIZE_Y = 50;
	private final int STEP = 50;

	private double speed;
	Animal animal;
	/**
	* This method configures .
    *
    * @param  imageLink  link for image
	* @param  size       size of log
	* @param  xpos       x coordinate
	* @param  ypos       y coordinate
	* @param  s          speed
	* @see               image of log
	*/
	public Log(String imageLink, int xpos, int ypos, double s, Animal animal) {
		this.animal = animal;
		setImage(new Image("file:media/images/logs/" + imageLink + ".png", 0, SIZE_Y, true, true));
		setX(xpos);
		setY(ypos);
		speed = s;
	}
	
	/**
	* This method returns true of log is moving to the left.
    *
    * @return     returns boolean comparison of speed < 0
	*/
	public double getSpeed() {
		return speed;
	}

	/**
	* This method sets 
    *
    * @param  now  current frame
	*/
	@Override
	public void act(long now) {
		double rng = (int)Math.random() * 3;
		move(speed , 0);
		if (getX() > LIMIT_RIGHT && speed > 0)
			setX(LIMIT_LEFT - rng * 10);
		if (getX() < LIMIT_LEFT && speed < 0)
			setX(LIMIT_RIGHT + rng * 10);
		if (animal.getMoveDown())
			move(0, STEP);
		if (animal.getMoveBG())
			move(0, -STEP * animal.getDownMovement());
	}
}
