package model;

import java.lang.Math;

import javafx.scene.image.Image;

public class Obstacle extends Actor {

	private final int LIMIT_LEFT = -200;
	private final int LIMIT_RIGHT = 600;
	private final int SIZE_Y = 50;
	private final int STEP = 50;

	private double speed;
	Animal animal;

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
	* @see               position of obstacle
	*/
	public Obstacle(String image, int xpos, int ypos, double s, Animal animal) {
		this.animal = animal;
		if (s > 0) {
			if (image.equals("car")) {
				int rng = (int)(Math.random() * 3 + 1);
				setImage(new Image("file:media/images/obstacles/" + image + rng + ".png", 0, SIZE_Y, true, true));
			} else {
				setImage(new Image("file:media/images/obstacles/" + image + ".png", 0, SIZE_Y, true, true));
			}
		} else {
			if (image.equals("car")) {
				int rng = (int)(Math.random() * 3 + 1);
				setImage(new Image("file:media/images/obstacles/" + image + rng + "left.png", 0, SIZE_Y, true, true));
			} else {
				setImage(new Image("file:media/images/obstacles/" + image + "left.png", 0, SIZE_Y, true, true));
			}
		}
		setX(xpos);
		setY(ypos);
		speed = s;
	}

	/**
	* This method sets the position of obstacle when is out of frame
	* and randomizes distance between each obstacle.
    *
	* @param  now  current frame
	* @see         position of obstacle
	*/
	@Override
	public void act(long now) {
		int rng = (int)(Math.random() * 3);
		move(speed, 0);
		if (getX() > LIMIT_RIGHT && speed > 0)
			setX(LIMIT_LEFT - rng * 10);
		if (getX() < LIMIT_LEFT && speed < 0)
			setX(LIMIT_RIGHT + rng * 10);
		if (animal.getRestMove() > 0)
			move(0, STEP);
	}
}
