package model;

import view.Game;
import controller.AnimalController;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class Animal extends Actor {

	public final int CHANGE_SCORE = 50;
	public final int START_X = 300;
	public final double START_Y = 701;
	public final int IMAGE_SIZE = 35;
	public final int MAX_Y = 800;
	public final int MOVEMENT_Y = 25;
	public final int MOVEMENT_X = 22;
	public final String IMG_FROGUP = "frogUp";
	public final String IMG_FROGRIGHT = "frogRight";
	public final String IMG_FROGDOWN = "frogDown";
	public final String IMG_FROGLEFT = "frogLeft";
	public final String IMG_FROGUPJUMP = "frogUpJump";
	public final String IMG_FROGRIGHTJUMP = "frogRightJump";
	public final String IMG_FROGDOWNJUMP = "frogDownJump";
	public final String IMG_FROGLEFTJUMP = "frogLeftJump";
	

	private AnimalController controller;

	private boolean move;
	private boolean carDeath;
	private boolean waterDeath;
	private boolean changeScore;
	private boolean moveDown;
	private boolean moveBG;
	private boolean secondFrame;
	private boolean frameMove;
	private int points;
	private int end;
	private int deathFrame;
	private int upMovement;
	private int downMovement;
	private int frame;
	private int endInfo;
	private double highest_y;
	private List<Integer> water;

	/**
	* This method initializes the image of the player and
	* the position of the player after each action.
	*
	* @return      Returns boolean
	*/
	public Animal(Game game) {
		// set image and starting position
		water = new ArrayList<Integer>();

		this.move = true;
		this.carDeath = false;
		this.waterDeath = false;
		this.changeScore = false;
		this.moveDown = false;
		this.moveBG = false;
		this.secondFrame = false;
		this.frameMove = false;
		this.points = 0;
		this.end = 0;
		this.deathFrame = 0;
		this.upMovement = 1;
		this.downMovement = 0;
		this.frame = 0;
		this.endInfo = 0;
		this.highest_y = MAX_Y;

		this.water = game.getWater();
		this.endInfo = game.getEnd();
		setX(START_X);
		setY(START_Y);
		setImage(getImage(IMG_FROGUP));

		controller = new AnimalController(this);
		setOnKeyPressed(controller::OnKeyPressed);
		setOnKeyReleased(controller::OnKeyReleased);
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
			highest_y += MOVEMENT_Y * 2;
			moveDown = false;
			downMovement++;
			endInfo--;
		}

		if (upMovement == 6 && endInfo > 12) moveDown = true;

		if (carDeath || waterDeath) {
			frame++;
			move = false;
		}
		if ((waterDeath && frame % 9 == 0) || (carDeath && frame % 12 == 0))
			deathFrame++;
		if ((waterDeath && frame == 44) || (carDeath && frame == 47))
			moveBG = true;
		if ((waterDeath && deathFrame == 5) || (carDeath && deathFrame == 4)) {
			setStart();
			deathFrame = 0;
			setImage(getImage(IMG_FROGUP));
			move = true;
			if (points > CHANGE_SCORE) {
				points -= CHANGE_SCORE;
				changeScore = true;
			}
			if (waterDeath) waterDeath = false;
			else if (carDeath) carDeath = false;
			frame = 0;
		} else if (carDeath) setImage(getImage("carDeath"));
		else if (waterDeath) setImage(getImage("waterDeath"));
		checkIntersect();
	}

	@Override
	public Image getImage(String address) {
		if (address.matches(IMG_FROGUP + "||" + IMG_FROGDOWN + "||" + IMG_FROGUPJUMP + "||" + IMG_FROGDOWNJUMP))
			return new Image("file:media/images/frog/" + address + ".png", IMAGE_SIZE, 0, true, true);
		else if (address.contains("Death"))
			return new Image("file:media/images/death/" + address + deathFrame + ".png", IMAGE_SIZE, IMAGE_SIZE, true, true);
		else return new Image("file:media/images/frog/" + address + ".png", 0, IMAGE_SIZE, true, true);
	}

	private void checkIntersect() {
		if (getIntersectingObjects(End.class).size() >= 1 || secondFrame) {
			secondFrame = !secondFrame;
			move = false;
			if (secondFrame == false) {
				setStart();
				move(0, MOVEMENT_Y);
				move = true;
			} else {
				if (getIntersectingObjects(End.class).get(0).isActivated()) {
					end--;
					points -= CHANGE_SCORE;
				}
				getIntersectingObjects(End.class).get(0).setEnd();
				end++;
				points += CHANGE_SCORE;
				changeScore = true;
				highest_y = MAX_Y;
				moveBG = true;
			}
		} else if (getIntersectingObjects(Obstacle.class).size() >= 1) carDeath = true;
		else if (getIntersectingObjects(Log.class).size() >= 1 && move) {
			move(getIntersectingObjects(Log.class).get(0).getSpeed() / 2, 0);
		} else if (getIntersectingObjects(Turtle.class).size() >= 1 && move) {
			move(getIntersectingObjects(Turtle.class).get(0).getSpeed() / 2, 0);
		} else if (getIntersectingObjects(WetTurtle.class).size() >= 1) {
			if (getIntersectingObjects(WetTurtle.class).get(0).isSunk()) waterDeath = true;
			else move(getIntersectingObjects(WetTurtle.class).get(0).getSpeed() / 2, 0);
		} else if (upMovement - 1 >= 0) {
			if (water.get(upMovement - 1) == 1) waterDeath = true;
		}
	}

	private void checkOutOfBounds() {
		if (getY() < 0 || getY() > START_Y + MOVEMENT_Y) {
			setX(START_X);
			setY(START_Y);
			upMovement++;
			System.out.println(upMovement);
		}
		if (getX() < 0) move(MOVEMENT_X * 2, 0);
		if (getX() > 600) move(-MOVEMENT_X * 2, 0);
	}
	
	private void setStart() {
		moveBG = false;
		endInfo += downMovement;
		setX(START_X);
		setY(START_Y);
		upMovement = 1;
		downMovement = 0;
		deathFrame = 0;
	}

	public boolean resetScore() {
		if (this.changeScore) {
			this.changeScore = false;
			return true;
		}
		return false;
	}
	
	/** Accessors & Mutators **/

	public boolean isChangeScore() {
		return this.changeScore;
	}

	public void setChangeScore(boolean changeScore) {
		this.changeScore = changeScore;
	}

	public boolean isFrameMove() {
		return this.frameMove;
	}

	public void setFrameMove(boolean frameMove) {
		this.frameMove = frameMove;
	}

	public boolean isMove() {
		return this.move;
	}

	public void setMove(boolean move) {
		this.move = move;
	}
	
	public boolean isMoveBG() {
		return this.moveBG;
	}

	public boolean isMoveDown() {
		return this.moveDown;
	}

	public boolean isStop() {
		return this.end == 5;
	}

	public int getPoints() {
		return this.points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getUpMovement() {
		return this.upMovement;
	}

	public void setUpMovement(int upMovement) {
		this.upMovement = upMovement;
	}

	public double getHighestY() {
		return this.highest_y;
	}

	public void setHighestY(double highscore_y) {
		this.highest_y = highscore_y;
	}

	public int getDownMovement() {
		return this.downMovement;
	}
}