package model;

import javafx.scene.image.Image;

public class Digit extends Actor{

	private final int DIM = 30;
	Image im1;

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
		im1 = new Image("file:src/main/java/view/images/" + n + ".png", DIM, DIM, true, true);
		setImage(im1);
		setX(x);
		setY(y);
	}
	
	@Override
	public void act(long now) {}
}
