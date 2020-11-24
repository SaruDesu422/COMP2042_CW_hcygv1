package model;

import java.lang.Math;

import javafx.scene.image.Image;

public class Obstacle extends Actor {

	private final int LIMIT_LEFT = -250;
	private final int LIMIT_RIGHT = 600;
	private final int SIZE_Y = 50;
	private final int STEP = 50;

	private double speed;
	private Animal animal;

	/**
	* Sets an obstacle as an actor, allows movements.
	* <pre>
	* Methods:<br>act(long now)<br>getImage(String address)
	* </pre>
    *
    * @param  	imageLink
	* @param  	xpos
	* @param  	ypos
	* @param  	s
	* @param	animal
	* @see		Animal
	*/
	public Obstacle(String imageLink, int xpos, int ypos, double s, Animal animal) {
		this.animal = animal;
		this.speed = s;
		setImage(getImage(imageLink));
		setX(xpos);
		setY(ypos);
	}

	@Override
	public Image getImage(String address) {
		String random = "";
		if (address.equals("car"))
			random = Integer.toString((int)Math.random() * 3 + 1);
		if (speed > 0) 
			return new Image("file:media/images/obstacles/" + address + random + ".png", 0, SIZE_Y, true, true);
		else return new Image("file:media/images/obstacles/" + address + random + "left.png", 0, SIZE_Y, true, true);
	}

	/**
	* Every frame, this method:<p>
	* - Checks out of bounds<p>
	* - Checks for vertical movements
    *
	* @param  now current frame
	*/
	@Override
	public void act(long now) {
		move(speed, 0);

		/** Checks out of bounds */
		int rng = (int)(Math.random() * 3);
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
}
