package model;

import javafx.scene.image.Image;

public class Turtle extends Actor{
	private final int SIZE = 130;
	Image turtle1;
	Image turtle2;
	Image turtle3;
	private int speed;
	boolean bool = true;

	/**
	* This method returns position of certain object.
    *
    * @param  cls  array of object location
	* @return      Returns array of position of an object
	*/
	public Turtle(int xpos, int ypos, int s) {
		turtle1 = new Image("file:src/main/java/view/images/TurtleAnimation1.png", SIZE, SIZE, true, true);
		turtle2 = new Image("file:src/main/java/view/images/TurtleAnimation2.png", SIZE, SIZE, true, true);
		turtle3 = new Image("file:src/main/java/view/images/TurtleAnimation3.png", SIZE, SIZE, true, true);
		setX(xpos);
		setY(ypos);
		speed = s;
		setImage(turtle2);
	}
	
	/**
	* This method configures actions and image of turtle.
    *
    * @param  now  current frame
	* @see         movement of turtle
	*/
	@Override
	public void act(long now) {
		if (now/900000000  % 3 ==0) {
			setImage(turtle2);
		}
		else if (now/900000000 % 3 == 1) {
			setImage(turtle1);
		}
		else if (now/900000000 %3 == 2) {
			setImage(turtle3);
		}
		move(speed , 0);
		if (getX() > 600 && speed>0)
			setX(-200);
		if (getX() < -75 && speed<0)
			setX(600);
	}
}
