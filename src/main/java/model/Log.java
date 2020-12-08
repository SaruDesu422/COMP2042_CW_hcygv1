package model;

import java.lang.Math;

import javafx.scene.image.Image;

public class Log extends Actor {
	
	private final int LIMIT_LEFT = -200;
	private final int LIMIT_RIGHT = 600;
	private final int SIZE_Y = 50;
	private final int STEP = 50;

	private double speed;
	private Animal animal;

	/**
	 * Sets a log as an actor, allows movements.
	 * 
	 * @param 	imageLink
	 * @param 	x
	 * @param 	y
	 * @param 	speed
	 * @param 	animal
	 * @see		Animal
	 */
	public Log(String imageLink, int x, int y, double speed, Animal animal) {
		this.animal = animal;
		this.speed = speed;
		setImage(getImage(imageLink));
		setX(x);
		setY(y);
	}
	
	@Override
	public Image getImage(String address) {
		return new Image("file:media/images/logs/" + address + ".png", 0, SIZE_Y, true, true);
	}

	/**
	 * Every frame, this method checks for out of bounds and
	 * vertical movements.
	 * 
	 * @param	now  current frame
	 */
	@Override
	public void act(long now) {
		move(speed, 0);

		/** Checks out of bounds */
		double rng = (int)Math.random() * 3;
		if (getX() > LIMIT_RIGHT && speed > 0)
			setX(LIMIT_LEFT - rng * 10);
		if (getX() < LIMIT_LEFT && speed < 0)
			setX(LIMIT_RIGHT + rng * 10);

		/** Checks for vertical movements and move accordingly */
		if (animal.isMoveDown())
			move(0, STEP);
		if (animal.isMoveBG())
			move(0, -STEP * animal.getDownMovement());
	}

	/**
	 * Accessor: double speed
	 * 
	 * @return	speed
	 */
	public double getSpeed() {
		return speed;
	}
}