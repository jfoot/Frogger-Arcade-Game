package level;

import actor.panning.PanningActor;
import actor.panning.Turtle;
import application.MyStage;

/**
 * Defines the implementation for the second level.
 * @author psyjpf
 */
public class Level2 extends GenerateLevel {

	public Level2(MyStage base, Boolean playMusic) {
		super(413,7, base, Game.class.getResource("cleanBackground.png").toString(), playMusic);
		generateAllRows();
	}
	@Override
	public void generateRow1() {
		for (int i=0; i < 2; i++) {
			Background.add(new PanningActor(PanningActor.class.getResource("log3.png").toString(), i * 220, 166, 150, -3.5, true));
		}
	}

	@Override
	public void generateRow2() {
		for (int i=0; i < 3; i++) {
			Background.add(new PanningActor(PanningActor.class.getResource("log3.png").toString(), i * 220, 217, 150, -0.75, true));
		}		
	}

	@Override
	public void generateRow3() {
		for (int i=0; i < 2; i++) {
			Background.add(new Turtle(300 + i * 200, 276, -1.5, 130, 130, true));
		}
	}

	@Override
	public void generateRow4() {
		for (int i=0; i < 2; i++) {
			Background.add(new PanningActor(PanningActor.class.getResource("logs.png").toString(), i * 400, 329, 300, -1.75, true));
		}
		
	}

	@Override
	public void generateRow5() {
		for (int i=0; i < 3; i++) {
			Background.add(new Turtle(300 + i * 200, 376, -2, 130, 130, true));
		}
	}

	@Override
	public void generateRow6() {
		Background.add(new PanningActor(PanningActor.class.getResource("truck1Right.png").toString(), 200, 490, 120, 2.5, false));	
		Background.add(new PanningActor(PanningActor.class.getResource("truck1Right.png").toString(), 500, 490, 120, 2.5, false));	
	}

	@Override
	public void generateRow7() {
		for (int i=0; i < 2; i++) {
			Background.add(new PanningActor(PanningActor.class.getResource("truck2Right.png").toString(), i * 500, 540, 200, 1.3, false));
		}
	}

	@Override
	public void generateRow8() {
		for (int i=0; i < 3; i++) {
			Background.add(new PanningActor(PanningActor.class.getResource("truck1Right.png").toString(), 100 + i * 150, 597, 120, 1, false));
		}
	}

	@Override
	public void generateRow9() {
		for (int i=0; i < 3; i++) {
			Background.add(new PanningActor(PanningActor.class.getResource("truck1Right.png").toString(), 300 * i, 649, 120, 0.5, false));
		}
	}
}
