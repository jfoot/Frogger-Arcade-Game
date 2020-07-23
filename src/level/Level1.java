package level;

import actor.panning.PanningActor;
import actor.panning.Turtle;
import application.MyStage;

/**
 * Defines the implementation for the first/ default level.
 * @author psyjpf
 */
public class Level1 extends GenerateLevel {

	public Level1(MyStage base, Boolean playMusic) {
		super(413,5, base, Game.class.getResource("cleanBackground.png").toString(), playMusic);
		generateAllRows();
	}

	@Override
	public void generateRow1() {
		for (int i=0; i < 3; i++) {
			Background.add(new PanningActor(PanningActor.class.getResource("log3.png").toString(), i * 220, 166, 150, 0.75, true));
		}
	}

	@Override
	public void generateRow2() {
		for (int i=0; i < 3; i++) {
			Background.add(new Turtle(200 + i*200, 217, -1, 130, 130, true));
		}		
	}

	@Override
	public void generateRow3() {
		for (int i=0; i < 2; i++) {
			Background.add(new PanningActor(PanningActor.class.getResource("logs.png").toString(), i * 400, 276, 300, -2, true));
		}
	}

	@Override
	public void generateRow4() {
		Background.add(new PanningActor(PanningActor.class.getResource("log3.png").toString(), 50, 329, 150, 0.75, true));
		Background.add(new PanningActor(PanningActor.class.getResource("log3.png").toString(), 270, 329, 150, 0.75, true));
		Background.add(new PanningActor(PanningActor.class.getResource("log3.png").toString(), 490, 329, 150, 0.75, true));
	}

	@Override
	public void generateRow5() {
		for (int i=0; i < 3; i++) {
			Background.add(new Turtle(300 + i * 200, 376, -1, 130, 130, false));
		}
	}

	@Override
	public void generateRow6() {
		Background.add(new PanningActor(PanningActor.class.getResource("car1Left.png").toString(), 500, 490, 50, -5, false));	
	}

	@Override
	public void generateRow7() {
		for (int i=0; i < 2; i++) {
			Background.add(new PanningActor(PanningActor.class.getResource("truck2Right.png").toString(), i * 500, 540, 200, 1, false));
		}
	}

	@Override
	public void generateRow8() {
		for (int i=0; i < 4; i++) {
			Background.add(new PanningActor(PanningActor.class.getResource("car1Left.png").toString(), 100 + i * 150, 597, 50, -1, false));
		}
	}

	@Override
	public void generateRow9() {
		for (int i=0; i < 3; i++) {
			Background.add(new PanningActor(PanningActor.class.getResource("truck1Right.png").toString(), 300 * i, 649, 120, 1, false));
		}
	}
	
}
