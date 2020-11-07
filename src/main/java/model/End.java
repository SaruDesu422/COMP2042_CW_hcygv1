package model;

import javafx.scene.image.Image;

public class End extends Actor{
	
	private final int END_SIZE = 60;
	boolean activated = false;
	@Override
	public void act(long now) {}

	/**
	* This method sets image of end.
	*
	* @param  x  x coordinate
	* @param  y  y coordinate
	* @see       image of end
	*/
	public End(int x, int y) {
		setX(x);
		setY(y);
		setImage(new Image("file:media/images/end/End.png", END_SIZE, END_SIZE, true, true));
	}

	/**
	* This method sets image of end when player reaches it
	* and sets it to activated.
	*
	* @see       Image of end with frog
	*/
	public void setEnd() {
		setImage(new Image("file:media/images/end/frogEnd.png", END_SIZE, END_SIZE, true, true));
		activated = true;
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
