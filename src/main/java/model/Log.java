package model;

import java.lang.Math;

import javafx.scene.image.Image;

public class Log extends Actor {

	private final int LIMIT_LEFT = -180;
	private final int LIMIT_RIGHT = 600;
	private double speed;

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
	public Log(String imageLink, int size, int xpos, int ypos, double s) {
		setImage(new Image(imageLink, size, size, true, true));
		setX(xpos);
		setY(ypos);
		speed = s;
	}
	
	/**
	* This method returns true of log is moving to the left.
    *
    * @return     returns boolean comparison of speed < 0
	*/
	public boolean getLeft() {
		return speed < 0;
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
	}
}
