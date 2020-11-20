package model;
import view.*;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class Actor extends ImageView{

    /**
	* Move object position by dx and dy.
    *
    * @param  dx
    * @param  dy
	*/
    public void move(double dx, int dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    /**
	* Checks for animal if it is intersecting with an object
    *
    * @param    <A> Actor
    * @param    cls object array
    * @return   someArray
	*/
    public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls){
        ArrayList<A> someArray = new ArrayList<A>();
        for (A actor: getWorld().getObjects(cls)) {
            if (actor != this && actor.intersects(this.getBoundsInLocal()))
                someArray.add(actor);
        }
        return someArray;
    }
    
    /**
	* Set image according to the image name.
    *
    * @param    address
	*/
    public abstract Image getImage(String address);

    /**
	* This method must be overriden for use.
    *
    * @param  now current frame
	*/
    public abstract void act(long now);

    /**
	* Accessor: World world
	*
	* @return   world
	*/
    public World getWorld() {
        return (World) getParent();
    }
}
