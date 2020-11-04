package view.frames;
import model.*;
import view.App;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;


public abstract class World extends Pane {

    private AnimationTimer timer;
    Animal animal;
    MyStage background;
    ScoreBoard scoreBoard;
    App app;
    
    /**
    * This method sets the image of the start button when the cursor is not
    * hovering over it anymore.
    *
    * @see     image of start button
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
								if (anActor.getOnKeyReleased() != null) {
									anActor.getOnKeyReleased().handle(event);
								}
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
								if (anActor.getOnKeyPressed() != null) {
									anActor.getOnKeyPressed().handle(event);
								}
							}
						}
					});
				}
			}
		});
    }
    
    public void createTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                act(now);
                List<Actor> actors = getObjects(Actor.class);
                for (Actor anActor: actors) {
                	anActor.act(now);
                }
                System.out.println(animal.getPoints());
                // if (animal.resetScore()) {
            	// 	setNumber(animal.getPoints());
                // }
                // // if all ends are activated
            	// if (animal.getStop()) {
            	// 	System.out.println("STOPP:");
            	// 	background.stopMusic();
                //     stop();
                //     // change to scoreboard
                //     scoreBoard = new ScoreBoard(scoreBoard.getApp());
                //     Scene scoreBoardScene = new Scene(scoreBoard, 600, 800);
                //     app.getPrimaryStage().setScene(scoreBoardScene);
                //     app.getPrimaryStage().show();
            	// 	// Alert alert = new Alert(AlertType.INFORMATION);
            	// 	// alert.setTitle("You Have Won The Game!");
            	// 	// alert.setHeaderText("Your High Score: "+animal.getPoints()+"!");
            	// 	// alert.setContentText("Highest Possible Score: 800");
            	// 	// alert.show();
            	// }
            }
        };
    }

    public void start() {
        // background.playMusic();
    	createTimer();
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
    
    public void setNumber(int n) {
    	int shift = 0;
    	while (n > 0) {
            int d = n / 10;
            int k = n - d * 10;
            n = d;
            background.add(new Digit(k, 550 - shift, 25));
            shift+=30;
    	}
    }

    public void add(Actor actor) {
        getChildren().add(actor);
    }

    public void remove(Actor actor) {
        getChildren().remove(actor);
    }

    public <A extends Actor> List<A> getObjects(Class<A> cls) {
        ArrayList<A> someArray = new ArrayList<A>();
        for (Node n: getChildren()) {
            if (cls.isInstance(n)) {
                someArray.add((A)n);
            }
        }
        return someArray;
    }

    public abstract void act(long now);
}
