package model;

import javafx.scene.image.Image;

public class Obstacle extends Actor {
	private final int LIMIT_LEFT = -120;
	private final int LIMIT_RIGHT = 600;
	private int speed;

	/**
	* This method configures initial image and position of obstacles.
    *
	* @param  imageLink  link of image
	* @param  xpos	     x coordinate
	* @param  ypos       y coordinate
	* @param  s          speed
	* @param  w          width
	* @param  h          height
	* @see               image of obstacle
	*/
	public Obstacle(String imageLink, int xpos, int ypos, int s, int w, int h) {
		setImage(new Image(imageLink, w,h, true, true));
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
		if (getX() > LIMIT_RIGHT && speed > 0)
			setX(LIMIT_LEFT);
		if (getX() < LIMIT_LEFT && speed < 0)
			setX(LIMIT_RIGHT);
	}
}
