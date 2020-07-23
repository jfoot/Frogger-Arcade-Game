package actor.panning;

import javafx.scene.image.Image;
/**
 * Defines the turtle obstacle which lets the players stand on top of them.
 * @author psyjpf
 */
public class Turtle extends PanningActor{
	
	private Image[] turtleFrames;
		
	/**
	 * Constructs a new Turtle object.
	 * @param xpos The X position of the Turtle
	 * @param ypos The Y position of the Turtle
	 * @param speed The Speed of the Turtle
	 * @param width The Width of the Turtle
	 * @param height The Height of the Turtle
	 * @param isSinkable states what type of turtle it is, if it can sink or not.
	 */
	public Turtle(int xpos, int ypos, double speed, int width, int height, boolean isSinkable) {
		super(xpos, ypos, speed);
		if(isSinkable) {
			turtleFrames = new Image[] {
				new Image(Turtle.class.getResource("TurtleAnimation2Wet.png").toString(), width, height, true, true),	
				new Image(Turtle.class.getResource("TurtleAnimation1.png").toString(), width, height, true, true),
				new Image(Turtle.class.getResource("TurtleAnimation3Wet.png").toString(), width, height, true, true),
				new Image(Turtle.class.getResource("TurtleAnimation4Wet.png").toString(), width, height, true, true)
			};
		}else {
			turtleFrames = new Image[] {
				new Image(Turtle.class.getResource("TurtleAnimation2.png").toString(), width, height, true, true),	
				new Image(Turtle.class.getResource("TurtleAnimation1.png").toString(), width, height, true, true),
				new Image(Turtle.class.getResource("TurtleAnimation3.png").toString(), width, height, true, true),
			};
		}
		setImage(turtleFrames[0]);
	}
	
	/**
	 * Defines the action of the object carries out, every number of frames it will cycle through
	 * the animations frames in the array.
	 */
	@Override
	public void act(long now) {
		super.act(now);
		setImage(turtleFrames[(int) (now/900000000  % turtleFrames.length)]);
		setIsSafe(((int) (now/900000000  % turtleFrames.length) != 3));
	}
}
