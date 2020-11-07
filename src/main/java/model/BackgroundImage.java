package model;

import javafx.scene.image.Image;

public class BackgroundImage extends Actor{

	private final int MAX_X = 600;
	private final int STEP = 50;

	Animal animal;

	/**
	* This method sets background image.
	*
	* @param  imageLink  link of image
	* @see               Image of background
	*/
	public BackgroundImage(String imageLink, int y, Animal animal) {
		this.animal = animal;
		setY(y);
		setX(0);
		if (imageLink == "file:media/images/background/endBackground.png") {
			setImage(new Image(imageLink, MAX_X, 0, true, true));
		} else {
			setImage(new Image(imageLink, MAX_X, STEP, false, true));
		}
	}
	
	/**
	* This method sets background image.
	*
	* @param  imageLink  link of image
	* @see               Image of background
	*/
	public BackgroundImage(String imageLink) {
		setImage(new Image(imageLink, MAX_X, 0, true, true));
	}

	@Override
	public void act(long now) {
		if (animal == null) return;
		if (animal.getRestMove() > 0)
			move(0, STEP);
	}
}
