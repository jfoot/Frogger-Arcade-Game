package resources;

import application.MyStage;
import level.GenerateLevel;

/**
 * Used for unit testing, this dummy level is an empty level with no obstacles or targets to reach.
 * This allows us to test collision code for both car deaths and water deaths. 
 * @author Jonathan Foot
 */
public class DummyLevel extends GenerateLevel {

	public DummyLevel(int waterLevel, int noOfGoals, MyStage base, String BackgroundImage, Boolean playMusic) {
		super(waterLevel, noOfGoals, base, BackgroundImage, playMusic);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void generateRow1() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void generateRow2() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void generateRow3() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void generateRow4() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void generateRow5() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void generateRow6() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void generateRow7() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void generateRow8() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void generateRow9() {
		// TODO Auto-generated method stub
		
	}

}
