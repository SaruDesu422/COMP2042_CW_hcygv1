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
	Animal animal;

	boolean bool = true;
	boolean sunk = false;
	Image turtle1;
	Image turtle2;
	Image turtle3;
	Image turtle4;
	Image turtle5;
	Image turtle6;
	Image turtle7;
	Image turtle8;
	Image turtle9;

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
		if (s > 0) {
			turtle1 = new Image("file:media/images/turtle/turtle1.png", SIZE_X, SIZE_Y, false, true);
			turtle2 = new Image("file:media/images/turtle/turtle2.png", SIZE_X, SIZE_Y, false, true);
			turtle3 = new Image("file:media/images/turtle/turtle3.png", SIZE_X, SIZE_Y, false, true);
			turtle4 = new Image("file:media/images/turtle/turtle4.png", SIZE_X, SIZE_Y, false, true);
			turtle5 = new Image("file:media/images/turtle/wetturtle1.png", SIZE_X, SIZE_Y, false, true);
			turtle6 = new Image("file:media/images/turtle/wetturtle2.png", SIZE_X, SIZE_Y, false, true);
			turtle7 = new Image("file:media/images/turtle/wetturtle3.png", SIZE_X, SIZE_Y, false, true);
			turtle8 = new Image("file:media/images/turtle/wetturtle4.png", SIZE_X, SIZE_Y, false, true);
		} else {
			turtle1 = new Image("file:media/images/turtle/turtle1left.png", SIZE_X, SIZE_Y, false, true);
			turtle2 = new Image("file:media/images/turtle/turtle2left.png", SIZE_X, SIZE_Y, false, true);
			turtle3 = new Image("file:media/images/turtle/turtle3left.png", SIZE_X, SIZE_Y, false, true);
			turtle4 = new Image("file:media/images/turtle/turtle4left.png", SIZE_X, SIZE_Y, false, true);
			turtle5 = new Image("file:media/images/turtle/wetturtle1left.png", SIZE_X, SIZE_Y, false, true);
			turtle6 = new Image("file:media/images/turtle/wetturtle2left.png", SIZE_X, SIZE_Y, false, true);
			turtle7 = new Image("file:media/images/turtle/wetturtle3left.png", SIZE_X, SIZE_Y, false, true);
			turtle8 = new Image("file:media/images/turtle/wetturtle4left.png", SIZE_X, SIZE_Y, false, true);
		}
		turtle9 = new Image("file:media/images/turtle/turtlesunk.png", SIZE_X, SIZE_Y, false, true);
		setX(xpos);
		setY(ypos);
		speed = s;
		setImage(turtle1);
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
				setImage(turtle2);
			} else if (frame%20 == 4 || frame%20 == 12) {
				setImage(turtle3);
			} else if (frame%20 == 8) {
				setImage(turtle4);
			} else if (frame%20 == 20) {
				setImage(turtle1);
			}
		} else if (frame == wait || frame == wait * 2 + 8) {
			setImage(turtle5);
			sunk = false;
		} else if (frame == wait + 4 || frame == wait * 2 + 4) {
			setImage(turtle6);
			sunk = false;
		} else if (frame == wait + 8 || frame == wait * 2) {
			setImage(turtle7);
			sunk = false;
		} else if (frame == wait + 12 || frame == wait * 2 - 4) {
			setImage(turtle8);
			sunk = false;
		} else if (frame == wait + 16) {
			setImage(turtle9);
			sunk = true;
		} else if (frame == wait * 2 + 12) {
			setImage(turtle1);
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
		if (animal.getRestMove() > 0)
			move(0, STEP);
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
