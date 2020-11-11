package model;

import view.Game;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Animal extends Actor {

	private final int CHANGE_SCORE = 50;
	private final int START_X = 300;
	private final double START_Y = 701;
	private final int IMAGE_SIZE = 35;
	private final int MAX_Y = 800;
	private final int MOVEMENT_Y = 25;
	private final int MOVEMENT_X = 22;

	Game game;

	private boolean noMove = false;
	private boolean carDeath = false;
	private boolean waterDeath = false;
	private boolean changeScore = false;
	private boolean moveDown = false;
	private boolean moveBG = false;
	private boolean secondFrame = false;
	private boolean moving = false;
	int points = 0;
	int end = 0;
	int carD = 0;
	int waterD = 0;
	int upMovement = 1;
	int downMovement = 0;
	int frame = 0;
	int frogFrame = 0;
	int endInfo = 0;
	double y = MAX_Y;
	ArrayList<End> intersecting = new ArrayList<End>();
	List<Integer> water = new ArrayList<Integer>();

	/**
	* This method initializes the image of the player and
	* the position of the player after each action.
	*
	* @return      Returns boolean
	*/
	public Animal(Game game) {
		// set image and starting position
		this.game = game;
		this.water = game.getWater();
		this.endInfo = game.getEnd();
		setX(START_X);
		setY(START_Y);
		// Image imgW1 = new Image("file:media/images/frog/hitbox.png", IMAGE_SIZE, 0, true, true);
		// Image imgA1 = new Image("file:media/images/frog/hitbox.png", 0, IMAGE_SIZE, true, true);
		// Image imgS1 = new Image("file:media/images/frog/hitbox.png", IMAGE_SIZE, 0, true, true);
		// Image imgD1 = new Image("file:media/images/frog/hitbox.png", 0, IMAGE_SIZE, true, true);
		// Image imgW2 = new Image("file:media/images/frog/hitbox.png", IMAGE_SIZE, 0, true, true);
		// Image imgA2 = new Image("file:media/images/frog/hitbox.png", 0, IMAGE_SIZE, true, true);
		// Image imgS2 = new Image("file:media/images/frog/hitbox.png", IMAGE_SIZE, 0, true, true);
		// Image imgD2 = new Image("file:media/images/frog/hitbox.png", 0, IMAGE_SIZE, true, true);
		Image imgW1 = new Image("file:media/images/frog/frogUp.png", IMAGE_SIZE, 0, true, true);
		Image imgA1 = new Image("file:media/images/frog/frogLeft.png", 0, IMAGE_SIZE, true, true);
		Image imgS1 = new Image("file:media/images/frog/frogDown.png", IMAGE_SIZE, 0, true, true);
		Image imgD1 = new Image("file:media/images/frog/frogRight.png", 0, IMAGE_SIZE, true, true);
		Image imgW2 = new Image("file:media/images/frog/frogUpJump.png", IMAGE_SIZE, 0, true, true);
		Image imgA2 = new Image("file:media/images/frog/frogLeftJump.png", 0, IMAGE_SIZE, true, true);
		Image imgS2 = new Image("file:media/images/frog/frogDownJump.png", IMAGE_SIZE, 0, true, true);
		Image imgD2 = new Image("file:media/images/frog/frogRightJump.png", 0, IMAGE_SIZE, true, true);
		setImage(imgW1);

		setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (!noMove) {
					moving = true;
					changeScore = false;
					if (event.getCode() == KeyCode.W) {
						move(0, -MOVEMENT_Y);
						upMovement++;
						setImage(imgW2);
					} else if (event.getCode() == KeyCode.A) {
						move(-MOVEMENT_X, 0);
						setImage(imgA2);
					} else if (event.getCode() == KeyCode.S) {
						move(0, MOVEMENT_Y);
						upMovement--;
						setImage(imgS2);
					} else if (event.getCode() == KeyCode.D) {
						move(MOVEMENT_X, 0);
						setImage(imgD2);
					}
				}
			}
		});

		setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (moving) {
					if (event.getCode() == KeyCode.W) {
						if (getY() < y) {
							changeScore = true;
							y = getY();
							points += 10;
						}
							move(0, -MOVEMENT_Y);
						setImage(imgW1);
					} else if (event.getCode() == KeyCode.A) {
						move(-MOVEMENT_X, 0);
						setImage(imgA1);
					} else if (event.getCode() == KeyCode.S) {
						move(0, MOVEMENT_Y);
						setImage(imgS1);
					} else if (event.getCode() == KeyCode.D) {
						move(MOVEMENT_X, 0);
						setImage(imgD1);
					}
					moving = false;
				}
			}
		});
	}

	/**
	* This method prevents player from going out of bound and 
	* set the image of the player after each action.
	* This method includes vertical and horizontal movements of the player,
	* deaths by different causes and reaching the end. 
	*
	* @see         Image of player
	*/
	@Override
	public void act(long now) {
		checkOutOfBounds();

		if (moveDown) {
			move(0, MOVEMENT_Y * 2);
			y += MOVEMENT_Y * 2;
			moveDown = false;
			downMovement++;
			endInfo--;
		}

		if (upMovement == 6) {
			if (endInfo > 12) {
				moveDown = true;
			}
		}

		if (carDeath) {
			frame++;
			noMove = true;
			if (frame % 12 == 0) {
				carD++;
			}
			if (frame == 47)
				moveBG = true;
			if (carD == 4) {
				moveBG = false;
				endInfo += downMovement;
				setX(START_X);
				setY(START_Y);
				upMovement = 1;
				downMovement = 0;
				carDeath = false;
				carD = 0;
				setImage(new Image("file:media/images/frog/frogUp.png", IMAGE_SIZE, IMAGE_SIZE, true, true));
				noMove = false;
				if (points > CHANGE_SCORE) {
					points -= CHANGE_SCORE;
					changeScore = true;
				}
				frame = 0;
			} else {
				setImage(new Image("file:media/images/death/carDeath" + carD + ".png", IMAGE_SIZE, IMAGE_SIZE, true, true));
			}
		}

		if (waterDeath) {
			frame++;
			noMove = true;
			if (frame % 9 == 0) {
				waterD++;
			}
			if (frame == 44)
				moveBG = true;
			if (waterD == 5) {
				moveBG = false;
				endInfo += downMovement;
				setX(START_X);
				setY(START_Y);
				upMovement = 1;
				downMovement = 0;
				waterDeath = false;
				waterD = 0;
				setImage(new Image("file:media/images/frog/frogUp.png", IMAGE_SIZE, IMAGE_SIZE, true, true));
				noMove = false;
				if (points > CHANGE_SCORE) {
					points -= CHANGE_SCORE;
					changeScore = true;
				}
				frame = 0;
			} else {
				setImage(new Image("file:media/images/death/waterDeath" + waterD + ".png", IMAGE_SIZE,IMAGE_SIZE , true, true));
			}
		}
		checkIntersect();
	}

	private void checkIntersect() {
		if (getIntersectingObjects(End.class).size() >= 1 || secondFrame) {
			secondFrame = !secondFrame;
			noMove = true;
			if (secondFrame == false) {
				moveBG = false;
				setX(START_X);
				setY(START_Y);
				move(0, MOVEMENT_Y);
				upMovement = 1;
				downMovement = 0;
				noMove = false;
			} else {
				if (getIntersectingObjects(End.class).get(0).isActivated()) {
					end--;
					points -= CHANGE_SCORE;
				}
				getIntersectingObjects(End.class).get(0).setEnd();
				end++;
				endInfo += downMovement;
				points += CHANGE_SCORE;
				changeScore = true;
				y = MAX_Y;
				moveBG = true;
			}
		} else if (getIntersectingObjects(Obstacle.class).size() >= 1) {
			carDeath = true;
		} else if (getIntersectingObjects(Log.class).size() >= 1 && !noMove) {
			move(getIntersectingObjects(Log.class).get(0).getSpeed() / 2, 0);
		} else if (getIntersectingObjects(Turtle.class).size() >= 1 && !noMove) {
			move(getIntersectingObjects(Turtle.class).get(0).getSpeed() / 2, 0);
		} else if (getIntersectingObjects(WetTurtle.class).size() >= 1) {
			if (getIntersectingObjects(WetTurtle.class).get(0).isSunk()) {
				waterDeath = true;
			} else {
				move(getIntersectingObjects(WetTurtle.class).get(0).getSpeed() / 2, 0);
			}
		} else if (upMovement - 1 >= 0) {
			if (water.get(upMovement - 1) == 1)
				waterDeath = true;
		}
	}

	private void checkOutOfBounds() {
		if (getY() < 0 || getY() > START_Y + MOVEMENT_Y) {
			setX(START_X);
			setY(START_Y);
			upMovement++;
			System.out.println(upMovement);
		}
		if (getX() < 0) {
			move(MOVEMENT_X * 2, 0);
		}
		if (getX() > 600) {
			move(-MOVEMENT_X * 2, 0);
		}
	}

	public boolean getMoveBG() {
		return moveBG;
	}

	public int getDownMovement() {
		return downMovement;
	}

	public boolean getMoveDown() {
		return moveDown;
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