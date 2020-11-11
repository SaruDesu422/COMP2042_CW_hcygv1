package model;

import javafx.scene.image.Image;

public class BackgroundImage extends Actor{

	private final int MAX_X = 600;
	private final int STEP = 50;

	private Animal animal;
	private final String IMG_ENDBACKGROUND = "endBackground";
	private final String IMG_STARTBACKGROUND = "startBackground";

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
		setImage(getImage(imageLink));
	}

	@Override
	public Image getImage(String address) {
		if (address.matches(IMG_ENDBACKGROUND + "||" + IMG_STARTBACKGROUND))
			return new Image("file:media/images/background/" + address + ".png", MAX_X, 0, true, true);
		else return new Image("file:media/images/background/" + address + ".png", MAX_X, STEP, false, true);
	}

	public BackgroundImage(String imageLink) {
		setImage(new Image("file:media/images/background/" + imageLink + ".png", MAX_X, 0, true, true));
	}

	@Override
	public void act(long now) {
		if (animal == null) return;
		if (animal.isMoveDown())
			move(0, STEP);
		if (animal.isMoveBG())
			move(0, -STEP * animal.getDownMovement());
	}
}
