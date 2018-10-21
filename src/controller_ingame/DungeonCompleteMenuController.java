package controller_ingame;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class DungeonCompleteMenuController extends Controller {
	private IngameHandler handler;
	@FXML
	private Button nextLevelButton;
	@FXML
	private Button restartButton;
	@FXML
	private Button returnButton;
	
	private boolean hasNextLevel;
	
	public DungeonCompleteMenuController(Stage s,
			                             IngameHandler handler,
			                             boolean hasNextLevel) {
		super(s);
		this.handler = handler;
		this.hasNextLevel = hasNextLevel;
	}
	
	@FXML
	private void initialize() {
		if (!hasNextLevel) {
			nextLevelButton.setVisible(false);
		}
	}
	
	@FXML
	private void handleNextLevelButton(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			handler.nextLevel();
		}
	}
	
	@FXML
	private void handleRestartButton(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			handler.restartLevel();
		}
	}
	
	@FXML
	private void handleReturnButton(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			handler.returnToPrevious();
		}
	}
}
