package model;
import view.frames.*;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class Actor extends ImageView{

    /**
	* This method set position after movement.
	*
	*/
    public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    /**
	* This method returns current pane.
	*
	* @return      Returns current pane
	*/
    public World getWorld() {
        return (World) getParent();
    }

    /**
	* This method returns the width of pane.
	*
	* @return      Returns width of pane
	*/
    public double getWidth() {
        return this.getBoundsInLocal().getWidth();
    }

    /**
	* This method returns the height of pane.
	*
	* @return      Returns height of pane
	*/
    public double getHeight() {
        return this.getBoundsInLocal().getHeight();
    }

    /**
	* This method returns position of certain object.
    *
    * @param  cls  array of object location
	* @return      Returns array of position of an object
	*/
    public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls){
        ArrayList<A> someArray = new ArrayList<A>();
        for (A actor: getWorld().getObjects(cls)) {
            if (actor != this && actor.intersects(this.getBoundsInLocal())) {
                someArray.add(actor);
            }
        }
        return someArray;
    }
    
    // public void manageInput(InputEvent e) {
        
    // }

    public abstract void act(long now);
}
