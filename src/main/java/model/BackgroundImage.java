package model;

import javafx.scene.image.Image;

public class BackgroundImage extends Actor{
	
	private final int MAX_X = 600;
	private final int MAX_Y = 800;

	/**
	* This method sets background image.
	*
	* @param  imageLink  link of image
	* @see               Image of background
	*/
	public BackgroundImage(String imageLink) {
		setImage(new Image(imageLink, MAX_X, MAX_Y, false, true));
	}
	
	@Override
	public void act(long now) {}
}
