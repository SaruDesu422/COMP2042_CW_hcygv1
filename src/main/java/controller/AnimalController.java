package controller;

import model.Animal;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class AnimalController {

    private Animal animal;
    
	/**
	* Controls the actions for key events.
	* <pre>
	* Methods:<br>OnKeyPressed(KeyEvent event)<br>OnKeyReleased(KeyEvent event)
	* </pre>
    *
    * @param    animal
    * @see      Animal
	*/
    public AnimalController(Animal animal) {
        this.animal = animal;
    }

    /**
    * Configures actions when a key is pressed.
    * 
    * @param    event
    */
    public void OnKeyPressed(KeyEvent event) {
        if (animal.isMove()) {
            animal.setFrameMove(true);
            if (event.getCode() == KeyCode.W) {
                animal.move(0, -animal.MOVEMENT_Y);
                animal.setUpMovement(animal.getUpMovement() + 1);
                animal.setImage(animal.getImage(animal.IMG_FROGUPJUMP));
            } else if (event.getCode() == KeyCode.A) {
                animal.move(-animal.MOVEMENT_X, 0);
                animal.setImage(animal.getImage(animal.IMG_FROGLEFTJUMP));
            } else if (event.getCode() == KeyCode.S) {
                animal.move(0, animal.MOVEMENT_Y);
                animal.setUpMovement(animal.getUpMovement() - 1);
                animal.setImage(animal.getImage(animal.IMG_FROGDOWNJUMP));
            } else if (event.getCode() == KeyCode.D) {
                animal.move(animal.MOVEMENT_X, 0);
                animal.setImage(animal.getImage(animal.IMG_FROGRIGHTJUMP));
            }
        }
    }

    /**
    * Configures actions when a key is released.
    * 
    * @param    event
    */
    public void OnKeyReleased(KeyEvent event) {
        if (animal.isFrameMove()) {
            if (event.getCode() == KeyCode.W) {
                if (animal.getY() < animal.getHighestY()) {
                    animal.setHighestY(animal.getY());
                    animal.setPoints(animal.getPoints() + 10);
                }
                animal.move(0, -animal.MOVEMENT_Y);
                animal.setImage(animal.getImage(animal.IMG_FROGUP));
            } else if (event.getCode() == KeyCode.A) {
                animal.move(-animal.MOVEMENT_X, 0);
                animal.setImage(animal.getImage(animal.IMG_FROGLEFT));
            } else if (event.getCode() == KeyCode.S) {
                animal.move(0, animal.MOVEMENT_Y);
                animal.setImage(animal.getImage(animal.IMG_FROGDOWN));
            } else if (event.getCode() == KeyCode.D) {
                animal.move(animal.MOVEMENT_X, 0);
                animal.setImage(animal.getImage(animal.IMG_FROGRIGHT));
            }
            animal.setFrameMove(false);
        }
    }
    
}
