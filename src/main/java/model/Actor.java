package model;
import view.*;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class Actor extends ImageView{

    /**
     * Move object's position by the parameters values.
     * 
     * @param   dx
     * @param   dy
     */
    public void move(double dx, int dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    /**
     * Checks if animal is intersecting with other objects in the stage.
     * 
     * @param   cls  Actor
     * @return  someArray
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
     * @param   address
     * @return  Image
     */
    public abstract Image getImage(String address);

    /**
     * This method must be overriden for use
     * 
     * @param   now  current frame
     */
    public abstract void act(long now);

    /**
     * Accessor: World world
     * 
     * @return  world
     */
    public World getWorld() {
        return (World) getParent();
    }
}
