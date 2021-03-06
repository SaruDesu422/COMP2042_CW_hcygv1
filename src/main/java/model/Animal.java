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
    private final int LAND = 0;
    private final int WATER = 1;
    private final int END = 3;
	public final Image IMG_FROGUP = getImage("frogUp");
	public final Image IMG_FROGRIGHT = getImage("frogRight");
	public final Image IMG_FROGDOWN = getImage("frogDown");
	public final Image IMG_FROGLEFT = getImage("frogLeft");
	public final Image IMG_FROGUPJUMP = getImage("frogUpJump");
	public final Image IMG_FROGRIGHTJUMP = getImage("frogRightJump");
	public final Image IMG_FROGDOWNJUMP = getImage("frogDownJump");
	public final Image IMG_FROGLEFTJUMP = getImage("frogLeftJump");

	private AnimalController controller;

	private boolean move;
	private boolean carDeath;
	private boolean waterDeath;
	private boolean moveDown;
	private boolean moveBG;
	private boolean secondEndFrame;
	private boolean frameMove;
	private int points;
	private int endActivated;
	private int deathFrame;
	private int upMovement;
	private int downMovement;
	private int frame;
	private int endInfo;
	private double highest_y;
	private List<Integer> water;
	private List<List<Integer>> backgroundInfo;
	int temp = 0;

	/**
	 * Frog that can be controlled by the player to play the game.
	 * 
	 * @param 	game
	 * @see		Game
	 */
	public Animal(Game game) {
		
		water = new ArrayList<Integer>();

		this.move = true;
		this.carDeath = false;
		this.waterDeath = false;
		this.moveDown = false;
		this.moveBG = false;
		this.secondEndFrame = false;
		this.frameMove = false;
		this.points = 0;
		this.endActivated = 0;
		this.deathFrame = 0;
		this.upMovement = 1;
		this.downMovement = 0;
		this.frame = 0;
		this.endInfo = 0;
		this.highest_y = MAX_Y;

		this.water = game.getWater();
		this.endInfo = game.getEnd();
		this.backgroundInfo = game.getBackgroundInfo();
		setX(START_X);
		setY(START_Y);
		setImage(IMG_FROGUP);

		controller = new AnimalController(this);
		setOnKeyPressed(controller::OnKeyPressed);
		setOnKeyReleased(controller::OnKeyReleased);
	}

	/**
	 * Every frame, this method checks for out of bounds,
	 * move down, intersecting objects and sets death animation.
	 * 
	 * @param	now  current frame
	 */
	@Override
	public void act(long now) {
		// Check out of Bounds 
		if (getY() > START_Y + MOVEMENT_Y) {
			setY(START_Y);
			upMovement++;
		}
		if (getX() < 0) move(MOVEMENT_X * 2, 0);
		if (getX() > 600) move(-MOVEMENT_X * 2, 0);
		
		// Check Move Down 
		if (moveDown) {
			move(0, MOVEMENT_Y * 2);
			highest_y += MOVEMENT_Y * 2;
			moveDown = false;
			downMovement++;
			endInfo--;
		}
		if (upMovement - downMovement == 6 && endInfo > 12)
			moveDown = true;
		
		// Check Intersect Objects 
		if (upMovement - 2 >= 0) {
			if (backgroundInfo.get(upMovement - 2).get(0) == WATER) {
				if (getIntersectingObjects(Log.class).size() >= 1 && move) {
					move(getIntersectingObjects(Log.class).get(0).getSpeed(), 0);
				} else if (getIntersectingObjects(Turtle.class).size() >= 1 && move) {
					move(getIntersectingObjects(Turtle.class).get(0).getSpeed(), 0);
				} else if (getIntersectingObjects(WetTurtle.class).size() >= 1) {
					if (getIntersectingObjects(WetTurtle.class).get(0).isSunk()) 
						waterDeath = true;
					else move(getIntersectingObjects(WetTurtle.class).get(0).getSpeed(), 0);
				} else if (upMovement - 1 >= 0) {
					if (water.get(upMovement - 1) == 1) 
						waterDeath = true;
				}
			} else if (backgroundInfo.get(upMovement - 2).get(0) == LAND) {
				if (getIntersectingObjects(Obstacle.class).size() >= 1) 
					carDeath = true;
			} else if (backgroundInfo.get(upMovement - 2).get(0) == END) {
				if (getIntersectingObjects(End.class).size() >= 1 || secondEndFrame) {
					secondEndFrame = !secondEndFrame;
					move = false;
					if (!secondEndFrame) {
						setStart();
						move(0, MOVEMENT_Y);
						move = true;
					} else {
						if (!getIntersectingObjects(End.class).get(0).isActivated()) {
							getIntersectingObjects(End.class).get(0).setEnd();
							endActivated++;
							points += CHANGE_SCORE;
						}
						highest_y = MAX_Y;
						moveBG = true;
					}
				}
			}
		}

		// Death Animation 
		if (carDeath || waterDeath) {
			frame++;
			move = false;
		}
		if ((waterDeath && frame % 5 == 0) || (carDeath && frame % 6 == 0))
			deathFrame++;
		if ((waterDeath && frame == 24) || (carDeath && frame == 23))
			moveBG = true;
		if ((waterDeath && deathFrame == 5) || (carDeath && deathFrame == 4)) {
			setStart();
			deathFrame = 0;
			setImage(IMG_FROGUP);
			move = true;
			if (points > CHANGE_SCORE)
				points -= CHANGE_SCORE;
			if (waterDeath) waterDeath = false;
			else if (carDeath) carDeath = false;
			frame = 0;
		} else if (carDeath) setImage(getImage("carDeath"));
		else if (waterDeath) setImage(getImage("waterDeath"));
	}
	
	/**
	 * Sets the animal back to the starting point and resets the ends
	 * y position
	 */
	private void setStart() {
		moveBG = false;
		endInfo += downMovement;
		setX(START_X);
		setY(START_Y);
		upMovement = 1;
		downMovement = 0;
		deathFrame = 0;
	}

	@Override
	public Image getImage(String address) {
		if (address.matches(IMG_FROGUP + "||" + IMG_FROGDOWN + "||" + IMG_FROGUPJUMP + "||" + IMG_FROGDOWNJUMP))
			return new Image("file:media/images/frog/" + address + ".png", IMAGE_SIZE, 0, true, true);
		else if (address.contains("Death"))
			return new Image("file:media/images/death/" + address + deathFrame + ".png", IMAGE_SIZE, IMAGE_SIZE, true, true);
		else return new Image("file:media/images/frog/" + address + ".png", 0, IMAGE_SIZE, true, true);
	}

	/**
	 * Accessor: boolean frameMove
	 * 
	 * @return	frameMove
	 */
	public boolean isFrameMove() {
		return this.frameMove;
	}

	/**
	 * Mutators: boolean frameMove
	 * 
	 * @param	frameMove
	 */
	public void setFrameMove(boolean frameMove) {
		this.frameMove = frameMove;
	}

	/**
	 * Accessor: boolean move
	 * 
	 * @return	move
	 */
	public boolean isMove() {
		return this.move;
	}
	
	/**
	 * Accessor: boolean moveBG
	 * 
	 * @return	moveBG
	 */
	public boolean isMoveBG() {
		return this.moveBG;
	}

	/**
	 * Accessor: boolean moveDown
	 * 
	 * @return	moveDown
	 */
	public boolean isMoveDown() {
		return this.moveDown;
	}

	/**
	 * Accessor: int endActivated
	 * 
	 * @return endActivated
	 */
	public int getEndActivated() {
		return endActivated;
	}

	/**
	 * Accessor: int points
	 * 
	 * @return	points
	 */
	public int getPoints() {
		return this.points;
	}

	/**
	 * Mutators: int points
	 * 
	 * @param	points
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * Accessor: int upMovement
	 * 
	 * @return	upMovement
	 */
	public int getUpMovement() {
		return this.upMovement;
	}

	/**
	 * Mutators: int upMovement
	 * 
	 * @param	upMovement
	 */
	public void setUpMovement(int upMovement) {
		this.upMovement = upMovement;
	}

	/**
	 * Accessor: double highest_y
	 * 
	 * @return	highest_y
	 */
	public double getHighestY() {
		return this.highest_y;
	}

	/**
	 * Mutators: double highest_y
	 * 
	 * @param	highest_y
	 */
	public void setHighestY(double highest_y) {
		this.highest_y = highest_y;
	}

	/**
	 * Accessor: int downMovement
	 * 
	 * @return	downMovement
	 */
	public int getDownMovement() {
		return this.downMovement;
	}
}