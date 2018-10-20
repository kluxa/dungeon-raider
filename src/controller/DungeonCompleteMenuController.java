package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class DungeonCompleteMenuController extends Controller {
	private PlayDungeon playDungeon;
	@FXML
	private Button nextLevelButton;
	@FXML
	private Button restartButton;
	@FXML
	private Button returnButton;
	
	private boolean hasNextLevel;
	
	public DungeonCompleteMenuController(Stage s,
			                             PlayDungeon playDungeon,
			                             boolean hasNextLevel) {
		super(s);
		this.playDungeon = playDungeon;
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
			playDungeon.nextLevel();
		}
	}
	
	@FXML
	private void handleRestartButton(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			playDungeon.restartLevel();
		}
	}
	
	@FXML
	private void handleReturnButton(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {
			playDungeon.returnToPrevious();
		}
	}
}
