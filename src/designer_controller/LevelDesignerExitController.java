package designer_controller;

import controller.Controller;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LevelDesignerExitController extends Controller {
	private DesignerHandler designerHandler;
	
	@FXML
	private AnchorPane display;
	@FXML
	private Button yesButton;
	@FXML
	private Button noButton;
	
	public LevelDesignerExitController(Stage s, DesignerHandler handler) {
		super(s);
		this.designerHandler = handler;
	}
	
	@FXML
	private void initialize() {
		display.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode() == KeyCode.TAB) {
				e.consume();
			} else if (e.getCode() == KeyCode.ESCAPE) {
				designerHandler.switchToMainScreen();
			}
		});
	}
	
	@FXML
	private void handleYesButton(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), display);
			fadeOut.setFromValue(1.0);
			fadeOut.setToValue(0.0);
			fadeOut.play();
			fadeOut.setOnFinished(finished -> {
				designerHandler.quit();
			});
		}
	}
	
	@FXML
	private void handleNoButton(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			designerHandler.switchToMainScreen();
		}
	}
}
