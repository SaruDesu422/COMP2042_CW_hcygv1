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
	* This method sets image of end.
	*
	* @param  x  x coordinate
	* @param  y  y coordinate
	* @see       image of end
	*/
	public End(int x, int y, Animal animal) {
		this.animal = animal;
		this.activated = false;
		setX(x);
		setY(y);
		setImage(getImage(IMG_END));
	}

	/**
	* This method sets image of end when player reaches it
	* and sets it to activated.
	*
	* @see       Image of end with frog
	*/
	public void setEnd() {
		setImage(getImage(IMG_FROGEND));
		activated = true;
	}

	@Override
	public Image getImage(String address) {
		return new Image("file:media/images/end/" + address + ".png", END_SIZE, END_SIZE, true, true);
	}

	@Override
	public void act(long now) {
		if (animal.isMoveDown())
			move(0, STEP);
		if (animal.isMoveBG())
			move(0, -STEP * animal.getDownMovement());
	}
	
	/**
	* This method returns the value of activated.
	*
	* @return    value of activated
	*/
	public boolean isActivated() {
		return activated;
	}
}
