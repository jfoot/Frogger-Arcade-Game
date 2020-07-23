package application;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import actor.Act;
import actor.Actor;
import actor.BackgroundImage;
import actor.player.Player;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * Defines the logic for the Pane in the program, this predominantly sets the event handlers 
 * and manages interactions between nodes on the pane it self.
 * @author psyjpf
 *
 */
public abstract class World extends Pane{
    private AnimationTimer timer;
    
    /**
     * Creates the world object and sets up the event listeners.
     */
    public World() {
    	/**
    	 * Every time the scene is changed re-rerun the following.
    	 */
    	sceneProperty().addListener(new ChangeListener<Scene>() {
			/**
			 * Every time something is added to the scene do the following.
			 */
    		@Override
			public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
				if (newValue != null) {
					/**
					 * Create an on key realised event if one does not already exists.
					 */
					newValue.setOnKeyReleased(new EventHandler<KeyEvent>() {	
						@Override
						public void handle(KeyEvent event) {
							if(getOnKeyReleased() != null) 
								getOnKeyReleased().handle(event);
							List<Player> myActors = getObjects(Player.class);
							for (Player anActor: myActors) {
								if (anActor.getOnKeyReleased() != null) {
									anActor.getOnKeyReleased().handle(event);
								}
							}
						}
						
					});
					
					/**
					 * Every time a key is pressed tell all players that this event has occurred. 
					 */
					newValue.setOnKeyPressed(new EventHandler<KeyEvent>() {
						@Override
						public void handle(KeyEvent event) {
							if(getOnKeyPressed() != null) 
								getOnKeyPressed().handle(event);
							List<Player> myActors = getObjects(Player.class);
							for (Player anActor: myActors) {
								
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

    /**
     * Creates an animation timer, this is the main timer used throughout the whole program
     * which states what frame the program is in and that the actors need to do their next frame.
     */
    public void createTimer() {
    	if(timer == null) {
	        timer = new AnimationTimer() {
	            @Override
	            public void handle(long now) {
	            	  List<Act> actors = getActableObjects(Actor.class);
		                
		              for (Act anActor: actors) {
		                	anActor.act(now);
		              }

	            }
	        };
    	}
    }
    

    /**
     * Used to start the animation timer, which assumes the player is now in a level
     * and so also creates the return to main menu button.
     */
    public void start() {
    	createTimer();
        timer.start();
        
        Button returnButton = new Button();
        returnButton.setText("Return to Main Menu");
        returnButton.setId("backButton");
        returnButton.getStylesheets().add("style.css");
        returnButton.setLayoutX(BackgroundImage.width -200);
        returnButton.setLayoutY(BackgroundImage.height -50);
        returnButton.setOnAction((event) -> {
			MyStage.getInstanceSingelton().change("StartScreen");
			MyStage.getInstanceSingelton().stopMusic();
        });
        
       getChildren().add(returnButton);
    }
    /**
     * Halts the animation timer.
     */
    public void stop() {
        timer.stop();
    }
    /**
     * Adds a new actor to the window
     * @param actor the actor to add.
     */
    public void add(Actor actor) {
    	getChildren().add(actor);
    }
    
    /**
     * Removes everything from the window.
     * This is needed after a level has been exited or completed.
     */
    public void removeAll() {
    	  getChildren().removeAll(getChildren());
    }

    /**
     * Removes a specific actor from screen
     * @param actor the actor to remove.
     */
    public void remove(Actor actor) {
    	 getChildren().remove(actor);
    }
    
    /**
     * Gets all the objects on the pan which are extended from Actor and implement Act.
     * @param <A> Any Class which is extended from Actor
     * @param cls A specific type of class which is extended from actor
     * @return A list of objects that extend from Actor and implement Act.
     */

   	public <A extends Actor> List<Act> getActableObjects(Class<A> cls) {
       	ArrayList<A> temp = (ArrayList<A>)getObjects(cls);
    	temp.removeIf( actor -> !(actor instanceof Act));
       	List<Act> actSafe = temp.stream()
    		    .map(e -> (Act) e)
    		    .collect(Collectors.toList());
           return  actSafe;
    }	
    

    /**
     * Gets all the objects on the pan which are extended from Actor.
     * @param <A> Any Class which is extended from Actor
     * @param cls A specific type of class which is extended from actor
     * @return A list of objects that extend from Actor.
     */
    @SuppressWarnings("unchecked")
    public <A extends Actor> List<A> getObjects(Class<A> cls) {
        ArrayList<A> actorList = new ArrayList<A>();
        for (Node n: getChildren()) {
            if (cls.isInstance(n)) {
            	actorList.add((A)n);
            }
        }
        return actorList;
    }

}
