package model;

import java.lang.Math;

import javafx.scene.image.Image;

public class WetTurtle extends Actor{
	
	private final int LIMIT_LEFT = -130;
	private final int LIMIT_RIGHT = 600;
	private final int SIZE_X = 130;
	private final int SIZE_Y = 50;
	private final int STEP = 50;
	private final Image IMG_TURTLE1 = getImage("turtle1");
	private final Image IMG_TURTLE2 = getImage("turtle2");
	private final Image IMG_TURTLE3 = getImage("turtle3");
	private final Image IMG_TURTLE4 = getImage("turtle4");
	private final Image IMG_WETTURTLE1 = getImage("wetturtle1");
	private final Image IMG_WETTURTLE2 = getImage("wetturtle2");
	private final Image IMG_WETTURTLE3 = getImage("wetturtle3");
	private final Image IMG_WETTURTLE4 = getImage("wetturtle4");

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
		setImage(IMG_TURTLE1);
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
				setImage(IMG_TURTLE2);
			else if (frame%20 == 4 || frame%20 == 12)
				setImage(IMG_TURTLE3);
			else if (frame%20 == 8)
				setImage(IMG_TURTLE4);
			else if (frame%20 == 20)
				setImage(IMG_TURTLE1);
		} else if (frame == wait || frame == wait * 2 + 4) {
			setImage(IMG_WETTURTLE1);
			sunk = false;
		} else if (frame == wait + 4 || frame == wait * 2) {
			setImage(IMG_WETTURTLE2);
			sunk = false;
		} else if (frame == wait + 8 || frame == wait * 2 - 4) {
			setImage(IMG_WETTURTLE3);
			sunk = false;
		} else if (frame == wait + 12) {
			setImage(IMG_WETTURTLE4);
			sunk = true;
		} else if (frame == wait * 2 + 8) {
			setImage(IMG_TURTLE1);
			sunk = false;
			frame = 0;
		}
		frame++;

		/** Checks out of bounds */
		double rng = (int)(Math.random() * 3);
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
