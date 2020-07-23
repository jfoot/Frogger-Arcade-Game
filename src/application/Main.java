package application;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Defines the Main for starting the JavaFX application.
 * @author psyjpf
 */
public class Main extends Application  {
		
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Creates the stage used to render the whole program
	 */
	@Override
	public void start(Stage primaryStagse) throws Exception {
		MyStage.getInstanceSingelton(primaryStagse);
    }

}
