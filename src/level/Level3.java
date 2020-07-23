package level;


import actor.panning.PanningActor;
import actor.panning.Turtle;
import application.MyStage;
/**
 * Defines the implementation for the third level.
 * @author psyjpf
 */
public class Level3 extends GenerateLevel {

	public Level3(MyStage base, Boolean playMusic) {
		super(675,1, base, Game.class.getResource("cleanBackground2.png").toString(), playMusic);
		generateAllRows();
	}
	@Override
	public void generateRow1() {
		for (int i=0; i < 2; i++) {
			Background.add(new PanningActor(PanningActor.class.getResource("log3.png").toString(), i * 220, 166, 150, 5, true));
		}
	}

	@Override
	public void generateRow2() {
		for (int i=0; i < 2; i++) {
			Background.add(new PanningActor(PanningActor.class.getResource("log3.png").toString(), i * 220, 217, 150, -2.5, true));
		}		
	}

	@Override
	public void generateRow3() {
		for (int i=0; i < 2; i++) {
			Background.add(new PanningActor(PanningActor.class.getResource("log3.png").toString(), i * 220, 276, 150, -0.75, true));
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
		for (int i=0; i < 2; i++) {
			Background.add(new PanningActor(PanningActor.class.getResource("logs.png").toString(), i * 400, 376, 300, 4, true));
		}
		for (int i=0; i < 3; i++) {
			Background.add(new PanningActor(PanningActor.class.getResource("logs.png").toString(), i * 400, 440, 300, 2, true));
		}
	}

	@Override
	public void generateRow6() {
		for (int i=0; i < 3; i++) {
			Background.add(new Turtle(200 + i*200, 490, -1, 130, 130, true));
		}
	}

	@Override
	public void generateRow7() {
		for (int i=0; i < 2; i++) {
			Background.add(new PanningActor(PanningActor.class.getResource("log3.png").toString(), i * 400, 540, 150, -1.75, true));
		}
	}

	@Override
	public void generateRow8() {
		for (int i=0; i < 2; i++) {
			Background.add(new PanningActor(PanningActor.class.getResource("logs.png").toString(), i * 400, 597, 300, -0.75, true));
		}
	}

	@Override
	public void generateRow9() {
		for (int i=0; i < 3; i++) {
			Background.add(new Turtle(200 + i*200, 649, -1, 130, 130, true));
		}
	}
}
