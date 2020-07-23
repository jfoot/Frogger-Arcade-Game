package application;
import javafx.application.Platform;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

/**
 * To test JavaFX you need to ensure that it is initialised first, once initialised you can not initialise it
 * multiple times so must do so only once for all tests. The below code achives this!
 *  
 * @author Based original upon: https://stackoverflow.com/questions/43282798/in-junit-5-how-to-run-code-before-all-tests
 */
public class MainTest implements BeforeAllCallback {

    public static boolean started = false;
    /**
     * Before all unit tests ensure that JavaFX is initialised
     */
    @Override
    public void beforeAll(ExtensionContext context) {
        if (!started) {
            started = true;
            Platform.startup(()-> {
            	Platform.runLater(new Runnable(){
        			@Override
        			public void run() {
        				System.out.println("[INFO] JavaFX Graphics Initalised");
        			}
            	});
            	System.out.println("[INFO] JavaFX Initalised");
    		});
            context.getRoot().getStore(GLOBAL).put("JavaFXStarted", this);
        }
    }

}