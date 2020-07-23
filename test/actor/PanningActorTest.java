package actor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import actor.panning.PanningActor;
import application.MainTest;

/**
 * Unit testing for the panning actor class.
 * @author Jonathan Foot
 */
@ExtendWith({MainTest.class})
public class PanningActorTest {
	
	/**
	 * Check PanningActors can be constructed properly.
	 */
	@Test
	public void constructorTest() {	
		PanningActor temp = new PanningActor(PanningActor.class.getResource("car1Left.png").toString(), 500, 490, 50, -5, false);
		assertEquals(false, temp.isSafe());
	}
	
	/**
	 * Checks the panning actors can move to the right correctly.
	 */
	@Test
	public void movementRightTest() {	
		int originalX = 500;
		int i = 0;
		PanningActor temp = new PanningActor(PanningActor.class.getResource("car1Left.png").toString(), originalX, 490, 50, 1, false);
		
		for(; i < 100; i++)
			temp.act(i);
		
		assertEquals(originalX+i, temp.getX());
	}
	
	/**
	 * Checks the panning actors can move to the left correctly.
	 */
	@Test
	public void movementLeftTest() {	
		int originalX = 300;
		int i = 0;
		PanningActor temp = new PanningActor(PanningActor.class.getResource("car1Left.png").toString(), originalX, 490, 50, -2, false);
		
		for(; i < 100; i++)
			temp.act(i);
		
		assertEquals(originalX-i*2, temp.getX());
	}
	
	/**
	 * Checks the panning actor will loop back around once they have gone off of the edge of the screen.
	 */
	@Test
	public void movmentOffScreen() {	
		int originalX = 400;
		int i = 0;
		PanningActor temp = new PanningActor(PanningActor.class.getResource("car1Left.png").toString(), originalX, 490, 50, 5, false);
		
		for(; i < 100; i++)
			temp.act(i);
		
		assertEquals(245 , temp.getX());
	}
}
