package application;

import java.io.IOException;
import javafx.fxml.FXML;


/**
 * Defines the controller for the StartScreen.FXML document
 * @author psyjpf
 */
public class InfoScreenController {
	
	/**
	 * Returns the Information screen back to the main menu.
	 * @throws IOException
	 */
	@FXML
    private void returnClick() throws IOException {
		MyStage.getInstanceSingelton().change("StartScreen");
	}
}
