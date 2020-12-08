package view;
import model.*;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public abstract class World extends Pane {

    Animal animal;
    MyStage background;
    ScoreBoard scoreBoard;
    App app;
	
	/**
	 * Configures listener for player's keyboard actions and controles
	 * addition and/or removal of actors in MyStage.
	 * 
	 * @see		MyStage
	 */
    public World() {
    	sceneProperty().addListener(new ChangeListener<Scene>() {
			@Override
			public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
				if (newValue != null) {
					newValue.setOnKeyReleased(new EventHandler<KeyEvent>() {
						@Override
						public void handle(KeyEvent event) {
							if(getOnKeyReleased() != null) 
								getOnKeyReleased().handle(event);
							List<Actor> myActors = getObjects(Actor.class);
							for (Actor anActor: myActors) {
								if (anActor.getOnKeyReleased() != null)
									anActor.getOnKeyReleased().handle(event);
							}
						}
					});
					newValue.setOnKeyPressed(new EventHandler<KeyEvent>() {
						@Override
						public void handle(KeyEvent event) {
							if(getOnKeyPressed() != null) 
								getOnKeyPressed().handle(event);
							List<Actor> myActors = getObjects(Actor.class);
							for (Actor anActor: myActors) {
								if (anActor.getOnKeyPressed() != null)
									anActor.getOnKeyPressed().handle(event);
							}
						}
					});
				}
			}
		});
    }

	/**
	 * Adds actor the the current pane
	 * 
	 * @param 	actor
	 * @see		Actor
	 */
    public void add(Actor actor) {
        getChildren().add(actor);
    }

	/**
	 * Removes actor from the current pane.
	 * 
	 * @param	actor
	 * @see		Actor
	 */
    public void remove(Actor actor) {
        getChildren().remove(actor);
    }

	/**
	 * Puts objects into an array
	 * 
	 * @param	cls  actor
	 * @return	someArray
	 * @see		Actor
	 */
    @SuppressWarnings("unchecked")
    public <A extends model.Actor> List<A> getObjects(Class<A> cls) {
        ArrayList<A> someArray = new ArrayList<A>();
        for (Node n: getChildren()) {
            if (cls.isInstance(n))
                someArray.add((A) n);
        }
        return someArray;
    }

    public abstract void act(long now);
}
