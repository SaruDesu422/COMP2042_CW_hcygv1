package model;

import view.Game;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Animal extends Actor {

	private final int CHANGE_SCORE = 50;
	private final int START_X = 300;
	private final double START_Y = 706.466666;
	private final int IMAGE_SIZE = 40;
	private final int MAX_Y = 800;
	private final double MOVEMENT_Y = 26.666666;
	private final double MOVEMENT_X = 21.333333;

	Game game;

	int points = 0;
	int end = 0;
	private boolean second = false;
	boolean noMove = false;
	boolean carDeath = false;
	boolean waterDeath = false;
	boolean changeScore = false;
	boolean moved = false;
	int carD = 0;
	int waterD = 0;
	int restMove = 0;
	int upMovement = 0;
	double y = MAX_Y;
	ArrayList<End> inter = new ArrayList<End>();

	/**
	* This method initializes the image of the player and
	* the position of the player after each action.
	*
	* @return      Returns boolean
	*/
	public Animal(Game game) {
		// set image and starting position
		this.game = game;
		setImage(new Image("file:media/images/frog/frogUp.png", IMAGE_SIZE, 0, true, true));
		setX(START_X);
		setY(START_Y);
		Image imgW1 = new Image("file:media/images/frog/frogUp.png", IMAGE_SIZE, 0, true, true);
		Image imgA1 = new Image("file:media/images/frog/frogLeft.png", 0, IMAGE_SIZE, true, true);
		Image imgS1 = new Image("file:media/images/frog/frogDown.png", IMAGE_SIZE, 0, true, true);
		Image imgD1 = new Image("file:media/images/frog/frogRight.png", 0, IMAGE_SIZE, true, true);
		Image imgW2 = new Image("file:media/images/frog/frogUpJump.png", IMAGE_SIZE, 0, true, true);
		Image imgA2 = new Image("file:media/images/frog/frogLeftJump.png", 0, IMAGE_SIZE, true, true);
		Image imgS2 = new Image("file:media/images/frog/frogDownJump.png", IMAGE_SIZE, 0, true, true);
		Image imgD2 = new Image("file:media/images/frog/frogRightJump.png", 0, IMAGE_SIZE, true, true);

		// configure position WHEN key is pressed
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event){
				if (!noMove) {
					if (second) {
						if (event.getCode() == KeyCode.W) {	  
							move(0, -MOVEMENT_Y);
							upMovement++;
							changeScore = false;
							setImage(imgW1);
							second = false;
						}
						else if (event.getCode() == KeyCode.A) {	            	
							move(-MOVEMENT_X, 0);
							setImage(imgA1);
							second = false;
						}
						else if (event.getCode() == KeyCode.S) {	            	
							move(0, MOVEMENT_Y);
							upMovement--;
							setImage(imgS1);
							second = false;
						}
						else if (event.getCode() == KeyCode.D) {	            	
							move(MOVEMENT_X, 0);
							setImage(imgD1);
							second = false;
						}
					}
					else if (event.getCode() == KeyCode.W) {	            	
						move(0, -MOVEMENT_Y);
						upMovement++;
						setImage(imgW2);
						second = true;
					}
					else if (event.getCode() == KeyCode.A) {	            	
						move(-MOVEMENT_X, 0);
						setImage(imgA2);
						second = true;
					}
					else if (event.getCode() == KeyCode.S) {	            	
						move(0, MOVEMENT_Y);
						upMovement--;
						setImage(imgS2);
						second = true;
					}
					else if (event.getCode() == KeyCode.D) {	            	
						move(MOVEMENT_X, 0);
						setImage(imgD2);
						second = true;
					}
				}
			}
		});	
		// configure position AFTER key is pressed
		setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				// if not allowed to move, do nothing
				if (!noMove) {
					// change score when moving forward
					if (event.getCode() == KeyCode.W) {	  
						if (getY() < y) {
							changeScore = true;
							y = getY();
							points+=10;
						}
						move(0, -MOVEMENT_Y);
						upMovement++;
						setImage(imgW1);
						second = false;
					}
					else if (event.getCode() == KeyCode.A) {	            	
						move(-MOVEMENT_X, 0);
						setImage(imgA1);
						second = false;
					}
					else if (event.getCode() == KeyCode.S) {	            	
						move(0, MOVEMENT_Y);
						upMovement--;
						setImage(imgS1);
						second = false;
					}
					else if (event.getCode() == KeyCode.D) {	            	
						move(MOVEMENT_X, 0);
						setImage(imgD1);
						second = false;
					}
				}
			}
		});
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
		// out of range vertically
		if (getY() < 0 || getY() > 734) {
			setX(START_X);
			setY(START_Y);
		}

		// out of range left
		if (getX() < 0) {
			move(MOVEMENT_X * 2, 0);
		}

		// out of range right
		if (getX() > 600) {
			move(-MOVEMENT_X * 2, 0);
		}

		// reach rest
		if (game.checkRestInfo()) {
			System.out.printf("%d, %d \n", upMovement, game.getRest() * 2);
			if (upMovement == game.getRest() * 2 && restMove == 0) {
				System.out.println(restMove);
				game.setRestIndex();
				restMove = game.getRest() * 2;
			}
		}

		// move according to rest
		if (restMove > 0) {
			move(0, MOVEMENT_Y);
			restMove--;
			if (restMove == 0) {
				moved = true;
			}
		}
		
		// reset restMove to MAX_Y
		if (moved) {
			y = MAX_Y;
			upMovement = 0;
			moved = false;
		}

		// configure car death image
		if (carDeath) {
			// set move to not allowed
			noMove = true;
			if ((now) % 11 == 0) {
				carD++;
			}
			if (carD == 4) {
				setX(START_X);
				setY(START_Y);
				upMovement = 0;
				carDeath = false;
				carD = 0;
				setImage(new Image("file:media/images/froggerUp.png", IMAGE_SIZE, IMAGE_SIZE, true, true));
				noMove = false;
				if (points > CHANGE_SCORE) {
					points -= CHANGE_SCORE;
					changeScore = true;
				}
			}
			else {
				setImage(new Image("file:media/images/cardeath" + carD + ".png", IMAGE_SIZE, IMAGE_SIZE, true, true));
			}
		}
		// configure water death image
		if (waterDeath) {
			noMove = true;
			if ((now) % 11 == 0) {
				waterD++;
			}
			if (waterD == 5) {
				setX(START_X);
				setY(START_Y);
				upMovement = 0;
				waterDeath = false;
				waterD = 0;
				setImage(new Image("file:media/images/froggerUp.png", IMAGE_SIZE, IMAGE_SIZE, true, true));
				noMove = false;
				if (points > CHANGE_SCORE) {
					points -= CHANGE_SCORE;
					changeScore = true;
				}
			}
			else {
				setImage(new Image("file:media/images/waterdeath" + waterD + ".png", IMAGE_SIZE,IMAGE_SIZE , true, true));
			}
		}
		// car death
		if (getIntersectingObjects(Obstacle.class).size() >= 1) {
			carDeath = true;
		}
		// stand on log
		if (getIntersectingObjects(Log.class).size() >= 1 && !noMove) {
			if(getIntersectingObjects(Log.class).get(0).getLeft())
				move(-1, 0);
			else
				move (.375, 0);
		}
		// stand on turtle
		else if (getIntersectingObjects(Turtle.class).size() >= 1 && !noMove) {
			move(-.5, 0);
		}
		// death by wet turtle
		else if (getIntersectingObjects(WetTurtle.class).size() >= 1) {
			if (getIntersectingObjects(WetTurtle.class).get(0).isSunk()) {
				// if at water
				waterDeath = true;
			} else {
				move(-.5, 0);
			}
		}
		// reach end
		else if (getIntersectingObjects(End.class).size() >= 1) {
			inter = (ArrayList<End>) getIntersectingObjects(End.class);
			// if end already activated
			if (getIntersectingObjects(End.class).get(0).isActivated()) {
				end--;
				points -= CHANGE_SCORE;
			}
			points += CHANGE_SCORE;
			changeScore = true;
			y = MAX_Y;
			// set end to activated
			getIntersectingObjects(End.class).get(0).setEnd();
			end++;
			setX(START_X);
			setY(START_Y);
		}
		// if at water area and not standing on anything
		else if (getY() < 413){
			waterDeath = true;
		}
	}

	public int getRestMove() {
		return restMove;
	}

	/**
	* This method returns true if all ends are activated.
	*
	* @return      Returns true if all ends are activated
	*/
	public boolean getStop() {
		return end == 5;
	}

	/**
	* This method returns points.
	*
	* @return      Returns points
	*/
	public int getPoints() {
		return points;
	}

	/**
	* This method only resets score when the score can be changed.
	* This method returns true if score is reset and false otherwise.
	*
	* @return      Returns true if score is reset
	*/
	public boolean resetScore() {
		if (changeScore) {
			changeScore = false;
			return true;
		}
		return false;
	}
	
}