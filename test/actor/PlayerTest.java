package actor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import actor.panning.PanningActor;
import actor.player.Player;
import application.MyStage;
import javafx.stage.Stage;
import level.GenerateLevel;
import level.Game;
import resources.DummyLevel;

/**
 * Unit tests for the Player Class
 * @author psyjpf
 */
public class PlayerTest extends ApplicationTest  { 
	
	private GenerateLevel mapDummy;
	private Player frog;

	@Override
	  public void start (Stage stage) throws Exception {
		MyStage stageDummy = MyStage.getInstanceSingelton(stage);
		mapDummy = new DummyLevel(100, 5, stageDummy,  Game.class.getResource("cleanBackground.png").toString() , false);
		stageDummy.change(mapDummy, 16);
		MyStage.getInstanceSingelton().add(new PanningActor(PanningActor.class.getResource("truck1Right.png").toString(), 0, 200, 120, 0, false));	
		MyStage.getInstanceSingelton().add(new PanningActor(PanningActor.class.getResource("logs.png").toString(), 0, 50, 120, 0, true));	
		frog = MyStage.getInstanceSingelton().getLevel().getFrog();
	}
	
	@BeforeEach
	public void reset() {
		frog.setX(100);
		frog.setY(500);
		frog.act(0);
	}

	/**
	 * Checks that when the frog is above water it will drown.
	 */
	@Test
	public void checkWaterCollision() {	
		assertEquals(false,frog.noMove());
		frog.setX(0);
		frog.setY(0);
		frog.act(0);
		assertEquals(true,frog.noMove());	
	}
	
	
	/**
	 * Checks that when the frog is on a log but above water it will not drown.
	 */
	@Test
	public void checkLogCollision() {	
		assertEquals(false,frog.noMove());
		frog.setX(0);
		frog.setY(50);
		frog.act(0);
		assertEquals(false,frog.noMove());
		
	}
	
	 /**
	  * Checks that when the frog is moved to collide with an obstacle it dies.
	  */
	@Test
	public void checkCarDeath() {	
		assertEquals(false,frog.noMove());
		frog.setX(0);
		frog.setY(200);
		frog.act(0);
		assertEquals(true,frog.noMove());
	}
	
	 /**
	  * Checks that the frog remains with the X bounds of the screen.
	  */
	@Test
	public void checkXBounds() {	
		frog.setX(100);
		frog.setY(500);
		frog.act(0);
		assertEquals(100,frog.getX());
		
		frog.setX(-100);
		frog.act(0);
		assertTrue(frog.getX() >= 0);
		
		frog.setX(10000);
		frog.act(0);
		assertTrue(frog.getX() <= BackgroundImage.width);
	}
	
	 /**
	  * Checks that the frog remains with the Y bounds of the screen.
	  */
	@Test
	public void checkYBounds() {	
		frog.setX(100);
		frog.setY(500);
		frog.act(0);
		assertEquals(500,frog.getY());
		
		frog.setY(-100);
		frog.act(0);
		assertTrue(frog.getY() >= 0);
		
		frog.setY(10000);
		frog.act(0);
		assertTrue(frog.getY() <= BackgroundImage.height);
	}

	/**
	 * Checks the water levels are initialised properly 
	 */
	@Test
	public void checkWaterDeath() {
		assertEquals(100 ,mapDummy.getWaterLevel());
	}
	
	/**
	 * Checks the end goals are initialised properly
	 */
	@Test
	public void checkEndGoal() {
		assertEquals(5, mapDummy.getEndGoals());
	}
	
	/**
	 * Checks the points are correctly set.
	 */
	@Test
	public void checkPoints() {
		frog.setPoints(50);
		assertEquals(50,frog.getPoints());
		frog.setPoints(frog.getPoints() + 50);
		assertEquals(100,frog.getPoints());
		frog.setPoints(frog.getPoints() - 10);
		assertEquals(90,frog.getPoints());
	}
	
	/**
	 * Check we are always getting the same object reference.
	 */
	@Test
	public void checkSingelton() {
		assertSame(MyStage.getInstanceSingelton(), MyStage.getInstanceSingelton());
	}

	
}
