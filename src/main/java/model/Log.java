package model;

import javafx.scene.image.Image;

public class Log extends Actor {

	private double speed;

	/**
	* This method configures .
    *
    * @param  imageLink  link for image
	* @param  size       size of log
	* @param  xpos       x coordinate
	* @param  ypos       y coordinate
	* @param  s          speed
	* @see               Image of log
	*/
	public Log(String imageLink, int size, int xpos, int ypos, double s) {
		setImage(new Image(imageLink, size, size, true, true));
		setX(xpos);
		setY(ypos);
		speed = s;
	}

	/**
	* This method moves object that is out of pane to
	* initial position.
    *
    * @param  now  current frame
	*/
	@Override
	public void act(long now) {
		move(speed , 0);
		if (getX()>600 && speed>0)
			setX(-180);
		if (getX()<-300 && speed<0)
			setX(700);
	}
	
	/**
	* This method returns true of log is moving to the left.
    *
    * @return     returns true if log is moving to the left
	*/
	public boolean getLeft() {
		return speed < 0;
	}
}
