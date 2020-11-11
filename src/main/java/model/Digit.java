package model;

import javafx.scene.image.Image;

public class Digit extends Actor{
	private final int DIM = 30;
	
	public Digit(int n, int x, int y) {
		setImage(getImage(Integer.toString(n)));
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
