package actor;

import javafx.scene.image.ImageView;
import java.util.ArrayList;

import application.World;

/**
 * The Actor class is used to define any sprite in the program, for example the player and obstacles.
 * @author Jonathan Foot
 */
public abstract class Actor extends ImageView{

	/**
	 * Moves an actor by an X and Y amount.
	 * @param dx The movement change in X
	 * @param dy The movement change in Y
	 */
    public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    /**
     * @return The Pane for which the Actor is drawn on.
     */
    public World getWorld() {
        return (World) getParent();
    }

    /**
     * @return The width of the Actors sprite/ image.
     */
    public double getWidth() {
        return this.getBoundsInLocal().getWidth();
    }

    /**
     * @return The height of the Actor sprite/ image.
     */
    public double getHeight() {
        return this.getBoundsInLocal().getHeight();
    }

    /**
     * Gets a list of objects that are extended from Actor and are intersecting.
     * @param <A> represents any class which extends from Actor class
     * @param cls represents a specific class which is extended from Actor.
     * @return A list of Actors which are intersecting with each other.
     */
    public <A extends Actor> java.util.List<A> getIntersectingObjects(Class<A> cls){
        ArrayList<A> someArray = new ArrayList<A>();
        for (A actor: getWorld().getObjects(cls)) {
            if (actor != this && actor.intersects(this.getBoundsInLocal())) {
                someArray.add(actor);
            }
        }
        return someArray;
    }
    
    /**
     * Gets a single object that extends from actor and is intersecting.
     * @param <A> represents any class which extends from Actor class
     * @param cls represents a specific class which is extended from Actor.
     * @return A single Actor which are intersecting with another.
     */
    public <A extends Actor> A getOneIntersectingObject(Class<A> cls) {
         return getIntersectingObjects(cls).get(0);
    }
}
