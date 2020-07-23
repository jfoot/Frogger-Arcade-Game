package actor.player;

import actor.Act;
import actor.Actor;
import actor.BackgroundImage;
import actor.End;
import actor.panning.PanningActor;
import actor.player.deathcontroller.CarDeath;
import actor.player.deathcontroller.DeathController;
import actor.player.deathcontroller.WaterDeath;
import actor.player.walkingcontroller.BackwardsWalk;
import actor.player.walkingcontroller.ForwardsWalk;
import actor.player.walkingcontroller.LeftWalk;
import actor.player.walkingcontroller.RightWalk;
import actor.player.walkingcontroller.WalkingController;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import level.Observer;

/**
 * The class which represents the main player object/ frog
 * @author psyjpf
 */
public class Player extends Actor implements Act {
	
	private final int waterLevel;
	private final Observer observer;
	private final int size = 40;
	
	
	private Integer lives;
	private DeathController healthState;
	private WalkingController walkingState;
	private double lastHeight = 800;
	private int points = 0;

	/**
	 * Constructs a new player object and links up event handlers
	 * When the WASD keys are pressed the frog will walk and change it's state.
	 * @param imageLink Defines the location of the start sprite image
	 * @param waterLevel Defines the water level, so the player knows if it is above water or not.
	 * @param lives Defines the number of lives the player has.
	 * @param observer the Observer to notify when the player score changes and hence game state
	 */
	public Player(String imageLink, Integer lives, Observer observer, int waterLevel) {
		this.waterLevel = waterLevel;
		this.lives = lives;
		this.observer = observer;
		healthState =  new DeathController(this);
		walkingState = new WalkingController(this);
		
		setImage(new Image(imageLink, getSize(), getSize(), true, true));
		
		setX(300);
		setY(679.8+ WalkingController.yDisplacment);
		
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event){
				if (!noMove()) {
					switch(event.getCode()) {
						case W:
			                walkingState.setState(new ForwardsWalk(walkingState));
							break;
						case A:
							 walkingState.setState(new LeftWalk(walkingState));
							 break;
						case S:
							walkingState.setState(new BackwardsWalk(walkingState));
							break;
						case D:
							 walkingState.setState(new RightWalk(walkingState));
							 break;
						default:
							break;
					}					
				}
			}
		});	
		
		setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (!noMove()) {
					if(event.getCode() == KeyCode.W && getY() < lastHeight) {	
						lastHeight = getY();
						setPoints(getPoints() + 10);						
					}
					
					if("WASD".contains(event.getCode().toString()))
						walkingState.clearState();
				}
			}	
		});
	}
	
	/**
	 * The main act method, which checks every frame the frog is still within the screen,
	 * has been hit by a car, has sunken or is at the end of the game.
	 */
	@Override
	public void act(long now) {	
		withInXBounds();
		withInYBounds();

		hasHitCar();
		checkIfSunken();
		
		checkIfAtEnd();
		healthState.act(now);
	}
	
	/**
	 * If the frog is outside of the X bounds of the screen put in back within.
	 */
	private void withInXBounds() {
		if (getX()<0) {
			setX(0);
		}
		if (getX()>(BackgroundImage.width - getWidth())) {
			setX(BackgroundImage.width - WalkingController.xDisplacment * 2);
		}
	}
	/**
	 * If the frog is outside the Y bounds of the screen put it back inside.
	 */
	private void withInYBounds() {
		if (getY()< 0 || getY()> (BackgroundImage.height - getHeight())) {
			setY(679.8+ WalkingController.yDisplacment);
		}
	}
	
	/**
	 * If the frog has been hit by a car set it's state to Car Death
	 */
	private void hasHitCar() {
		if (getIntersectingObjects(PanningActor.class).removeIf(c -> !c.isSafe())) {
			healthState.setState(new CarDeath());
		}
	}
	
	
	/**
	 * Check if the Turtle the player is on is sunken or not, if so set water death.
	 */
	private void checkIfSunken() {
		if(!movingWithSafeActor() && onWater()) {
			healthState.setState(new WaterDeath());
		}		
	}
	
	/**
	 * If a frog is on a log or turtle move the frog it and report back.
	 * @return is the frog on a log  or turtle or not
	 */
	private boolean movingWithSafeActor() {
		if (getIntersectingObjects(PanningActor.class).removeIf(c -> c.isSafe()) && !noMove()) {
			move(getIntersectingObjects(PanningActor.class).get(0).getSpeed(),0);
			return true;
		}
		return false;
	}

	
	/**
	 * @return if the player currently above water level on the map.
	 */
	private Boolean onWater() {
		return getY()< waterLevel;
	}
	
	/**
	 * Check if the player is at an end goal of the level and if so change the goals state and reset the frog to the start.
	 * If the player has already reached that goal state then just reset them to start, else increment score.
	 */
	public void checkIfAtEnd() {
		if (getIntersectingObjects(End.class).size() >= 1) {
			if (!getIntersectingObjects(End.class).get(0).isActivated()) {
				setPoints(getPoints() + 50);
			}
			
			lastHeight=800;
			walkingState.clearState();
			getIntersectingObjects(End.class).get(0).setEnd();			
			setX(300);
			setY(679.8+(WalkingController.yDisplacment*2));
		}
	}
	
	/**
	 * @return The Size of the player sprite.
	 */
	public int getSize() {
		return size;
	}

	
	/**
	 * @return The number of points the player has
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * @param value The value you want to set the new score to.
	 */
	public void setPoints(int value) {
		points = value;
		observer.update();
	}
	
	/**
	 * If the frog is dying the frog should not be able to move.
	 * @return Can the frog move or not.
	 */
	public boolean noMove() {
		return (healthState.getState() != null);
	}
	
	/**
	 * @return The number of lives the player has.
	 */
	public Integer getLives() {
		return lives;
	}
	
	/**
	 * Decreases the number of lives by one, when the player dies.
	 */
	public void decrementLife() {
		if(lives != null)
			lives--;
		walkingState.resetState();
		observer.update();
	}
	
}
