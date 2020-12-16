package model;

import java.lang.Math;

import javafx.scene.image.Image;

public class Turtle extends Actor{
	
	private final int LIMIT_LEFT = -130;
	private final int LIMIT_RIGHT = 600;
	private final int SIZE_X = 130;
	private final int SIZE_Y = 50;
	private final int STEP = 50;
	private final Image IMG_TURTLE1 = getImage("turtle1");
	private final Image IMG_TURTLE2 = getImage("turtle2");
	private final Image IMG_TURTLE3 = getImage("turtle3");
	private final Image IMG_TURTLE4 = getImage("turtle4");
	
	private double speed;
	private int frame = 0;
	private Animal animal;

	boolean bool = true;

	/**
	 * Sets a turtle as an actor, allows movements.
	 * 
	 * @param	x
	 * @param	y
	 * @param	speed
	 * @param	animal
	 * @see		Animal
	 */
	public Turtle(int x, int y, double speed, Animal animal) {
		this.animal = animal;
		this.speed = speed;
		setX(x);
		setY(y);
		setImage(IMG_TURTLE1);
	}

	@Override
	public Image getImage(String address) {
		if (speed > 0)
			return new Image("file:media/images/turtle/" + address + ".png", SIZE_X, SIZE_Y, false, true);
		return new Image("file:media/images/turtle/" + address + "left.png", SIZE_X, SIZE_Y, false, true);
	}
	
	/**
	 * Every frame, this method sets image according to the frame,
	 * checks for out of bounds and vertical movements.
	 * 
	 * @param	now  current frame
	 */
	@Override
	public void act(long now) {
		move(speed, 0);

		// Animation 
		if (frame == 0 || frame == 16)
			setImage(IMG_TURTLE2);
		else if (frame == 4 || frame == 12)
			setImage(IMG_TURTLE3);
		else if (frame == 8)
			setImage(IMG_TURTLE4);
		else if (frame == 20) {
			setImage(IMG_TURTLE1);
			frame = 0;
		}
		frame++;

		// Checks out of bounds 
		double rng = (int)(Math.random() * 3);
		if (getX() > LIMIT_RIGHT && speed > 0)
			setX(LIMIT_LEFT - rng * 10);
		if (getX() < LIMIT_LEFT && speed < 0)
			setX(LIMIT_RIGHT + rng * 10);
		
		// Checks for vertical movements and move accordingly 
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
