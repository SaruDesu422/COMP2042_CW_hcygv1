package model;

import javafx.scene.image.Image;

public class Digit extends Actor{
	private final int DIM = 30;
	
	/**
	* Sets a digit as an actor.
	* <pre>
	* Methods:<br>getImage(String address)
	* </pre>
    *
	* @param  	number
	* @param  	x
	* @param  	y
	*/
	public Digit(int number, int x, int y) {
		setImage(getImage(Integer.toString(number)));
		setX(x);
		setY(y);
	}

	@Override
	public Image getImage(String address) {
		return new Image("file:media/images/digits/" + address + ".png", DIM, DIM, true, true);
	}
	
	@Override
	public void act(long now) {}
}
