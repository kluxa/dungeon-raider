package designer_controller;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LevelDesignerInitController extends Controller {
	private DesignerHandler designerHandler;
	
	@FXML
	private AnchorPane display;
	@FXML
	private Button loadLevelButton;
	@FXML
	private Button newLevelButton;
	@FXML
	private Button exitButton;
	
	public LevelDesignerInitController(Stage s, DesignerHandler handler) {
		super(s);
		this.designerHandler = handler;
	}
	
	@FXML
	private void initialize() {
		display.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
			KeyCode k = e.getCode();
			if (k == KeyCode.TAB) {
				e.consume();
			} else if (k == KeyCode.ESCAPE) {
				designerHandler.quit();
			}
		});
	}
	
	@FXML
	private void handleLoadLevelButton(KeyEvent e) {
		KeyCode k = e.getCode();
		if (k == KeyCode.ENTER) {
			designerHandler.switchToLoadScreen();
		}
	}
	
	@FXML
	private void handleNewLevelButton(KeyEvent e) {
		KeyCode k = e.getCode();
		if (k == KeyCode.ENTER) {
			designerHandler.switchToSizeScreen();
		}
	}
	
	@FXML
	private void handleExitButton(KeyEvent e) {
		KeyCode k = e.getCode();
		if (k == KeyCode.ENTER) {
			designerHandler.quit();
		}
	}
}
