package model;

import javafx.scene.image.Image;

public class End extends Actor{
	
	private final int END_SIZE = 60;
	private final int STEP = 50;

	private Animal animal;
	private boolean activated;

	private final String IMG_FROGEND = "frogEnd";
	private final String IMG_END = "End";

	/**
	 * Sets an end as an actor, allows vertical movements.
	 * 
	 * @param	x
	 * @param	y
	 * @param	animal
	 * @see		Animal
	 */
	public End(int x, int y, Animal animal) {
		this.animal = animal;
		this.activated = false;
		setX(x);
		setY(y);
		setImage(getImage(IMG_END));
	}

	@Override
	public Image getImage(String address) {
		return new Image("file:media/images/end/" + address + ".png", END_SIZE, END_SIZE, true, true);
	}

	/**
	 * Every frame, checks for vertical movements and move accordingly.
	 * 
	 * @param	now  current frame
	 */
	@Override
	public void act(long now) {
		if (animal.isMoveDown())
			move(0, STEP);
		if (animal.isMoveBG())
			move(0, -STEP * animal.getDownMovement());
	}

	/**
	 * Sets end to activated and change the object's image.
	 */
	public void setEnd() {
		setImage(getImage(IMG_FROGEND));
		activated = true;
	}
	
	/**
	 * Accessor: boolean activated
	 * 
	 * @return	activated
	 */
	public boolean isActivated() {
		return activated;
	}
}
