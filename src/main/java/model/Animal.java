package model;

import java.util.ArrayList;

import javafx.event.EventHandler;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Animal extends Actor {

	private final int CHANGE_SCORE = 50;
	private final int START_X = 300;
	private final double START_Y = 679.8;
	private final int IMAGE_SIZE = 40;
	private final int MAX_Y = 800;
	private final double MOVEMENT_Y = 13.3333333*2;
	private final double MOVEMENT_X = 10.666666*2;

	int points = 0;
	int end = 0;
	private boolean repeat = false;
	boolean noMove = false;
	boolean carDeath = false;
	boolean waterDeath = false;
	boolean changeScore = false; 
	int carD = 0;
	int waterD = 0;
	double y = MAX_Y;
	ArrayList<End> inter = new ArrayList<End>();

	/**
	* This method initializes the image of the player and
	* the position of the player after each action.
	*
	* @return      Returns boolean
	*/
	public Animal(String imageLink) {
		/* set image and starting position */
		setImage(new Image(imageLink, IMAGE_SIZE, IMAGE_SIZE, true, true));
		setX(START_X);
		setY(START_Y + MOVEMENT_Y);
		Image imgW1 = new Image("file:src/main/java/view/images/froggerUp.png", IMAGE_SIZE, IMAGE_SIZE, true, true);
		Image imgA1 = new Image("file:src/main/java/view/images/froggerLeft.png", IMAGE_SIZE, IMAGE_SIZE, true, true);
		Image imgS1 = new Image("file:src/main/java/view/images/froggerDown.png", IMAGE_SIZE, IMAGE_SIZE, true, true);
		Image imgD1 = new Image("file:src/main/java/view/images/froggerRight.png", IMAGE_SIZE, IMAGE_SIZE, true, true);
		Image imgW2 = new Image("file:src/main/java/view/images/froggerUpJump.png", IMAGE_SIZE, IMAGE_SIZE, true, true);
		Image imgA2 = new Image("file:src/main/java/view/images/froggerLeftJump.png", IMAGE_SIZE, IMAGE_SIZE, true, true);
		Image imgS2 = new Image("file:src/main/java/view/images/froggerDownJump.png", IMAGE_SIZE, IMAGE_SIZE, true, true);
		Image imgD2 = new Image("file:src/main/java/view/images/froggerRightJump.png", IMAGE_SIZE, IMAGE_SIZE, true, true);
		/* configure position WHEN key is pressed */
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event){
				/* if not allowed to move, do nothing */
				if (!noMove) {
					/* configure MOVEMENT_Y when key is pressed twice */
					if (repeat) {
						if (event.getCode() == KeyCode.W) {	  
							move(0, -MOVEMENT_Y);
							changeScore = false;
							setImage(imgW1);
							repeat = false;
						}
						else if (event.getCode() == KeyCode.A) {	            	
							move(-MOVEMENT_X, 0);
							setImage(imgA1);
							repeat = false;
						}
						else if (event.getCode() == KeyCode.S) {	            	
							move(0, MOVEMENT_Y);
							setImage(imgS1);
							repeat = false;
						}
						else if (event.getCode() == KeyCode.D) {	            	
							move(MOVEMENT_X, 0);
							setImage(imgD1);
							repeat = false;
						}
					}
					else if (event.getCode() == KeyCode.W) {	            	
						move(0, -MOVEMENT_Y);
						setImage(imgW2);
						repeat = true;
					}
					else if (event.getCode() == KeyCode.A) {	            	
						move(-MOVEMENT_X, 0);
						setImage(imgA2);
						repeat = true;
					}
					else if (event.getCode() == KeyCode.S) {	            	
						move(0, MOVEMENT_Y);
						setImage(imgS2);
						repeat = true;
					}
					else if (event.getCode() == KeyCode.D) {	            	
						move(MOVEMENT_X, 0);
						setImage(imgD2);
						repeat = true;
					}
				}
			}
		});	
		/* configure position AFTER key is pressed */
		setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				/* if not allowed to move, do nothing */
				if (!noMove) {
					/* change score when moving forward */
					if (event.getCode() == KeyCode.W) {	  
						if (getY() < y) {
							changeScore = true;
							y = getY();
							points+=10;
						}
						move(0, -MOVEMENT_Y);
						setImage(imgW1);
						repeat = false;
					}
					else if (event.getCode() == KeyCode.A) {	            	
						move(-MOVEMENT_X, 0);
						setImage(imgA1);
						repeat = false;
					}
					else if (event.getCode() == KeyCode.S) {	            	
						move(0, MOVEMENT_Y);
						setImage(imgS1);
						repeat = false;
					}
					else if (event.getCode() == KeyCode.D) {	            	
						move(MOVEMENT_X, 0);
						setImage(imgD1);
						repeat = false;
					}
				}
			}
		});
	}

	/**
	* This method returns true if all ends are activated.
	*
	* @return      returns boolean of comparison end == 5
	*/
	public boolean getStop() {
		return end == 5;
	}
	
	/**
	* This method returns points.
	*
	* @return      returns points
	*/
	public int getPoints() {
		return points;
	}
	
	/**
	* This method only resets score when the score can be changed.
	* This method returns true if score is reset and false otherwise.
	*
	* @return      returns boolean of changeScore
	*/
	public boolean resetScore() {
		if (changeScore) {
			changeScore = false;
			return true;
		}
		return false;
	}
	
	/**
	* This method prevents player from going out of bound and 
	* set the image of the player after each action.
	* This method includes vertical and horizontal MOVEMENT_Ys of the player,
	* deaths by different causes and reaching the end. 
	*
	* @see         Image of player
	*/
	@Override
	public void act(long now) {
		/* out of range vertically */
		if (getY() < 0 || getY() > 734) {
			setX(START_X);
			setY(START_Y + MOVEMENT_Y);
		}
		/* out of range left */
		if (getX() < 0) {
			move(MOVEMENT_Y * 2, 0);
		}
		/* out of range right */
		if (getX() > 600) {
			move(-MOVEMENT_Y * 2, 0);
		}
		/* configure car death image */
		if (carDeath) {
			/* set move to not allowed */
			noMove = true;
			if ((now) % 11 == 0) {
				carD++;
			}
			if (carD == 4) {
				setX(START_X);
				setY(START_Y + MOVEMENT_Y);
				carDeath = false;
				carD = 0;
				setImage(new Image("file:src/main/java/view/images/froggerUp.png", IMAGE_SIZE, IMAGE_SIZE, true, true));
				noMove = false;
				if (points > CHANGE_SCORE) {
					points -= CHANGE_SCORE;
					changeScore = true;
				}
			}
			else {
				setImage(new Image("file:src/main/java/view/images/cardeath" + carD + ".png", IMAGE_SIZE, IMAGE_SIZE, true, true));
			}
		}
		/* configure water death image */
		if (waterDeath) {
			noMove = true;
			if ((now) % 11 == 0) {
				waterD++;
			}
			if (carD == 5) {
				setX(START_X);
				setY(START_Y + MOVEMENT_Y);
				waterDeath = false;
				waterD = 0;
				setImage(new Image("file:src/main/java/view/images/froggerUp.png", IMAGE_SIZE, IMAGE_SIZE, true, true));
				noMove = false;
				if (points > CHANGE_SCORE) {
					points -= CHANGE_SCORE;
					changeScore = true;
				}
			}
			else {
				setImage(new Image("file:src/main/java/view/images/waterdeath" + waterD + ".png", IMAGE_SIZE,IMAGE_SIZE , true, true));
			}
		}
		/* car death */
		if (getIntersectingObjects(Obstacle.class).size() >= 1) {
			carDeath = true;
		}
		if (getX() == 240 && getY() == 82) {}
		/* stand on log */
		if (getIntersectingObjects(Log.class).size() >= 1 && !noMove) {
			if(getIntersectingObjects(Log.class).get(0).getLeft())
				move(-2, 0);
			else
				move (.75, 0);
		}
		/* stand on turtle */
		else if (getIntersectingObjects(Turtle.class).size() >= 1 && !noMove) {
			move(-1, 0);
		}
		/* death by wet turtle */
		else if (getIntersectingObjects(WetTurtle.class).size() >= 1) {
			if (getIntersectingObjects(WetTurtle.class).get(0).isSunk()) {
				waterDeath = true;
			} else {
				move(-1, 0);
			}
		}
		/* reach end */
		else if (getIntersectingObjects(End.class).size() >= 1) {
			inter = (ArrayList<End>) getIntersectingObjects(End.class);
			/* if end already activated */
			if (getIntersectingObjects(End.class).get(0).isActivated()) {
				end--;
				points -= CHANGE_SCORE;
			}
			points += CHANGE_SCORE;
			changeScore = true;
			y = MAX_Y;
			/* set end to activated */
			getIntersectingObjects(End.class).get(0).setEnd();
			end++;
			setX(START_X);
			setY(START_Y + MOVEMENT_Y);
		}
		/* if at water area and not standing on anything */
		else if (getY() < 413){
			waterDeath = true;
		}
	}
}
