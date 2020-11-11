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
	* This method initializes the image and starting position of wet turtles.
    *
	* @param  xpos  x coordinate
	* @param  ypos  y coordinate
	* @param  s     speed
	* @see          image of wet turtle
	* @see          position of wet turtle
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
	
	public double getSpeed() {
		return speed;
	}

	/**
	* This method sets the position of wet turtle when is out of frame
	* and starts the animation for the wet turtle.
	* This method also randomizes the distance between each wet turtles.
    *
    * @param  now  current frame
	* @see         position of wet turtle
	* @see         animation of wet turtle
	*/
	@Override
	public void act(long now) {
		int wait = 150;
		if (frame < wait) {
			sunk = false;
			if (frame%20 == 0 || frame%20 == 16) {
				setImage(getImage("turtle2"));
			} else if (frame%20 == 4 || frame%20 == 12) {
				setImage(getImage("turtle3"));
			} else if (frame%20 == 8) {
				setImage(getImage("turtle4"));
			} else if (frame%20 == 20) {
				setImage(getImage("turtle1"));
			}
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
		/* wet turtle out of frame */
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

	/**
	* This method returns the value of sunk.
    *
	* @return      returns value of sunk
	*/
	public boolean isSunk() {
		return sunk;
	}
}
