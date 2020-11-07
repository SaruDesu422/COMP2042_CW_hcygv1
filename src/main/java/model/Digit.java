package model;

import javafx.scene.image.Image;

public class Digit extends Actor{
	private final int DIM = 30;
	Image digit;
	@Override
	public void act(long now) {}
	
	/**
	* This method sets image for digits.
	*
	* @param  n  digit
	* @param  x  x coordinate
	* @param  y  y coordinate
	* @return    Start button background
	* @see       Image of end
	*/
	public Digit(int n, int x, int y) {
		digit = new Image("file:media/images/digits/" + n + ".png", DIM, DIM, true, true);
		setImage(digit);
		setX(x);
		setY(y);
	}
}
