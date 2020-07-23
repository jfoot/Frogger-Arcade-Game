package actor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import application.MainTest;

/**
 * Unit Tests for the End Class
 * @author Jonathan Foot
 */
@ExtendWith({MainTest.class})
public class EndTest {
	
	/**
	 * Ensures the end gates are constructed correctly
	 */
	@Test
	public void constructorTest() {	
		End temp = new End(0,0);
		assertEquals(false, temp.isActivated());
	}
	
	/**
	 * Ensures the end gate can be set to activated.
	 */
	@Test
	public void isActivatedTest() {	
		End temp = new End(0,0);
		temp.setEnd();
		assertEquals(true, temp.isActivated());
	}
}
