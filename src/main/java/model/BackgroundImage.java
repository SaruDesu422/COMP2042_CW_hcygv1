package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BackgroundImage extends Actor{

	private final int MAX_X = 600;
	private final int STEP = 50;
	private ImageView endImgView;

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
		if (imageLink.equals("file:media/images/background/endBackground.png")) {
			Image endImg = new Image(imageLink, MAX_X, 0, true, true);
			endImgView = new ImageView(endImg);
			setImage(endImg);
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
	
	public int getEnd() {
		return (int)endImgView.getY();
	}

	@Override
	public void act(long now) {
		if (animal == null) return;
		if (animal.getRestMove() > 0)
			move(0, STEP);
	}
}
