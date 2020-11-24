package model;

import java.lang.Math;

import javafx.scene.image.Image;

public class WetTurtle extends Actor{
	
	private final int LIMIT_LEFT = -130;
	private final int LIMIT_RIGHT = 600;
	private final int SIZE_X = 130;
	private final int SIZE_Y = 50;
	private final int STEP = 50;

	private double speed;
	private int frame = 0;
	private Animal animal;

	private boolean sunk = false;

	/**
	* Sets a wet turtle as an actor, allows movements.
	* <pre>
	* Methods:<br>act(long now)<br>getImage(String address)
	* </pre>
    *
	* @param  	x
	* @param  	y
	* @param  	speed
	* @param	animal
	* @see		Animal
	*/
	public WetTurtle(int xpos, int ypos, double s, Animal animal) {
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
	
	/**
	* Every frame, this method:<p>
	* - Sets image as part of animation<p>
	* - Checks out of bounds<p>
	* - Checks for vertical movements
    *
    * @param  now  current frame
	*/
	@Override
	public void act(long now) {
		move(speed, 0);

		/** Animation */
		int wait = 150;
		if (frame < wait) {
			sunk = false;
			if (frame%20 == 0 || frame%20 == 16)
				setImage(getImage("turtle2"));
			else if (frame%20 == 4 || frame%20 == 12)
				setImage(getImage("turtle3"));
			else if (frame%20 == 8)
				setImage(getImage("turtle4"));
			else if (frame%20 == 20)
				setImage(getImage("turtle1"));
		} else if (frame == wait || frame == wait * 2 + 4) {
			setImage(getImage("wetturtle1"));
			sunk = false;
		} else if (frame == wait + 4 || frame == wait * 2) {
			setImage(getImage("wetturtle2"));
			sunk = false;
		} else if (frame == wait + 8 || frame == wait * 2 - 4) {
			setImage(getImage("wetturtle3"));
			sunk = false;
		} else if (frame == wait + 12) {
			setImage(getImage("wetturtle4"));
			sunk = true;
		} else if (frame == wait * 2 + 8) {
			setImage(getImage("turtle1"));
			sunk = false;
			frame = 0;
		}
		frame++;

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
	* Accessor: boolean sunk
    *
	* @return	sunk
	*/
	public boolean isSunk() {
		return sunk;
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
