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
		if (imageLink.equals("file:media/images/background/endBackground.png") || imageLink.equals("file:media/images/background/startBackground.png")) {
			setImage(new Image(imageLink, MAX_X, 0, true, true));
		} else {
			setImage(new Image(imageLink, MAX_X, STEP, false, true));
		}
	}

	/**
	* This method sets stationary background image.
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
		if (animal.getMoveDown())
			move(0, STEP);
		if (animal.getMoveBG())
			move(0, -STEP * animal.getDownMovement());
	}
}
