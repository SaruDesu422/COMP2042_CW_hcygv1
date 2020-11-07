package model;

import javafx.scene.image.Image;

public class BackgroundImage extends Actor{

	private final int MAX_X = 600;
	private final int MAX_Y = 800;
	private final double MOVEMENT_Y = 26.666666;

	Animal animal;

	/**
	* This method sets background image.
	*
	* @param  imageLink  link of image
	* @see               Image of background
	*/
	public BackgroundImage(String imageLink, Animal animal) {
		this.animal = animal;
		if (imageLink == "file:media/images/background/endBackground.png") {
			setY(96);
			setImage(new Image(imageLink, MAX_X, 0, true, true));
		} else {
			setImage(new Image(imageLink, MAX_X, MAX_Y, false, true));
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
			move(0, MOVEMENT_Y * 2);
	}
}
